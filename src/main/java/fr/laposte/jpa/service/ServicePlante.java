package fr.laposte.jpa.service;

import java.util.List;

import fr.laposte.jpa.model.Exposition;
import fr.laposte.jpa.service.dto.PlanteDTO;

public interface ServicePlante {

	//Partie Catégories
		
	
	//Partie Plantes
	List<PlanteDTO> rechercherTout();
	List<PlanteDTO> rechercherParMotCle(String motCle);
	List<PlanteDTO> rechercherParCategorie(int categorieId);
	List<PlanteDTO> rechercherParExposition(Exposition exposition);
	List<PlanteDTO> rechercherParArrosage(int arrosage);
	
	PlanteDTO retrouverParId(int id);
	
	//@PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_EDITOR')")
	
	PlanteDTO ajouter(PlanteDTO plante);
	
	PlanteDTO modifier(PlanteDTO plante, int id);
	
	void supprimerParId(int id);
	
	long total();
}
 