package exerciceEpitaClassSalaire.dao;

import java.util.List;

import exerciceEpitaClassSalaire.entite.AdressePostale;

public interface AdressePostaleDao {

	void createAdressePostale(AdressePostale adressePostale);
	
	AdressePostale getAdressePostale(long id);
	
	List<AdressePostale> getAllAdressesPostales();
	
}
