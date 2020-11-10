package exerciceEpitaDaoPG.dao;

import java.util.List;

import exerciceEpitaDaoPG.entite.Produit;

public interface ProduitDao {
	
	public void createProduit(Produit produit);
	
	public Produit lireProduit(long id);

	public List<Produit> getAllProduits();

	public List<Produit> getProduitsAvecClauseWhere(long codeBarre);
	
}
