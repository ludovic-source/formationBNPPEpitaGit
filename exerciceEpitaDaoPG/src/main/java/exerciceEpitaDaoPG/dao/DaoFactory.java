package exerciceEpitaDaoPG.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import exerciceEpitaDaoPG.entite.Categorie;

public class DaoFactory {
	
	private static StandardServiceRegistry  registry;
	private static SessionFactory sessionFactory;
	
	public static ProduitDao getProduitDao() {
		return new ProduitDaoImpl();
	}
	
	public static CategorieDao getCategorieDao() {
		return new CategorieDaoImpl();
	}
	
	public static SessionFactory buildSessionFactory(Class clazz) {
		
		return new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(clazz)
				.buildSessionFactory();
	}
	
	// autre solution que le buildSessionFactory au-dessus
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				// create registry
				registry = new StandardServiceRegistryBuilder().configure().build();
				// create MetadataSources
				MetadataSources sources = new MetadataSources(registry);
				// create metadata
				Metadata metadata = sources.getMetadataBuilder().build();
				// create sessionfactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

}
