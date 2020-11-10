package com.epita.coursEpitaExerciceSalaireSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epita.coursEpitaExerciceSalaireSpring.dao.IDaoEntreprise;
import com.epita.coursEpitaExerciceSalaireSpring.entite.Entreprise;



@Service
public class ServiceEntrepriseImpl implements IServiceEntreprise {

	@Autowired
	IDaoEntreprise daoEntreprise;
	
	@Override
	public void createEntreprise(Entreprise entreprise) {
		daoEntreprise.save(entreprise);
	}
	
}
