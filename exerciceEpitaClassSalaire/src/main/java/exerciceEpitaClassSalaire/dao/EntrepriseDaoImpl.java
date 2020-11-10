package exerciceEpitaClassSalaire.dao;

import java.util.List;

import org.hibernate.Session;

import exerciceEpitaClassSalaire.entite.Entreprise;

public class EntrepriseDaoImpl implements EntrepriseDAO {

	@Override
	public void createEntreprise(Entreprise entreprise) {
		
		Session session = DaoFactory.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(entreprise);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("ça marche aussi pour les entreprises !");
		
	}

	@Override
	public Entreprise getEntreprise(long id) {
	
		Session session = DaoFactory.getSessionFactory().openSession();
		
		Entreprise entreprise = session.find(Entreprise.class, id);
		session.close();
		
		return entreprise;
	}

	@Override
	public List<Entreprise> getAllEntreprises() {
		
		Session session = DaoFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		// ce n'est plus du SQL mais du JPQL
		List<Entreprise> listeEntreprises = session.createQuery("from Entreprise").list();
		session.getTransaction().commit();
		session.close();
		
		return listeEntreprises;
	}
	
	

}
