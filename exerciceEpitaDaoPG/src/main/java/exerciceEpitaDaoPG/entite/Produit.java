package exerciceEpitaDaoPG.entite;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String description;
	private String code;
	private long codeBarre;
	
	// d�claration pour faire une jointure avec la table cat�gorie
	// --- on consid�re dans l'exercice qu'un produit peut-�tre dans +ieurs cat�gories, 
	// --- et une categorie peut avoir +ieurs produits
	//@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//@JoinColumn(name = "FK_produit_categorie", referencedColumnName = "id")
	// le mappedBy permet de cr�er une relation categorie vers produit
	// quand on cr�e une cat�gorie, il faut donc ajouter la liste des produits associ�s � cette cat�gorie
	@ManyToMany(mappedBy = "listeProduits")
	private List<Categorie> categories;		// SET est plus performant que liste
	
	// Accesseurs
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getCodeBarre() {
		return codeBarre;
	}
	public void setCodeBarre(long codeBarre) {
		this.codeBarre = codeBarre;
	}
	
	
	public List<Categorie> getListeCategories() {
		return categories;
	}
	public void setListeCategories(List<Categorie> listeCategories) {
		this.categories = listeCategories;
	}

	
	

}
