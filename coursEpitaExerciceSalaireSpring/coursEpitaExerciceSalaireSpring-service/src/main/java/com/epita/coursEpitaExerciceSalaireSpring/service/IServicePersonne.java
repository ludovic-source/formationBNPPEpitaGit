package com.epita.coursEpitaExerciceSalaireSpring.service;

import com.epita.coursEpitaExerciceSalaireSpring.entite.Collaborateur;
import com.epita.coursEpitaExerciceSalaireSpring.entite.Personne;

public interface IServicePersonne {

	void createPersonne(Personne personne);

	void createCollaborateur(Collaborateur collaborateur);
}
