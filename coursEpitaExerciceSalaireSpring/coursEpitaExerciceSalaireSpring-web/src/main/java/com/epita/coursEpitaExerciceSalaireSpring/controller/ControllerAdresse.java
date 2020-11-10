package com.epita.coursEpitaExerciceSalaireSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epita.coursEpitaExerciceSalaireSpring.entite.Adresse;
import com.epita.coursEpitaExerciceSalaireSpring.service.IServiceAdresse;


@RestController
@RequestMapping("/adresse")
public class ControllerAdresse {
	
	@Autowired
	IServiceAdresse serviceAdresse;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public void createAdresse(@RequestBody Adresse adresse) {
		if (adresse != null) {
			serviceAdresse.createAdresse(adresse);
		}	
	}

}
