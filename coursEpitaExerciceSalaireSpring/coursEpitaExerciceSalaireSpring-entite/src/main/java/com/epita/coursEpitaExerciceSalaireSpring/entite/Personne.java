package com.epita.coursEpitaExerciceSalaireSpring.entite;

import java.util.Set;

import javax.persistence.CascadeType;
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
		private Long id_personne;
		private String nom_personne;
		
		@ManyToOne
		private Adresse adresse_personne;	

		@ManyToOne
		private Entreprise entreprise;
		
		// le cascade ALL permet de créer la ligne "rémunération" dans la table si
		// elle n'existe pas au préalable (mise à jour en cascade) 
		//@OneToMany(mappedBy = "collaborateur", cascade = CascadeType.ALL)
		//private Set<RemunerationAnnuelle> remunerations;

		// ACCESSEURS
		public Long getId_personne() {
			return id_personne;
		}

		public void setId_personne(Long id_personne) {
			this.id_personne = id_personne;
		}

		public String getNom_personne() {
			return nom_personne;
		}

		public void setNom_personne(String nom_personne) {
			this.nom_personne = nom_personne;
		}

		public Adresse getAdresse_personne() {
			return adresse_personne;
		}

		public void setAdresse_personne(Adresse adresse_personne) {
			this.adresse_personne = adresse_personne;
		}

		public Entreprise getEntreprise() {
			return entreprise;
		}

		public void setEntreprise(Entreprise entreprise) {
			this.entreprise = entreprise;
		}
		/*
		public Set<RemunerationAnnuelle> getRemunerations() {
			return remunerations;
		}

		public void setRemunerations(Set<RemunerationAnnuelle> remunerations) {
			this.remunerations = remunerations;
		}
		*/
		

		
		
}
