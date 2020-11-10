package exerciceEpitaClassSalaire.dao;

import java.util.List;

import org.hibernate.Session;

import exerciceEpitaClassSalaire.entite.Personne;


public class PersonneDaoImpl implements PersonneDao {

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
		
		Personne personne= session.find(Personne.class, id);
		session.close();
		
		return personne;
	}

	@Override
	public List<Personne> getAllPersonnes() {
		
		Session session = DaoFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		// ce n'est plus du SQL mais du JPQL
		List<Personne> listePersonnes = session.createQuery("from Personne").list();
		session.getTransaction().commit();
		session.close();
		
		return listePersonnes;
	}

}
