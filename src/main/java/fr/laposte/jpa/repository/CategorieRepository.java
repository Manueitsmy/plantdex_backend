package fr.laposte.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.laposte.jpa.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer>{

	Categorie findByLibelle (String libelle);
}
