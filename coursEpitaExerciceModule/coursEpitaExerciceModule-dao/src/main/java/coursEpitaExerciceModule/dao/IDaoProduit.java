package coursEpitaExerciceModule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import coursEpitaExerciceModule.entite.Produit;

public interface IDaoProduit extends JpaRepository<Produit, Long>{

}
