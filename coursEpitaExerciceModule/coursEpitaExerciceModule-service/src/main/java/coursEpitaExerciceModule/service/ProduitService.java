package coursEpitaExerciceModule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coursEpitaExerciceModule.dao.IDaoProduit;
import coursEpitaExerciceModule.entite.Produit;

@Service
public class ProduitService implements IProduitService {

	@Autowired
	IDaoProduit daoProduit;
	
	public void createProduit(Produit produit) {
		daoProduit.save(produit);
		
	}
	

}
