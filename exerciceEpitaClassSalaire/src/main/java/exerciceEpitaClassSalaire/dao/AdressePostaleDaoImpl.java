package exerciceEpitaClassSalaire.dao;

import java.util.List;

import org.hibernate.Session;

import exerciceEpitaClassSalaire.entite.AdressePostale;

public class AdressePostaleDaoImpl implements AdressePostaleDao {

	@Override
	public void createAdressePostale(AdressePostale adressePostale) {
		
		Session session = DaoFactory.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(adressePostale);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("ça marche !");
		
	}

	@Override
	public AdressePostale getAdressePostale(long id) {
		
		Session session = DaoFactory.getSessionFactory().openSession();
		
		AdressePostale adressePostale= session.find(AdressePostale.class, id);
		session.close();
		
		return adressePostale;
	}

	@Override
	public List<AdressePostale> getAllAdressesPostales() {
	
		Session session = DaoFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		// ce n'est plus du SQL mais du JPQL
		List<AdressePostale> listeAdressePostales = session.createQuery("from AdressePostale").list();
		session.getTransaction().commit();
		session.close();
		
		return listeAdressePostales;
	}

}
