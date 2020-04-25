package com.octest.beans;

public class Utilisateur {
	
	private String nom;
	private String prenom;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws BeanException {
		 if (nom.length() > 20) {
	            throw new BeanException("Le nom est trop grand ! (20 caractères maximum)");
	        }
	        else {
	            this.nom = nom; 
	        }
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}	

}
