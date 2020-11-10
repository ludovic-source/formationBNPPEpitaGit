package com.epita.coursEpitaExerciceSalaireSpring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epita.coursEpitaExerciceSalaireSpring.entite.RemunerationAnnuelle;
import com.epita.coursEpitaExerciceSalaireSpring.service.IServiceRemunerationAnnuelle;

@RestController
@RequestMapping("/remuneration")
public class ControllerRemuneration {
	
	@Autowired
	IServiceRemunerationAnnuelle serviceRemunerationAnnuelle;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public void createRemuneration(@RequestBody RemunerationAnnuelle remunerationAnnuelle) {
		if (remunerationAnnuelle != null) {
			serviceRemunerationAnnuelle.createRemunerationAnnuelle(remunerationAnnuelle);
		}
	} 
	
	@GetMapping("/remunerationId/{id}")
	public Optional<RemunerationAnnuelle> getRemunerationById(@PathVariable("id") Long id) {
		if (id != null) {
			System.out.println("coucou - remuneration Id : " + id);
			return serviceRemunerationAnnuelle.getRemunerationById(id);
		}
		return null;
	}

}
