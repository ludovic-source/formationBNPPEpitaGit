package exerciceEpitaDAOAnimaux.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import exerciceEpitaDAOAnimaux.entite.Mammifere;

public class MammifereDAOImpl implements MammifereDAO {

	@Override
	public void createMammifere(Mammifere mammifere) {
		
		SessionFactory sessionFactory = DAOFactory.buildSessionFactory(Mammifere.class);		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(mammifere);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("ça marche !");
		
	}

	@Override
	public Mammifere lireMammifere(int id) {
		
		SessionFactory sessionFactory = DAOFactory.buildSessionFactory(Mammifere.class);		
		Session session = sessionFactory.openSession();
		
		Mammifere mammifere = session.find(Mammifere.class, id);
		session.close();
		return mammifere;
		
	}
	
	
	

}
