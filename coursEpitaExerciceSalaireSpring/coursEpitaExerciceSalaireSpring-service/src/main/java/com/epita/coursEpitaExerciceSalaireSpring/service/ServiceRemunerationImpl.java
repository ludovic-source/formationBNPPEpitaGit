package com.epita.coursEpitaExerciceSalaireSpring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epita.coursEpitaExerciceSalaireSpring.dao.IDaoRemunerationAnnuelle;
import com.epita.coursEpitaExerciceSalaireSpring.entite.RemunerationAnnuelle;

@Service
public class ServiceRemunerationImpl implements IServiceRemunerationAnnuelle {

	@Autowired
	IDaoRemunerationAnnuelle daoRemuneration;
	
	@Override
	public void createRemunerationAnnuelle(RemunerationAnnuelle remunerationAnnuelle) {
		daoRemuneration.save(remunerationAnnuelle);
		
	}

	@Override
	public Optional<RemunerationAnnuelle> getRemunerationById(Long id) {		
		return daoRemuneration.findById(id);
	}

}
