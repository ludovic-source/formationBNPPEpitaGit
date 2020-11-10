package exerciceEpitaClassSalaire.entite;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AdressePostale {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_adresse;
	private String nom_rue;
	private long code_postal;	
	
	@OneToMany(mappedBy = "adresse_postale")
	private Set<Personne> personnes;	

	@OneToMany(mappedBy = "adresse_postale_entreprise")
	private Set<Entreprise> entreprises;

	// Accesseurs
		
	public Set<Personne> getPersonnes() {
		return personnes;
	}
	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}
	public long getId_adresse() {
		return id_adresse;
	}
	public void setId_adresse(long id_adresse) {
		this.id_adresse = id_adresse;
	}
	public String getNom_rue() {
		return nom_rue;
	}
	public void setNom_rue(String nom_rue) {
		this.nom_rue = nom_rue;
	}
	public long getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(long code_postal) {
		this.code_postal = code_postal;
	}
	public Set<Entreprise> getEntreprises() {
		return entreprises;
	}
	public void setEntreprises(Set<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}
	
}
