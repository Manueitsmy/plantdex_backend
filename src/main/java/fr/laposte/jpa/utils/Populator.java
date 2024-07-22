package fr.laposte.jpa.utils;

import org.springframework.beans.factory.annotation.Autowired;

import fr.laposte.jpa.model.Categorie;
import fr.laposte.jpa.model.Exposition;
import fr.laposte.jpa.model.Plante;
import fr.laposte.jpa.repository.CategorieRepository;
import fr.laposte.jpa.repository.PlanteRepository;

public class Populator {

	@Autowired
	private CategorieRepository categories;
	@Autowired
	private PlanteRepository plantes;

	public void clean() {
		plantes.deleteAll();
		categories.deleteAll();
	}

	public void basic() {
		
		clean();
		
		Categorie ci = categories.save(new Categorie("intérieur"));
		Categorie ce = categories.save(new Categorie("extérieur"));
		Categorie cx =categories.save(new Categorie("exotique"));
		
		String url = "https://static4.depositphotos.com/1026290/368/i/450/depositphotos_3682375-stock-photo-houseplant.jpg";
		
		plantes.save(new Plante("plante 1", Exposition.BEAUCOUP, 3, ci, url));
		plantes.save(new Plante("plante 2", Exposition.MOYEN, 2, ce,url));
		plantes.save(new Plante("plante 3", Exposition.PEU, 1, cx,url));
		plantes.save(new Plante("plante 4", Exposition.MOYEN, 2, cx,url));		
	}
}
