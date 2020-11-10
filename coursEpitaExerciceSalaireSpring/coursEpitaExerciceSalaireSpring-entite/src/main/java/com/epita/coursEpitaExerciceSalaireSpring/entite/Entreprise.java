package com.epita.coursEpitaExerciceSalaireSpring.entite;

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
	private Long id_entreprise;
	private String nom_entreprise;
	
	// Même problème que pour les adresses
	// @OneToMany(mappedBy = "entreprise")
	// private Set<Personne> personnes_entreprise;
	
	@ManyToOne
	private Adresse adresse_entreprise;

	// ACCESSEURS
	public Long getId_entreprise() {
		return id_entreprise;
	}

	public void setId_entreprise(Long id_entreprise) {
		this.id_entreprise = id_entreprise;
	}

	public String getNom_entreprise() {
		return nom_entreprise;
	}

	public void setNom_entreprise(String nom_entreprise) {
		this.nom_entreprise = nom_entreprise;
	}
	/*
	public Set<Personne> getPersonnes_entreprise() {
		return personnes_entreprise;
	}

	public void setPersonnes_entreprise(Set<Personne> personnes_entreprise) {
		this.personnes_entreprise = personnes_entreprise;
	}
	*/
	public Adresse getAdresse_entreprise() {
		return adresse_entreprise;
	}

	public void setAdresse_entreprise(Adresse adresse_entreprise) {
		this.adresse_entreprise = adresse_entreprise;
	}

	
	
}
