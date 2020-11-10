package exerciceEpitaDaoPG.main;

import java.util.List;
import exerciceEpitaDaoPG.dao.CategorieDao;
import exerciceEpitaDaoPG.dao.DaoFactory;
import exerciceEpitaDaoPG.dao.ProduitDao;
import exerciceEpitaDaoPG.entite.Categorie;
import exerciceEpitaDaoPG.entite.Produit;

public class App {

	public static void main(String[] args) {
		
		ProduitDao produitDao = DaoFactory.getProduitDao();
		CategorieDao categorieDao = DaoFactory.getCategorieDao();
		
		
		// ajout d'un produit dans la table
		Produit produitACreer = new Produit();
		produitACreer.setNom("One Piece");
		produitACreer.setDescription("Les recettes de Sanji");
		produitACreer.setCode("94352");
		produitACreer.setCodeBarre(11058979);
		produitDao.createProduit(produitACreer);		
		
				
		// lire un produit
		System.out.println("AFFICHAGE D'UN PRODUIT EN PARTICULIER");
		Produit produitLu = produitDao.lireProduit(1);
		System.out.println("Nom du produit = " + produitLu.getNom());
		System.out.println("Description du produit = " + produitLu.getDescription());
	
		// lire tous les produits dans la table
		System.out.println("AFFICHAGE DE TOUS LES PRODUITS DANS LA TABLE");
		List<Produit> listeProduits = produitDao.getAllProduits();
		for (Produit chaqueProduit : listeProduits) {
			System.out.println("Produit n° " + chaqueProduit.getId() 
						+ " --- nom : " + chaqueProduit.getNom()
						+ " --- description : " + chaqueProduit.getDescription());
		}
		
		// lire les produits dans la table qui correspondent à une clause where
		System.out.println();
		System.out.println("AFFICHAGE DES PRODUITS CORRESPONDANT A UNE CLAUSE WHERE DANS LA TABLE");
		List<Produit> listeProduitsAvecWhere = produitDao.getProduitsAvecClauseWhere(11058979);
		for (Produit chaqueProduit : listeProduitsAvecWhere) {
			System.out.println("Produit n° " + chaqueProduit.getId() 
							+ " --- nom : " + chaqueProduit.getNom()
							+ " --- description : " + chaqueProduit.getDescription());
		}		

		
		// ajouter ou mettre à jour un élément dans la table catégorie
		// --- en associant les produits trouvés ci-dessus à la categorie
		// --- il ne faut surtout pas faire d'update sans avoir récupéré la liste de tous les produits de la categorie
		// ---    sinon, les produits précédents seront effacés
		Categorie categorieACreer = new Categorie();		
		categorieACreer.setName("Manga");			
		categorieACreer.setListeProduits(listeProduitsAvecWhere);			
		categorieACreer.setId(1);		
		categorieDao.createCategorie(categorieACreer);		
	
		
		// ajout d'un produit dans une categorie existante
		Categorie categorieLue = categorieDao.lireCategorie(1);
		System.out.println("categorie lue dans APP : " + categorieLue.getName());
		//System.out.println("produits dans la categorie : " + categorieLue.getListeProduits());
		
		/*
		// lire tous les produits dans la table
		System.out.println("AFFICHAGE DE LA CATEGORIE DES PRODUITS");
		Set<Produit> listeProduits2 = produitDao.getAllProduits();
		for (Produit chaqueProduit : listeProduits2) {
			System.out.println("Produit n° " + chaqueProduit.getId() 
							+ " --- nom : " + chaqueProduit.getNom()
							+ " --- description : " + chaqueProduit.getDescription()
							+ " --- nom categorie : " + chaqueProduit.getListeCategories().get(0).getName());
		}
		*/
		
	}

}
