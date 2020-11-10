package exerciceEpitaClassSalaire.dao;

import java.util.List;

import exerciceEpitaClassSalaire.entite.Personne;

public interface PersonneDao {
	
	void createPersonne(Personne personne);
	
	Personne getPersonne(long id);
	
	List<Personne> getAllPersonnes();

}
