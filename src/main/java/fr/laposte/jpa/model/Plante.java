package fr.laposte.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Plante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	@Size(max = 20)
	@NonNull
	private String nom;
	
	@Enumerated(EnumType.STRING)
	@NonNull
	private Exposition soleil;
	
	@Min(1)
	@Max(3)
	@NonNull
	private Integer arrosage;
	
	@ManyToOne
	@NonNull
	private Categorie categorie;
	
	@NonNull
	private String image;

}
