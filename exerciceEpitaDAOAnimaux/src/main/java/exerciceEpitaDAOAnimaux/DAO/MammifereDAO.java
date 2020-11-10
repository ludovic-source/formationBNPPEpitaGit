package exerciceEpitaDAOAnimaux.DAO;

import exerciceEpitaDAOAnimaux.entite.Mammifere;

public interface MammifereDAO {
	
	public void createMammifere(Mammifere mammifere);
	
	public Mammifere lireMammifere(int id);

}
