package com.epita.coursEpitaExerciceSalaireSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epita.coursEpitaExerciceSalaireSpring.entite.Personne;

public interface IDaoPersonne extends JpaRepository<Personne, Long>{

}
