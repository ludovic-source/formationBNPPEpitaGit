package com.epita.coursEpitaExerciceSalaireSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epita.coursEpitaExerciceSalaireSpring.entite.Entreprise;
import com.epita.coursEpitaExerciceSalaireSpring.service.IServiceEntreprise;


@RestController
@RequestMapping("/entreprise")
public class ControllerEntreprise {

	@Autowired
	IServiceEntreprise serviceEntreprise;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public void createEntreprise(@RequestBody Entreprise entreprise) {
		if (entreprise != null) {		
			serviceEntreprise.createEntreprise(entreprise);
		}	
	}
	
}
