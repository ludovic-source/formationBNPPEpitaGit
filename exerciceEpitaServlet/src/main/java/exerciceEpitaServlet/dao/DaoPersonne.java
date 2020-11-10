package exerciceEpitaServlet.dao;

import exerciceEpitaServlet.entite.Personne;

public interface DaoPersonne {
	
	void createPersonne(Personne personne);
	Personne getPersonne(long id);

}
