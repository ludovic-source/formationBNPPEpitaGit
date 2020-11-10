package exerciceEpitaDAO.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import exerciceEpitaDAO.entite.Voiture;

public class VoitureDAOImpl implements VoitureDAO {

	@Override
	public void create(Voiture voiture) {	

		SessionFactory sessionFactory = DAOFactory.buildSessionFactory(Voiture.class);		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(voiture);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("ça marche !");
		
	}

	@Override
	public Voiture findSingleVoiture(int id) {
		
		SessionFactory sessionFactory = DAOFactory.buildSessionFactory(Voiture.class);		
		Session session = sessionFactory.openSession();
		
		Voiture voiture = session.find(Voiture.class, id);
		session.close();
		return voiture;
		
	}

}
