package exerciceEpitaDAOAnimaux.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DAOFactory {
	
	public static MammifereDAO getMammifereDAO() {
		return new MammifereDAOImpl();
	}

	public static SessionFactory buildSessionFactory(Class clazz) {
		
		return new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(clazz)
				.buildSessionFactory();
	}
	
}
