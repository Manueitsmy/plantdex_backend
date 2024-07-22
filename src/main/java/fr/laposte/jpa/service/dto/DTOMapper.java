package fr.laposte.jpa.service.dto;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.laposte.jpa.model.Categorie;
import fr.laposte.jpa.model.Plante;


@Component
public class DTOMapper {

	@Autowired
	private ModelMapper modelMapper;

	// Plante

	public List<PlanteDTO> planteVersDTO(List<Plante> plantes) {
		//peek permet de modifier un attribut à la volée, ici on veut passer les valeurs de soleil en minuscule
		return plantes.stream()
				.map(p -> modelMapper.map(p, PlanteDTO.class))
				.peek(e -> e.setSoleil(e.getSoleil().toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Plante> planteDTOVersPlante(List<PlanteDTO> plantesDTO) {
		return plantesDTO.stream().map(p -> modelMapper.map(p, Plante.class)).collect(Collectors.toList());
	}

	public PlanteDTO planteVersDTO(Plante plante) {
		return modelMapper.map(plante, PlanteDTO.class);
	}

	public Plante planteDTOVersPlante(PlanteDTO planteDTO) {
		planteDTO.setSoleil(planteDTO.getSoleil().toUpperCase());
		return modelMapper.map(planteDTO, Plante.class);
	}

	// Categorie

	public List<CategorieDTO> categorieVersDTO(List<Categorie> categories) {
		return categories.stream().map(c -> modelMapper.map(c, CategorieDTO.class)).collect(Collectors.toList());
	}

	public List<Categorie> categorieDTOVersCategorie(List<CategorieDTO> categoriesDTO) {
		return categoriesDTO.stream().map(c -> modelMapper.map(c, Categorie.class)).collect(Collectors.toList());
	}

	public CategorieDTO categorieVersDTO(Categorie categorie) {
		return modelMapper.map(categorie, CategorieDTO.class);
	}

	public Categorie categorieDTOVersCategorie(CategorieDTO categorieDto) {
		return modelMapper.map(categorieDto, Categorie.class);
	}
}