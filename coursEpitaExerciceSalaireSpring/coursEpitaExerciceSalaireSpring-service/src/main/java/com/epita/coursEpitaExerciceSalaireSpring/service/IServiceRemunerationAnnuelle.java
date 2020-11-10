package com.epita.coursEpitaExerciceSalaireSpring.service;

import java.util.Optional;

import com.epita.coursEpitaExerciceSalaireSpring.entite.RemunerationAnnuelle;

public interface IServiceRemunerationAnnuelle {

	void createRemunerationAnnuelle(RemunerationAnnuelle remunerationAnnuelle);
	
	Optional<RemunerationAnnuelle> getRemunerationById(Long id);
}
