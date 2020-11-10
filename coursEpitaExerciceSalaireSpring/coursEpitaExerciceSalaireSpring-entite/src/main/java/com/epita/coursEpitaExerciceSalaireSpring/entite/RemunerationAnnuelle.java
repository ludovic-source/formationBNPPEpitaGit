package com.epita.coursEpitaExerciceSalaireSpring.entite;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RemunerationAnnuelle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_remuneration_annuelle;
	private String annee;
	private BigDecimal montant;
	
	@ManyToOne
	private Collaborateur collaborateur;

	// ACCESSEURS
	public Long getId_remunation_annuelle() {
		return id_remuneration_annuelle;
	}

	public void setId_remunation_annuelle(Long id_remuneration_annuelle) {
		this.id_remuneration_annuelle = id_remuneration_annuelle;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}
	
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}
	
	
	
}
