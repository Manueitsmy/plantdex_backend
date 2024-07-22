package fr.laposte.jpa.conf;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Log4j2
public class WebConfig{

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		
		httpSecurity.authorizeHttpRequests(authz -> authz
	            .requestMatchers(HttpMethod.GET).permitAll().anyRequest().authenticated())
		
		.oauth2ResourceServer(oauth2Configurer -> oauth2Configurer
				.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt -> {
					Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
					Collection<String> roles = realmAccess.get("roles");
					var grantedAuthorities = roles.stream()
							.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
							.collect(Collectors.toList());
					return new JwtAuthenticationToken(jwt, grantedAuthorities);
				}))
				
				.withObjectPostProcessor(new ObjectPostProcessor<BearerTokenAuthenticationFilter>() {                       	
					@Override
					public <O extends BearerTokenAuthenticationFilter> O postProcess(O filter) {								
						filter.setAuthenticationFailureHandler((request, response, exception) -> {
							String jwt = request.getHeader("Authorization");
							log.warn(request.getRequestURI()+" - Auth failed - " + request.getRemoteAddr()+" - "+jwt);
							BearerTokenAuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();
							delegate.commence(request, response, exception);
						});
						return filter;
					}
				}
						)).sessionManagement((sessionManagement) -> sessionManagement
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return httpSecurity.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:4200"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
			