package exerciceEpitaClassSalaire.dao;

import java.util.List;

import exerciceEpitaClassSalaire.entite.Entreprise;

public interface EntrepriseDAO {
	
	void createEntreprise(Entreprise entreprise);
	
	Entreprise getEntreprise(long id);
	
	List<Entreprise> getAllEntreprises();

}
