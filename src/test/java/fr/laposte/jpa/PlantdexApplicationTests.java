package fr.laposte.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.laposte.jpa.model.Categorie;
import fr.laposte.jpa.model.Exposition;
import fr.laposte.jpa.model.Plante;
import fr.laposte.jpa.repository.CategorieRepository;
import fr.laposte.jpa.repository.PlanteRepository;

@SpringBootTest
class PlantdexApplicationTests {

	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private PlanteRepository planteRepository;


	@BeforeEach
	void clean() {

		planteRepository.deleteAll();
		categorieRepository.deleteAll();

		Categorie plantesFleuries = new Categorie("Plantes fleuries");
		Categorie orchidees = new Categorie("Orchidées");
		Categorie cactusPlantesGrasses = new Categorie("Cactus et plantes grasses");
		Categorie arbres = new Categorie("Arbres");

		categorieRepository.saveAll(Arrays.asList(plantesFleuries, orchidees, cactusPlantesGrasses, arbres));

		
		 Plante anthurium = new Plante("Anthurium",Exposition.MOYEN,2, plantesFleuries, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRTy8icDTOticBDvf3gqxcn12vjXjMjjl9YUtQsdIKnA&s");
		 Plante orchideeb = new Plante("Orchidée blanche", Exposition.BEAUCOUP, 3, orchidees, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQo1KUmx4o0P4wsyEv3bX5r6RJyJZ9srjb3Rj0yqUnLRA&s");
		 Plante cactusr = new Plante("Cactus raquette", Exposition.MOYEN, 2, cactusPlantesGrasses, "https://media.ooreka.fr/public/image/cactus-raquette-main-12825523.jpg");
		 Plante olivier = new Plante("Olivier", Exposition.BEAUCOUP, 2, arbres, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfimYIgNGclr5CZlhkVRrnrqlf9pZBzBLZ1aVzWKxhVQ&s");
		 Plante palmier = new Plante("Palmier", Exposition.BEAUCOUP, 1, arbres, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiz0Ad02YPilknBAtbnx11jB02OjD6j18r553cEl6ZKA&s");
		 
		 planteRepository.saveAll(Arrays.asList(anthurium, orchideeb, cactusr, olivier, palmier));
		 
	}

	@Test
	void contextLoads() {
		assertEquals(2, 2);
	}
}