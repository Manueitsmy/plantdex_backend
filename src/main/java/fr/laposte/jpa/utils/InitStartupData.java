package fr.laposte.jpa.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import fr.laposte.jpa.repository.CategorieRepository;
import fr.laposte.jpa.repository.PlanteRepository;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class InitStartupData implements ApplicationRunner {

	@Autowired
	private PlanteRepository planteRepository;

	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (args.getNonOptionArgs().contains("clean")) {
			log.info("Option clean : nettoyage des données");
			planteRepository.deleteAll();
		}

		if (args.getNonOptionArgs().contains("demo")) {
			log.info("Option demo : nettoyage des données et création de données");
			planteRepository.deleteAll();
			categorieRepository.deleteAll();
		}
	}
}