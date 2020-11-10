package exerciceEpitaClassSalaire.entite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Personne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_personne;
	private String nom_personne;
	// private long id_adresse;
	// private long id_entreprise;
	
	@ManyToOne
	private AdressePostale adresse_postale;	

	@ManyToOne
	private Entreprise entreprise;
	
	// Accesseurs
	
	public long getId_personne() {
		return id_personne;
	}
	public void setId_personne(long id_personne) {
		this.id_personne = id_personne;
	}
	public String getNom_personne() {
		return nom_personne;
	}
	public void setNom_personne(String nom_personne) {
		this.nom_personne = nom_personne;
	}

	public AdressePostale getAdressePostale() {
		return adresse_postale;
	}
	public void setAdressePostale(AdressePostale adressePostale) {
		this.adresse_postale = adressePostale;
	}

	public AdressePostale getAdresse_postale() {
		return adresse_postale;
	}
	public void setAdresse_postale(AdressePostale adresse_postale) {
		this.adresse_postale = adresse_postale;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
}
