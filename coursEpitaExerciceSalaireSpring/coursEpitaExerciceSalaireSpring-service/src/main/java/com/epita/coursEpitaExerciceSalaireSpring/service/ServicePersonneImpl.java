package com.epita.coursEpitaExerciceSalaireSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epita.coursEpitaExerciceSalaireSpring.dao.IDaoPersonne;
import com.epita.coursEpitaExerciceSalaireSpring.entite.Collaborateur;
import com.epita.coursEpitaExerciceSalaireSpring.entite.Personne;

@Service
public class ServicePersonneImpl implements IServicePersonne{

	@Autowired
	IDaoPersonne daoPersonne;
	
	@Override
	public void createPersonne(Personne personne) {
		daoPersonne.save(personne);
	}
	
	@Override
	public void createCollaborateur(Collaborateur collaborateur) {
		daoPersonne.save(collaborateur);
	}
}
