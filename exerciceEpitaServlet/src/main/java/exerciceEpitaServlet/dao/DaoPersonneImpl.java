package exerciceEpitaServlet.dao;

import org.hibernate.Session;

import exerciceEpitaServlet.entite.Personne;

public class DaoPersonneImpl implements DaoPersonne {

	@Override
	public void createPersonne(Personne personne) {
		
		Session session = DaoFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(personne);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("ça marche !");
	}

	@Override
	public Personne getPersonne(long id) {
		
		Session session = DaoFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		Personne personne = session.find(Personne.class, id);
		session.close();
		
		return personne;
		
		
	}

}
