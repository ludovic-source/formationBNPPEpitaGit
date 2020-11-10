package exerciceEpitaClassSalaire.entite;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Entreprise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_entreprise;
	private String nom_entreprise;
	
	@ManyToOne
	private AdressePostale adresse_postale_entreprise;

	@OneToMany(mappedBy = "entreprise")
	private Set<Personne> listePersonnes;
	
	public long getId_entreprise() {
		return id_entreprise;
	}

	public void setId_entreprise(long id_entreprise) {
		this.id_entreprise = id_entreprise;
	}

	public String getNom_entreprise() {
		return nom_entreprise;
	}

	public void setNom_entreprise(String nom_entreprise) {
		this.nom_entreprise = nom_entreprise;
	}

	public AdressePostale getAdresse_postale_entreprise() {
		return adresse_postale_entreprise;
	}

	public void setAdresse_postale_entreprise(AdressePostale adresse_postale_entreprise) {
		this.adresse_postale_entreprise = adresse_postale_entreprise;
	}
	
	
	
}
