package fr.laposte.jpa.service;

import java.util.List;

import fr.laposte.jpa.service.dto.CategorieDTO;

public interface ServiceCategorie {

	List<CategorieDTO> rechercherTout();
	CategorieDTO retrouverParId(int id);
	CategorieDTO retrouverParLlibelle(String libelle);
    long total();
	
}
