package com.epita.coursEpitaExerciceSalaireSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epita.coursEpitaExerciceSalaireSpring.dao.IDaoAdresse;
import com.epita.coursEpitaExerciceSalaireSpring.entite.Adresse;


@Service
public class ServiceAdresseImpl implements IServiceAdresse {

	@Autowired
	IDaoAdresse daoAdresse;
	
	@Override
	public void createAdresse(Adresse adresse) {
		daoAdresse.save(adresse);
		
	}
}
