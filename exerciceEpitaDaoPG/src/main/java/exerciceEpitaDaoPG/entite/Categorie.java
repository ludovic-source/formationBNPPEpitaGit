package exerciceEpitaDaoPG.entite;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

@Entity
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
	// d�claration pour faire une jointure avec la table cat�gorie
	// --- on consid�re dans l'exercice qu'un produit peut-�tre dans +ieurs cat�gories, 
	// --- et une categorie peut avoir +ieurs produits
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Produit> listeProduits;
	
	// Accesseurs
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<Produit> getListeProduits() {
		System.out.println("lecture des produits de la cat�gorie");
		System.out.println("liste produits : " + listeProduits);
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	
}
