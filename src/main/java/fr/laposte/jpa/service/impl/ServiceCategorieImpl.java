package fr.laposte.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.laposte.jpa.repository.CategorieRepository;
import fr.laposte.jpa.service.ServiceCategorie;
import fr.laposte.jpa.service.dto.CategorieDTO;
import fr.laposte.jpa.service.dto.DTOMapper;

@Service
public class ServiceCategorieImpl implements ServiceCategorie {
	
	@Autowired
	private CategorieRepository categorieRepository;

	@Autowired
	private DTOMapper dtoMapper;

	@Override
	public List<CategorieDTO> rechercherTout() {
		return dtoMapper.categorieVersDTO(categorieRepository.findAll());
	}

	@Override
	public CategorieDTO retrouverParId(int id) {
		return dtoMapper.categorieVersDTO(categorieRepository.findById(id).orElseThrow());
	}

	@Override
	public CategorieDTO retrouverParLlibelle(String libelle) {
		return dtoMapper.categorieVersDTO(categorieRepository.findByLibelle(libelle));
	}

	@Override
	public long total() {
		return categorieRepository.count();
	}
}
