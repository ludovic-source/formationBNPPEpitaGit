package exerciceEpitaDaoPG.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import exerciceEpitaDaoPG.entite.Categorie;
import exerciceEpitaDaoPG.entite.Produit;

public class CategorieDaoImpl implements CategorieDao {

	@Override
	public void createCategorie(Categorie categorie) {
		
		Session session = DaoFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(categorie);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("ça marche aussi pour les categories !");
		
	}

	@Override
	public void updateCategorie(Categorie categorie) {

		System.out.println("coucou");
		Session session = DaoFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(categorie);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("vive l'update d'une categorie !");
		
	}

	@Override
	public Categorie lireCategorie(long id) {
		
		SessionFactory sessionFactory = DaoFactory.buildSessionFactory(Categorie.class);		
		Session session = sessionFactory.openSession();
		
		Categorie categorie = session.find(Categorie.class, id);
		session.close();		
		
		return categorie;
	}

	@Override
	public List<Produit> getListeProduits(long id) {		
		
		SessionFactory sessionFactory = DaoFactory.buildSessionFactory(Categorie.class);		
		Session session = sessionFactory.openSession();
		
		Categorie categorie = session.find(Categorie.class, id);
		session.close();	
		
		List<Produit> listeProduits = categorie.getListeProduits();
		System.out.println("lecture produits réalisée");
		System.out.println("nom du premier produit = " + listeProduits);
		return listeProduits;
		
	}
	
	
	
	

}
