package exerciceEpitaDAO.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import exerciceEpitaDAO.entite.Voiture;

public class DAOFactory {
	
	
	public static VoitureDAO getVoitureDAO() {
		return new VoitureDAOImpl();
	}
	
	public static SessionFactory buildSessionFactory(Class clazz) {
		
		return new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(clazz)
				.buildSessionFactory();
	}

}
