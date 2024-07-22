package fr.laposte.jpa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.laposte.jpa.service.ServicePlante;
import fr.laposte.jpa.service.dto.PlanteDTO;


@RestController
@RequestMapping("api/plantes")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControleurPlante {

	@Autowired
	private ServicePlante servicePlante;

	@GetMapping
	// Pour construire un filtre basé sur des ET et des OU ce n'est pas si simple
	// Une solution est d'abord de baser les repository sur JPA Spécifications
	// ET ensuite de créer un objet spécialisé qui sera envoyé depuis le front puis
	// parsé
	public List<PlanteDTO> getPlantes(@RequestParam(required = false, name = "motcle") String motCle,
			@RequestParam(required = false, name = "categorie") Integer categorieId) {

		if (motCle != null) {
			return servicePlante.rechercherParMotCle(motCle);
		} else if (categorieId != null) {

			return servicePlante.rechercherParCategorie(categorieId);
		} else {
			return servicePlante.rechercherTout();
		}
	}

	@GetMapping("/{id}")
	public PlanteDTO getPlanteById(@PathVariable int id) {
		return servicePlante.retrouverParId(id);
	}

	@PostMapping
	public PlanteDTO addPlante(@RequestBody PlanteDTO plante) {
		return servicePlante.ajouter(plante);
	}

	@DeleteMapping("/{id}")
	public void deletePlante(@PathVariable int id) {
		servicePlante.supprimerParId(id);
	}
	
	@PutMapping("/{id}")
	public void updatePlante(@PathVariable int id,@RequestBody PlanteDTO plante) {
		servicePlante.modifier(plante, id);
	}

}