package com.epita.coursEpitaExerciceSalaireSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epita.coursEpitaExerciceSalaireSpring.entite.Entreprise;

public interface IDaoEntreprise extends JpaRepository<Entreprise, Long> {

}
