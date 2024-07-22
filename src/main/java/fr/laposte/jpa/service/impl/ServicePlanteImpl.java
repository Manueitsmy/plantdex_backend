package fr.laposte.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.laposte.jpa.model.Categorie;
import fr.laposte.jpa.model.Exposition;
import fr.laposte.jpa.model.Plante;
import fr.laposte.jpa.repository.CategorieRepository;
import fr.laposte.jpa.repository.PlanteRepository;
import fr.laposte.jpa.service.ServicePlante;
import fr.laposte.jpa.service.dto.DTOMapper;
import fr.laposte.jpa.service.dto.PlanteDTO;


@Service
public class ServicePlanteImpl implements ServicePlante {

	@Autowired
	private PlanteRepository planteRepository;

	@Autowired
	private CategorieRepository categorieRepository;

	@Autowired
	private DTOMapper dtoMapper;

	@Override
	public List<PlanteDTO> rechercherTout() {
		return dtoMapper.planteVersDTO(planteRepository.findAll());
	}

	@Override
	public List<PlanteDTO> rechercherParMotCle(String motCle) {
		return dtoMapper.planteVersDTO(planteRepository.findByNomContains(motCle));
	}

	@Override
	public List<PlanteDTO> rechercherParCategorie(int categorieId) {
		return dtoMapper.planteVersDTO(planteRepository.findByCategorieId(categorieId));
	}

	@Override
	public List<PlanteDTO> rechercherParExposition(Exposition exposition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanteDTO> rechercherParArrosage(int arrosage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanteDTO retrouverParId(int id) {

		return dtoMapper.planteVersDTO(planteRepository.findById(id).orElseThrow());
	}

	@Override
	public PlanteDTO ajouter(PlanteDTO plante) {
		Categorie categorie = categorieRepository.findByLibelle(plante.getCategorieLibelle());
		Plante target = dtoMapper.planteDTOVersPlante(plante);
		target.setCategorie(categorie);
		return dtoMapper.planteVersDTO(planteRepository.save(target));

	}

	@Override
	public PlanteDTO modifier(PlanteDTO plante, int id) {
		
		PlanteDTO target = null;
		
		if (planteRepository.findById(id).isPresent()) {
			Categorie categorie = categorieRepository.findByLibelle(plante.getCategorieLibelle());
			Plante planteModel =planteRepository.findById(id).orElseThrow();
			planteModel = dtoMapper.planteDTOVersPlante(plante);
			planteModel.setCategorie(categorie);
			
			//pas obligatoire si id seulement dans URL
			planteModel.setId(id);
			
			target = dtoMapper.planteVersDTO(planteRepository.save(planteModel));
		}

		return target;
	}

	@Override
	public void supprimerParId(int id) {
		planteRepository.deleteById(id);
	}

	@Override
	public long total() {
		return planteRepository.count();
	}
}