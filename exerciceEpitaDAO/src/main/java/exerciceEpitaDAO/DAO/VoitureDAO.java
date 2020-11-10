package exerciceEpitaDAO.DAO;

import exerciceEpitaDAO.entite.Voiture;

public interface VoitureDAO {
	
	public void create(Voiture voiture);
	
	public Voiture findSingleVoiture(int id);


}
