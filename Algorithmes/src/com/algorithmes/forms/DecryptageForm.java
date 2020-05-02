package com.algorithmes.forms;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.algorithmes.beans.Texte;

public class DecryptageForm {
	
	private String resultat;
	private String texteAchiffrer;
	private HashMap<String, Float> mapFrequenceLettresTexteChiffre = new HashMap<>();
	private String texteDecrypte;
	private String tabLettresAdjacentes[][] = new String[27][27];

	public void decrypterForm(HttpServletRequest request) {
		
		this.texteAchiffrer = request.getParameter("texteNonChiffre");
		System.out.println(request.getParameter("decalage"));
		int decalage = Integer.parseInt(request.getParameter("decalage"));
		
		Texte texte = new Texte();
		
		// operation de chiffrement
		this.resultat = texte.chiffrer(this.texteAchiffrer, decalage);			
		
		// récupération de la fréquence d'apparition des lettres		
		mapFrequenceLettresTexteChiffre = texte.calculerFrequenceLettres(this.resultat);
		
		// récupération des stats de lettre adjacentes
		tabLettresAdjacentes = texte.calculerLettresAdjacentes(this.resultat);
		
		// operation de decryptage
		texteDecrypte = texte.decrypter(this.resultat);
		
	}

	public String[][] getTabLettresAdjacentes() {
		return tabLettresAdjacentes;
	}

	public String getResultat() {
		return resultat;
	}

	public String getTexteAchiffrer() {
		return texteAchiffrer;
	}
	
	public HashMap<String, Float> getMapFrequenceLettresTexteChiffre() {
		return mapFrequenceLettresTexteChiffre;
	}
	
	public String getTexteDecrypte() {
		return texteDecrypte;
	}

	
}
