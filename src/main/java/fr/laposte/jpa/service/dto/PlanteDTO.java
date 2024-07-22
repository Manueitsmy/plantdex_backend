package fr.laposte.jpa.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlanteDTO {
	
	private int id;	
	private String nom;	
	private int arrosage;		
	private String soleil;
	
	//permet de renommer la propriété JSON
	@JsonProperty("categorie")
	private String categorieLibelle;	
	private String image;
	
}
