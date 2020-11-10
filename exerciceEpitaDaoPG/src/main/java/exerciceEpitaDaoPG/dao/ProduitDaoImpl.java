package exerciceEpitaDaoPG.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import exerciceEpitaDaoPG.entite.Produit;

public class ProduitDaoImpl implements ProduitDao {

	@Override
	public void createProduit(Produit produit) {
		
		SessionFactory sessionFactory = DaoFactory.buildSessionFactory(Produit.class);		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(produit);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("ça marche !");
	}

	@Override
	public Produit lireProduit(long id) {
		
		SessionFactory sessionFactory = DaoFactory.buildSessionFactory(Produit.class);		
		Session session = sessionFactory.openSession();
		
		Produit produit = session.find(Produit.class, id);
		session.close();
		
		return produit;
	}

	@Override
	public List<Produit> getAllProduits() {
	
		Session session = DaoFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		// ce n'est plus du SQL mais du JPQL
		List<Produit> listeProduits = session.createQuery("from Produit").list();
		session.getTransaction().commit();
		session.close();
		
		return listeProduits;
	}
	
	@Override
	public List<Produit> getProduitsAvecClauseWhere(long codeBarre) {
	
		Session session = DaoFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		//String requete = "select p from Produit p where p.codeBarre = " + codeBarre;
		// ce n'est plus du SQL mais du JPQL - c'est une requete objet
		List<Produit> listeProduits = session.createQuery("select p from Produit p where p.codeBarre = :codeBarreAttendu")
				.setParameter("codeBarreAttendu", codeBarre)
				.list();
		session.getTransaction().commit();
		session.close();
		
		return listeProduits;
	}
	

}
