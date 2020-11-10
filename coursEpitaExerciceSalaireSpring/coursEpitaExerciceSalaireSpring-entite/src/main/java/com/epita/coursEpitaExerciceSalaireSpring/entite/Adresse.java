package com.epita.coursEpitaExerciceSalaireSpring.entite;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Adresse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_adresse;
	private String rue;
	private String ville;
	
	// j'ai mis les 4 lignes ci-dessous en commentaires à cause des boucles infinies
	//    quand on veut récupérer les objets, car les objets sont tous imbriqués les uns
	//    les autres
	// Donc juste une liaison unidirectionnelle
	
	//@OneToMany(mappedBy = "adresse_personne")
	//private Set<Personne> personnes;
	
	//@OneToMany(mappedBy = "adresse_entreprise")
	//private Set<Entreprise> entreprises;

	// ACCESSEURS
	public Long getId_adresse() {
		return id_adresse;
	}

	public void setId_adresse(Long id_adresse) {
		this.id_adresse = id_adresse;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	/*
	public Set<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}
	*/
	/*
	public Set<Entreprise> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(Set<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}
	*/

	
	
}
