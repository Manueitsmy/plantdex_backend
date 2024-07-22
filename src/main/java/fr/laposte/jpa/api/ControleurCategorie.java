package fr.laposte.jpa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.laposte.jpa.service.ServiceCategorie;
import fr.laposte.jpa.service.dto.CategorieDTO;

@RestController
@RequestMapping("/api/categories")
public class ControleurCategorie {

	@Autowired
	private ServiceCategorie serviceCategorie;

	@GetMapping
	public List<CategorieDTO> getCategories() {
		return serviceCategorie.rechercherTout();
	}

	@GetMapping("/{id}")
	public CategorieDTO getCatgorieById(@PathVariable int id) {
		return serviceCategorie.retrouverParId(id);
	}

}