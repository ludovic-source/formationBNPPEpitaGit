package com.algorithmes.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Texte {
	
	private final String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 
	
	private HashMap<String, Float> mapFrequenceLettres = new HashMap<>();
	private String tabTriLettresParFrequence[] = new String[26];
	
	private void initierMapFrequenceLettres() {
		
		// la liste est initiée dans l'ordre suivant : de la plus frequente à la moins frequente
		mapFrequenceLettres.put("E", 15.87f);
		tabTriLettresParFrequence[0] = "E";		
		mapFrequenceLettres.put("A", 9.42f);
		tabTriLettresParFrequence[1] = "A";
		mapFrequenceLettres.put("I", 8.41f);
		tabTriLettresParFrequence[2] = "I";
		mapFrequenceLettres.put("S", 7.90f);
		tabTriLettresParFrequence[3] = "S";
		mapFrequenceLettres.put("T", 7.26f);
		tabTriLettresParFrequence[4] = "T";
		mapFrequenceLettres.put("N", 7.15f);
		tabTriLettresParFrequence[5] = "N";
		mapFrequenceLettres.put("R", 6.46f);
		tabTriLettresParFrequence[6] = "R";
		mapFrequenceLettres.put("U", 6.24f);
		tabTriLettresParFrequence[7] = "U";
		mapFrequenceLettres.put("L", 5.34f);
		tabTriLettresParFrequence[8] = "L";
		mapFrequenceLettres.put("O", 5.14f);
		tabTriLettresParFrequence[9] = "O";
		mapFrequenceLettres.put("D", 3.39f);
		tabTriLettresParFrequence[10] = "D";
		mapFrequenceLettres.put("M", 3.24f);
		tabTriLettresParFrequence[11] = "M";
		mapFrequenceLettres.put("P", 2.86f);
		tabTriLettresParFrequence[12] = "P";
		mapFrequenceLettres.put("C", 2.64f);
		tabTriLettresParFrequence[13] = "C";
		mapFrequenceLettres.put("V", 2.15f);
		tabTriLettresParFrequence[14] = "V";
		mapFrequenceLettres.put("Q", 1.06f);
		tabTriLettresParFrequence[15] = "Q";
		mapFrequenceLettres.put("G", 1.04f);
		tabTriLettresParFrequence[16] = "G";
		mapFrequenceLettres.put("B", 1.02f);
		tabTriLettresParFrequence[17] = "B";
		mapFrequenceLettres.put("F", 0.95f);
		tabTriLettresParFrequence[18] = "F";
		mapFrequenceLettres.put("J", 0.89f);
		tabTriLettresParFrequence[19] = "J";
		mapFrequenceLettres.put("H", 0.77f);
		tabTriLettresParFrequence[20] = "H";
		mapFrequenceLettres.put("Z", 0.32f);
		tabTriLettresParFrequence[21] = "Z";
		mapFrequenceLettres.put("X", 0.30f);
		tabTriLettresParFrequence[22] = "X";
		mapFrequenceLettres.put("Y", 0.24f);
		tabTriLettresParFrequence[23] = "Y";
		mapFrequenceLettres.put("K", 0.00f);
		tabTriLettresParFrequence[24] = "K";
		mapFrequenceLettres.put("W", 0.00f);
		tabTriLettresParFrequence[25] = "W";
		
	}
	
	public String chiffrer(String texteAChiffrer, int decalage) {		

		String texteChiffre = "";
		HashMap<String, String> alphabetSubstition = new HashMap<>();
		
		// initialisation de la map de correspondance entre la lettre d'origine (clé) et la lettre de substitution (valeur)
		for (int i = 0; i < 26; i++) {
			if (i+decalage < 26) {				
				alphabetSubstition.put(alphabet[i], alphabet[i+decalage]);
			}
			else {
				alphabetSubstition.put(alphabet[i], alphabet[i+decalage-26]);				
			}
		}	
		
		// passer toutes les lettres avec accent sans accent
		int longueurTexte = texteAChiffrer.length();
		for (int i = 0; i < longueurTexte - 1; i++) {
			if (texteAChiffrer.substring(i, i+1).equals("à")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "a" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("é")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "e" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("è")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "e" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("ê")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "e" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("ù")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "u" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("ô")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "o" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("î")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "i" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("ï")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "i" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("â")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "a" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("û")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "u" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("À")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "A" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("Ê")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "E" + texteAChiffrer.substring(i+1);				
			}
			if (texteAChiffrer.substring(i, i+1).equals("ç")) {
				texteAChiffrer = texteAChiffrer.substring(0, i) + "c" + texteAChiffrer.substring(i+1);				
			}
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("à")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "a";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("é")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "e";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("è")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "e";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("ê")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "e";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("ù")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "u";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("ô")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "o";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("î")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "i";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("ï")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "i";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("â")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "a";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("û")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "u";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("À")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "A";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("Ê")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "E";				
		}
		if (texteAChiffrer.substring(longueurTexte-1).equals("ç")) {
			texteAChiffrer = texteAChiffrer.substring(0, longueurTexte-1) + "c";				
		}
		
		// chiffrement de chacun des caractères du texte à chiffrer
		texteAChiffrer = texteAChiffrer.toUpperCase();
		longueurTexte = texteAChiffrer.length();
		for (int i = 0; i < longueurTexte - 1; i++) {
			if (alphabetSubstition.containsValue(texteAChiffrer.substring(i, i+1))) {				
				texteChiffre = texteChiffre + alphabetSubstition.get(texteAChiffrer.substring(i, i+1));
			}
			else {
				texteChiffre = texteChiffre + texteAChiffrer.substring(i, i+1);
			}
		}
		
		if (alphabetSubstition.containsValue(texteAChiffrer.substring(longueurTexte - 1))) {				
			texteChiffre = texteChiffre + alphabetSubstition.get(texteAChiffrer.substring(longueurTexte - 1));
		}
		else {
			texteChiffre = texteChiffre + texteAChiffrer.substring(longueurTexte - 1);
		}
		
		return texteChiffre;
		
	}
	
	public String decrypter(String texteADecrypter) {
		
		String texteDecrypte = "";
		
		// methode de base en utilisant uniquement la fréquence des lettres
		// les lettres qui n'apparaissent pas seront mises à blanc
		
		// 1ere étape : récupérer la frequence d'apparition des lettres
		HashMap<String, Float> mapFrequenceLettresTexteChiffre = new HashMap<>();
		mapFrequenceLettresTexteChiffre = calculerFrequenceLettres(texteADecrypter);
		
		// 2ème étape : trier la table des frequences		
		String tabTrieeLettresChiffree[][] = new String[26][2];
		Set<String> setLettresChiffrees = mapFrequenceLettresTexteChiffre.keySet();			
		int i = 0;
		for (String lettreChiffree : setLettresChiffrees) {
			tabTrieeLettresChiffree[i][0] = lettreChiffree;
			tabTrieeLettresChiffree[i][1] = "" + mapFrequenceLettresTexteChiffre.get(lettreChiffree);
			i++;
		}
		for (int j = 0; j < 25; j++) {
			for (int k = 0; k < 25; k++) {				
				if (Float.parseFloat(tabTrieeLettresChiffree[k][1]) < Float.parseFloat(tabTrieeLettresChiffree[k+1][1])) {
					String frequence = tabTrieeLettresChiffree[k][1];
					String lettre = tabTrieeLettresChiffree[k][0];
					tabTrieeLettresChiffree[k][0] = tabTrieeLettresChiffree[k+1][0];
					tabTrieeLettresChiffree[k][1] = tabTrieeLettresChiffree[k+1][1];
					tabTrieeLettresChiffree[k+1][0] = lettre;
					tabTrieeLettresChiffree[k+1][1] = frequence;
				}
			}
		}
		
		// 3ème étape : mettre en place une map pour représenter un alphabet de décryptage
		// clé = lettre du texte chiffré / valeur = lettre décryptée (qui peut être " " si aucune proposition)
		// dans cette étape, associer la lettre la plus fréquente (index 0) à la lettre E
		// et mettre les 7 lettres de l'index 1 à 7 dans un tableau pour être traitées dans l'étape suivante
		// ce tableau à 4 colonnes : colonne 0 (lettre chiffree), colonne 1 (frequence), colonne 2 (type : C ou V), colonne 3 (nbre de doubles)
		HashMap<String, String> alphabetDecrypte = new HashMap<>();
		String tabLettresFrequentes[][] = new String [7][4];
		for (int j = 0; j < 26; j++) {
			if (j == 0) {
				alphabetDecrypte.put(tabTrieeLettresChiffree[j][0], tabTriLettresParFrequence[j]);
			}
			else {
				alphabetDecrypte.put(tabTrieeLettresChiffree[j][0], "x");
			}
			if (j > 0 && j < 8 ) {
				tabLettresFrequentes[j-1][0] = tabTrieeLettresChiffree[j][0];
				tabLettresFrequentes[j-1][1] = tabTriLettresParFrequence[j];
				tabLettresFrequentes[j-1][2] = " ";
				tabLettresFrequentes[j-1][3] = "0";
			}
		}
		
		// 4ème étape : séparer les voyelles des consonnes du mini tableau
		// type = 'C' pour consonnes, et "V" pour voyelles
		// principe : une voyelle n'est jamais doublée dans le texte, contrairement aux consonnes
		
		// ------- COMMENCER PAR COMPTER LE NOMBRE DE LETTRES DOUBLEES AVEC LE NOMBRE DE FOIS QU'ELLES SONT DOUBLEES
		int longueurTexte = texteADecrypter.length();
		for (int j = 0; j < longueurTexte - 2; j++) {
			for (int k = 0; k < 7; k++) {
				if (texteADecrypter.substring(j, j+1).equals(tabLettresFrequentes[k][0])) {
					if (texteADecrypter.substring(j+1, j+2).equals(tabLettresFrequentes[k][0])) {
						int nbreDouble = Integer.parseInt(tabLettresFrequentes[k][3]) + 1;   
						tabLettresFrequentes[k][3] = "" + nbreDouble;
					}	
				}	
			}			
		}
		for (int k = 0; k < 7; k++) {
			if (texteADecrypter.substring(longueurTexte - 2, longueurTexte-1).equals(tabLettresFrequentes[k][0])) {
				if (texteADecrypter.substring(longueurTexte - 1).equals(tabLettresFrequentes[k][0])) {
					int nbreDouble = Integer.parseInt(tabLettresFrequentes[k][3]) + 1;
					tabLettresFrequentes[k][3] = "" + nbreDouble;
				}	
			}	
		}
		
		// ------- DETERMINER SI LA LETTRE EST UNE VOYELLE OU UNE CONSONNE PAR RAPPORT AU NOMBRE DE FOIS QU'ELLES SONT DOUBLEES DANS LE TEXTE
		for (int j = 0; j < 7; j++) {
			if (tabLettresFrequentes[j][3] == "0") {
				tabLettresFrequentes[j][2] = "V";
			}
			else {
				tabLettresFrequentes[j][2] = "C";
			}
			System.out.println("lettre chiffrée : " + tabLettresFrequentes[j][0] + " --> Type : " + tabLettresFrequentes[j][2]
					+ " --> nbre de doubles : " + tabLettresFrequentes[j][3]);		
			
		}
		
		// ------ DETERMINER LA CONSONNE LE PLUS SOUVENT DOUBLEE : c'est à dire recherche de la lettre S décryptée
		int plusGrandNbreDoubles = 0;
		String lettrePlusDeDoubles = "";
		for (int j = 0; j < 7; j++) {
			if (tabLettresFrequentes[j][2] == "C") {
				if (Integer.parseInt(tabLettresFrequentes[j][3]) > plusGrandNbreDoubles) {
					plusGrandNbreDoubles = Integer.parseInt(tabLettresFrequentes[j][3]);
					lettrePlusDeDoubles = tabLettresFrequentes[j][0];
				}
			}
		}
		System.out.println("lettre plus de doubles : " + lettrePlusDeDoubles);
		alphabetDecrypte.put(lettrePlusDeDoubles, "S");
		
		// 5ème étape : trouver la lettre A
		// utiliser les voyelles trouvées dans le tableau précédent, et exclure celle qui ne se trouve jamais à côté d'un E
		// ------ COMPTER LE NOMBRE DE VOYELLES
		int nbreVoyelles = 0;
		for (int k = 0; k < 7; k++) {
			if (tabLettresFrequentes[k][2] == "V") {
				nbreVoyelles ++;
			}
		}	
		
		// ------ CREER UN TABLEAU AVEC UNIQUEMENT LES VOYELLES FREQUENTES
		String tabVoyellesSelect[][] = new String[nbreVoyelles][2];
		int indexTabVoyellesSelect = 0;
		for (int k = 0; k < 7; k++) {
			if (tabLettresFrequentes[k][2] == "V") {
				tabVoyellesSelect[indexTabVoyellesSelect][0] = tabLettresFrequentes[k][0];
				tabVoyellesSelect[indexTabVoyellesSelect][1] = "0";
				indexTabVoyellesSelect++;
			}
		}	
		
		// ------ TROUVER LA VOYELLE QUI NE SE TROUVE JAMAIS A COTE D'UN E (correspondance chiffrée du E)
		// ------ ko signifie que la voyelle se trouve a côté d'un E
		longueurTexte = texteADecrypter.length();
		System.out.println("recherche proximite de : " + tabTrieeLettresChiffree[0][0]);
		for (int j = 0; j < longueurTexte - 2; j++) {
			if (texteADecrypter.substring(j, j+1).equals(tabTrieeLettresChiffree[0][0])) {
				for (int k = 0; k < nbreVoyelles; k++) {
					if (texteADecrypter.substring(j-1, j).equals(tabVoyellesSelect[k][0])) {
						int entierConversion = Integer.parseInt(tabVoyellesSelect[k][1]);
						entierConversion ++;
						tabVoyellesSelect[k][1] = "" + entierConversion;
					}
					if (tabVoyellesSelect[k][0].equals(texteADecrypter.substring(j+1, j+2))) {
						int entierConversion = Integer.parseInt(tabVoyellesSelect[k][1]);
						entierConversion ++;
						tabVoyellesSelect[k][1] = "" + entierConversion;
					}
				}	
			}
		}
		for (int k = 0; k < nbreVoyelles; k++) {
			if (texteADecrypter.substring(longueurTexte-2, longueurTexte-1).equals(tabVoyellesSelect[k][0])) {
				int entierConversion = Integer.parseInt(tabVoyellesSelect[k][1]);
				entierConversion ++;
				tabVoyellesSelect[k][1] = "" + entierConversion;
			}
			if (texteADecrypter.substring(longueurTexte-1).equals(tabVoyellesSelect[k][0])) {
				int entierConversion = Integer.parseInt(tabVoyellesSelect[k][1]);
				entierConversion ++;
				tabVoyellesSelect[k][1] = "" + entierConversion;
			}
		}
		for (int k = 0; k < nbreVoyelles; k++) {
			if (texteADecrypter.substring(longueurTexte-1).equals(tabVoyellesSelect[k][0])) {
				int entierConversion = Integer.parseInt(tabVoyellesSelect[k][1]);
				entierConversion ++;
				tabVoyellesSelect[k][1] = "" + entierConversion;
			}
		}		
		
		// ------ TROUVER LA VOYELLE QUI NE SE TROUVE JAMAIS A COTE DES AUTRES VOYELLES DU TABLEAU DE VOYELLES		
		//for (int k = 0; k < nbreVoyelles; k++) {
		//	for (int j = 0; j < longueurTexte - 2; j++) {
		//		if (texteADecrypter.substring(j, j+1).equals(tabVoyellesSelect[k][0])) {
		//			for (int x = 0; x < nbreVoyelles; x++) {
		//				if (tabVoyellesSelect[x][0].equals(texteADecrypter.substring(j-1, j))) {
		//					int entierConversion = Integer.parseInt(tabVoyellesSelect[k][1]);
		//					entierConversion ++;
		//					tabVoyellesSelect[x][1] = "" + entierConversion;
		//				}
		//				if (tabVoyellesSelect[x][0].equals(texteADecrypter.substring(j+1, j+2))) {
		//					int entierConversion = Integer.parseInt(tabVoyellesSelect[k][1]);
		//					entierConversion ++;
		//					tabVoyellesSelect[x][1] = "" + entierConversion;
		//				}
		//			}	
		//		}
		//	}			
		//}	
		
		// --------- LA VOYELLE QUI EST A LE MOINS DE PROXIMITE AVEC LA LETTRE "E" CORRESPOND A LA LETTRE "A"
		int plusPetit = Integer.parseInt(tabVoyellesSelect[0][1]);
		String voyelleA = tabVoyellesSelect[0][0];
		for (int k = 0; k < nbreVoyelles; k++) {			
				int entierConversion = Integer.parseInt(tabVoyellesSelect[k][1]);
				if (entierConversion < plusPetit) {
					plusPetit = Integer.parseInt(tabVoyellesSelect[k][1]);
					voyelleA = tabVoyellesSelect[k][0];	
			}
		}
		alphabetDecrypte.put(voyelleA, "A");
		
		// dernière étape : décrypter
		longueurTexte = texteADecrypter.length();
		for (int j = 0; j < longueurTexte - 1; j++) {
			if (alphabetDecrypte.containsKey(texteADecrypter.substring(j, j+1))) {
				texteDecrypte += alphabetDecrypte.get(texteADecrypter.substring(j, j+1));
			}
			else {
				texteDecrypte += texteADecrypter.substring(j, j+1);
			}
		}
		if (alphabetDecrypte.containsKey(texteADecrypter.substring(longueurTexte-1))) {
			texteDecrypte += alphabetDecrypte.get(texteADecrypter.substring(longueurTexte-1));
		}
		else {
			texteDecrypte += texteADecrypter.substring(longueurTexte-1);		
		}
				
		return texteDecrypte;
		
	}
	
	public HashMap<String, Float> calculerFrequenceLettres(String texteChiffre) {
		
		HashMap<String, Integer> mapNbreLettresTexteChiffre = new HashMap<>();
		HashMap<String, Float> mapFrequenceLettresTexteChiffre = new HashMap<>();
		initierMapFrequenceLettres();
		Set<String> setLettresSet = mapFrequenceLettres.keySet();
		int longueurTexteChiffre = texteChiffre.length();
		int nbreDeLettresTexteChiffre = 0;
		
		for (String chaqueLettre : setLettresSet) {
			mapNbreLettresTexteChiffre.put(chaqueLettre, 0);			
			for (int i=0; i < longueurTexteChiffre - 1; i++) {		
				if (texteChiffre.substring(i, i+1).equals(chaqueLettre)) {
					mapNbreLettresTexteChiffre.replace(chaqueLettre, mapNbreLettresTexteChiffre.get(chaqueLettre)+1);
					nbreDeLettresTexteChiffre++;
				}
			}		
			if (texteChiffre.substring(longueurTexteChiffre-1).equals(chaqueLettre)) {
				mapNbreLettresTexteChiffre.replace(chaqueLettre, mapNbreLettresTexteChiffre.get(chaqueLettre)+1);
				nbreDeLettresTexteChiffre++;
			}			
		}		
		
		for (String chaqueLettre : setLettresSet) {
			int nbreDePresence = mapNbreLettresTexteChiffre.get(chaqueLettre);
			float frequencePresence = (float) nbreDePresence * 100 / nbreDeLettresTexteChiffre;
			mapFrequenceLettresTexteChiffre.put(chaqueLettre, frequencePresence);			
		}	
		
		return mapFrequenceLettresTexteChiffre;
		
	}
	
	public String[][] calculerLettresAdjacentes(String texte) {
		
		// --- INITIALISER LE TABLEAU
		// ------- sur la ligne d'index 0, on retrouve chaque lettre de A à Z
		// ------- sur la colonne d'index 0, on retrouve chaque lettre de A à Z
		// ------- les autres index indiquera le nbre de fois où la lettre se trouve avant
		String tabLettresAdjacentes[][] = new String[27][27];
		tabLettresAdjacentes[0][1] = "A"; tabLettresAdjacentes[1][0] = "A";
		tabLettresAdjacentes[0][2] = "B"; tabLettresAdjacentes[2][0] = "B";
		tabLettresAdjacentes[0][3] = "C"; tabLettresAdjacentes[3][0] = "C";
		tabLettresAdjacentes[0][4] = "D"; tabLettresAdjacentes[4][0] = "D";
		tabLettresAdjacentes[0][5] = "E"; tabLettresAdjacentes[5][0] = "E";
		tabLettresAdjacentes[0][6] = "F"; tabLettresAdjacentes[6][0] = "F";
		tabLettresAdjacentes[0][7] = "G"; tabLettresAdjacentes[7][0] = "G";
		tabLettresAdjacentes[0][8] = "H"; tabLettresAdjacentes[8][0] = "H";
		tabLettresAdjacentes[0][9] = "I"; tabLettresAdjacentes[9][0] = "I";
		tabLettresAdjacentes[0][10] = "J"; tabLettresAdjacentes[10][0] = "J";
		tabLettresAdjacentes[0][11] = "K"; tabLettresAdjacentes[11][0] = "K";
		tabLettresAdjacentes[0][12] = "L"; tabLettresAdjacentes[12][0] = "L";
		tabLettresAdjacentes[0][13] = "M"; tabLettresAdjacentes[13][0] = "M";
		tabLettresAdjacentes[0][14] = "N"; tabLettresAdjacentes[14][0] = "N";
		tabLettresAdjacentes[0][15] = "O"; tabLettresAdjacentes[15][0] = "O";
		tabLettresAdjacentes[0][16] = "P"; tabLettresAdjacentes[16][0] = "P";
		tabLettresAdjacentes[0][17] = "Q"; tabLettresAdjacentes[17][0] = "Q";
		tabLettresAdjacentes[0][18] = "R"; tabLettresAdjacentes[18][0] = "R";
		tabLettresAdjacentes[0][19] = "S"; tabLettresAdjacentes[19][0] = "S";
		tabLettresAdjacentes[0][20] = "T"; tabLettresAdjacentes[20][0] = "T";
		tabLettresAdjacentes[0][21] = "U"; tabLettresAdjacentes[21][0] = "U";
		tabLettresAdjacentes[0][22] = "V"; tabLettresAdjacentes[22][0] = "V";
		tabLettresAdjacentes[0][23] = "W"; tabLettresAdjacentes[23][0] = "W";
		tabLettresAdjacentes[0][24] = "X"; tabLettresAdjacentes[24][0] = "X";
		tabLettresAdjacentes[0][25] = "Y"; tabLettresAdjacentes[25][0] = "Y";
		tabLettresAdjacentes[0][26] = "Z"; tabLettresAdjacentes[26][0] = "Z";
		for (int i = 1; i < 27; i++) {
			for (int j = 1; j < 27; j++) {
				tabLettresAdjacentes[i][j] = "0";
			}
		}
		
		// --- COMPTER LE NOMBRE DE FOIS QUE CHAQUE LETTRE EST ADJACENTE A UNE AUTRE
		// --------- pour chaque lettre de la colonne d'index 0, on compte le nbre de fois qu'elle se trouve devant
		// ---------      une lettre de la ligne d'index 0
		int longueurTexte = texte.length();
		boolean colonneTrouvee = false;
		boolean ligneTrouvee = false;
		for (int i = 1; i < longueurTexte -1; i++) {
			ligneTrouvee = false;
			for (int ligne = 1; ligne < 27 && !ligneTrouvee; ligne++) {
				if (texte.substring(i, i+1).equals(tabLettresAdjacentes[ligne][0])) {
					colonneTrouvee = false;
					for (int colonne = 1; colonne < 27 && !colonneTrouvee; colonne++) {
						if (texte.substring(i-1, i).equals(tabLettresAdjacentes[0][colonne])) {
							int entierConversion = Integer.parseInt(tabLettresAdjacentes[ligne][colonne]);
							entierConversion = entierConversion + 1;
							tabLettresAdjacentes[ligne][colonne] = "" + entierConversion;
							colonneTrouvee = true;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < 27; i++) {
			for (int j = 0; j < 27; j++) {
				System.out.print(" - " + tabLettresAdjacentes[i][j]);
			}
			System.out.println("");
		}
		
		return tabLettresAdjacentes;
	}
	
	
}
