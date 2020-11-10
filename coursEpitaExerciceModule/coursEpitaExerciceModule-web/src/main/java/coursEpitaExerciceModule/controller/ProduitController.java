package coursEpitaExerciceModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coursEpitaExerciceModule.entite.Produit;
import coursEpitaExerciceModule.service.IProduitService;

@RestController
@RequestMapping("/produit")
public class ProduitController {
	
	@Autowired
	IProduitService produitService;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public void createProduit(@RequestBody Produit produit) {
		System.out.println("controller OK");
		if (produit != null) {			
			produitService.createProduit(produit);
		}		
	}

}
