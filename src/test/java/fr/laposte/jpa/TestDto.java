package fr.laposte.jpa;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fr.laposte.jpa.model.Categorie;
import fr.laposte.jpa.model.Exposition;
import fr.laposte.jpa.model.Plante;
import fr.laposte.jpa.service.dto.PlanteDTO;


@SpringBootTest
public class TestDto {

	@Autowired
	private ModelMapper modelMapper;
	
	@Test
	public void testMap() {
		Plante test = new Plante();
		Categorie cactus = new Categorie();
		cactus.setLibelle("Cactus");
		test.setArrosage(2);
		test.setCategorie(cactus);
		test.setSoleil(Exposition.MOYEN);
		test.setNom("Test");
				
		PlanteDTO dto = modelMapper.map(test, PlanteDTO.class);
		System.out.println(dto);
	}

}