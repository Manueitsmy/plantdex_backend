package fr.laposte.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import fr.laposte.jpa.model.Plante;

public interface PlanteRepository extends JpaRepository<Plante, Integer> {
	
	List<Plante> findByNomContains(String motCle);
	List<Plante> findByCategorieId(int categorieId);

}
