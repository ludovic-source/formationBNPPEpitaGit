package exerciceEpitaDaoPG.dao;

import java.util.List;

import exerciceEpitaDaoPG.entite.Categorie;
import exerciceEpitaDaoPG.entite.Produit;

public interface CategorieDao {
	
	public void createCategorie(Categorie categorie);
	
	public void updateCategorie(Categorie categorie);
	
	public Categorie lireCategorie(long id);
	
	public List<Produit> getListeProduits(long id);

}
