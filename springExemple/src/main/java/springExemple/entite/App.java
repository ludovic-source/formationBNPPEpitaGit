package springExemple.entite;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		
		try {
			Server.createTcpServer().start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Voiture voiture = new Voiture();
		voiture.setName("Ferrari");
		
		SessionFactory sessionFactory = buildSessionFactory(Voiture.class);

		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(voiture);
		session.getTransaction().commit();
		session.close();
		
	}

	private static SessionFactory buildSessionFactory(Class clazz) {
		
		return new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(clazz)
				.buildSessionFactory();
	}

}
