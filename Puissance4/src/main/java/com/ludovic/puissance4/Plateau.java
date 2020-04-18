package com.ludovic.puissance4;

import java.util.ArrayList;

public class Plateau {

    final private String celluleVideHTML = "<div class=\"celluleVide\">"+
                                            "</div>";
    final private String celluleJauneHTML = "<div class=\"celluleJaune\">"+
                                            "</div>";
    final private String celluleRougeHTML = "<div class=\"celluleRouge\">"+
                                            "</div>";
    final private String debutCelluleTitreHTML = "<div class=\"celluleTitre\"><br />";
    final private String finCelluleTitreHTML = "</div>";

    final private String debutLigneCaseHTML = "<div class=\"ligne\">";
    final private String debutLigneTitreHTML = "<div class=\"ligneTitre\">";
    final private String finLigneHTML = "</div>";

    public String[][] ajouterJetonPlateau(String[][] plateauActuel, int numeroColonne, String couleurJouee) {

        // iniitaliser un tableau de 6 lignes et de 7 colonnes
        // la ligne du bas est 0 (donc le premier jeton tombe dans la 1ere ligne) - la ligne du haut est 5
        // la colonne de gauche est 0 - la colonne de droite est 6
        String[][] nouveauPlateau = new String[6][7];
        nouveauPlateau = plateauActuel;
        boolean pieceJouee = false;

        //System.out.println("plateauActuel = " + plateauActuel[4][4]);

        for (int chaqueLigne = 0; chaqueLigne < 6 && !pieceJouee; chaqueLigne++) {

            if (plateauActuel[chaqueLigne][numeroColonne] == null) {
                nouveauPlateau[chaqueLigne][numeroColonne] = couleurJouee;
                pieceJouee = true;
            }
        }
        if (!pieceJouee) {
            System.out.println("attention : le joueur a mis une pièce en trop dans la colonne");
        }

        return nouveauPlateau;

    }

    public String construirePlateauJeuHTMLVide(String[][] plateauJeu) {

        String plateauHTML = "<div id=\"plateau\">";

        for (int chaqueLigne = 5; chaqueLigne >= 0; chaqueLigne --) {
            plateauHTML += debutLigneCaseHTML;
            for (int chaqueCellule = 0; chaqueCellule < 7; chaqueCellule ++) {
                if (plateauJeu[chaqueLigne][chaqueCellule] == null) {
                    plateauHTML += celluleVideHTML;
                }
                else {
                    if (plateauJeu[chaqueLigne][chaqueCellule] == "jaune") {
                        plateauHTML += celluleJauneHTML;
                    }
                    else {
                        plateauHTML += celluleRougeHTML;
                    }
                }
            }
            plateauHTML += finLigneHTML;
        }
        plateauHTML += debutLigneTitreHTML;
        for (int chaqueCellule = 0; chaqueCellule < 7; chaqueCellule ++) {
            plateauHTML += debutCelluleTitreHTML + chaqueCellule + finCelluleTitreHTML;
        }
        plateauHTML += finLigneHTML;

        return plateauHTML;
    }

    public String actualiserPlateauJeuHTML(String[][] plateauPrecedent, int numeroColonneJouee) {

        String plateauHTML = "<div id=\"plateau\">";

        for (int chaqueLigne = 0; chaqueLigne < 6; chaqueLigne ++) {
            plateauHTML += debutLigneCaseHTML;
            for (int chaqueCellule = 0; chaqueCellule < 7; chaqueCellule ++) {
                plateauHTML += celluleVideHTML;
            }
            plateauHTML += finLigneHTML;
        }
        plateauHTML += debutLigneTitreHTML;
        for (int chaqueCellule = 0; chaqueCellule < 7; chaqueCellule ++) {
            plateauHTML += debutCelluleTitreHTML + chaqueCellule + finCelluleTitreHTML;
        }
        plateauHTML += finLigneHTML;

        return plateauHTML;

    }

    public ArrayList<String> verifierSiGagnant(String[][] plateauJeu) {

        // la fonction retourne en position 0 les valeurs possibles suivantes :
        //              jaune, rouge, finPartie (sans vainqueur), ou "" pour dire que la partie n'est pas finie
        // les positiosns 1, 2, 3, et 4 indiquent cellules gagnantes
        ArrayList<String> reponse = new ArrayList<>();

        reponse = verifierHorizontales(plateauJeu);
        if (reponse.get(0).equals("jaune") || reponse.get(0).equals("rouge")) {
            return reponse;
        }


        reponse = verifierVerticales(plateauJeu);
        if (reponse.get(0).equals("jaune") || reponse.get(0).equals("rouge")) {
            return reponse;
        }

        reponse = verifierDiagonalesMontantes(plateauJeu);
        if (reponse.get(0).equals("jaune") || reponse.get(0).equals("rouge")) {
            return reponse;
        }

        reponse = verifierDiagonalesDescendantes(plateauJeu);
        if (reponse.get(0).equals("jaune") || reponse.get(0).equals("rouge")) {
            return reponse;
        }

        reponse = verifierFinDePartie(plateauJeu);
        return reponse;

    }

    private ArrayList<String> verifierFinDePartie (String[][] plateauJeu) {

        // la fonction retourne en position 0 les valeurs possibles suivantes :
        //              "finPartie" si aucun gagnant ou "" pour dire que la partie n'est pas finie
        ArrayList<String> reponse = new ArrayList<>();
        reponse.add(0, "finPartie");

        for (int chaqueColonne = 0; chaqueColonne < 7; chaqueColonne ++) {
            if (plateauJeu[5][chaqueColonne] == null) {
                reponse.set(0, "");
                return reponse;
            }
        }
        return reponse;

    }

    private ArrayList<String> verifierHorizontales (String[][] plateauJeu) {

        // la fonction retourne en position 0 les valeurs possibles suivantes :
        //              jaune, rouge, ou "" pour dire que la partie n'est pas finie
        // les positions 1, 2, 3, et 4 indiquent cellules gagnantes
        ArrayList<String> reponse = new ArrayList<>();
        String couleurPrecedente = "";
        int nbreDeCasesMemeCouleur = 1;
        boolean gagnantTrouve = false;
        reponse.add(0, "");
        reponse.add(1,"");
        reponse.add(2,"");
        reponse.add(3,"");
        reponse.add(4,"");
        String position = "";

        for (int chaqueLigne = 0; chaqueLigne < 6 && !gagnantTrouve; chaqueLigne++) {
            nbreDeCasesMemeCouleur = 1;
            couleurPrecedente = plateauJeu[chaqueLigne][0];
            position = chaqueLigne + "0";
            reponse.set(1, position);
            for (int chaqueColonne = 1; chaqueColonne < 7 && !gagnantTrouve; chaqueColonne++) {
                if (plateauJeu[chaqueLigne][chaqueColonne] == couleurPrecedente) {
                    nbreDeCasesMemeCouleur ++;
                    if (couleurPrecedente == "jaune" || couleurPrecedente == "rouge") {
                        position = "" + chaqueLigne + chaqueColonne;
                        reponse.set(nbreDeCasesMemeCouleur, position);
                        if (nbreDeCasesMemeCouleur == 4) {
                            gagnantTrouve = true;
                        }
                    }
                }
                else {
                    nbreDeCasesMemeCouleur = 1;
                    couleurPrecedente = plateauJeu[chaqueLigne][chaqueColonne];
                }
            }
        }

        if (gagnantTrouve) {
            reponse.set(0, couleurPrecedente);
        }

        return reponse;

    }

    private ArrayList<String> verifierVerticales (String[][] plateauJeu) {

        // la fonction retourne en position 0 les valeurs possibles suivantes :
        //              jaune, rouge, ou "" pour dire que la partie n'est pas finie
        // les positions 1, 2, 3, et 4 indiquent cellules gagnantes
        ArrayList<String> reponse = new ArrayList<>();
        String couleurPrecedente = "";
        int nbreDeCasesMemeCouleur = 1;
        boolean gagnantTrouve = false;
        reponse.add(0, "");
        reponse.add(1,"");
        reponse.add(2,"");
        reponse.add(3,"");
        reponse.add(4,"");
        String position = "";

        for (int chaqueColonne = 0; chaqueColonne < 7 && !gagnantTrouve; chaqueColonne++) {
            nbreDeCasesMemeCouleur = 1;
            couleurPrecedente = plateauJeu[0][chaqueColonne];
            position = "0" + chaqueColonne;
            reponse.set(1, position);
            for (int chaqueLigne = 1; chaqueLigne < 6 && !gagnantTrouve; chaqueLigne++) {
                if (plateauJeu[chaqueLigne][chaqueColonne] == couleurPrecedente) {
                    nbreDeCasesMemeCouleur ++;
                    if (couleurPrecedente == "jaune" || couleurPrecedente == "rouge") {
                        position = "" + chaqueLigne + chaqueColonne;
                        reponse.set(nbreDeCasesMemeCouleur, position);
                        if (nbreDeCasesMemeCouleur == 4) {
                            gagnantTrouve = true;
                        }
                    }
                }
                else {
                    nbreDeCasesMemeCouleur = 1;
                    couleurPrecedente = plateauJeu[chaqueLigne][chaqueColonne];
                }
            }
        }

        if (gagnantTrouve) {
            reponse.set(0, couleurPrecedente);
        }

        return reponse;

    }

    private ArrayList<String> verifierDiagonalesMontantes (String[][] plateauJeu) {

        // la fonction retourne en position 0 les valeurs possibles suivantes :
        //              jaune, rouge, ou "" pour dire que la partie n'est pas finie
        // les positions 1, 2, 3, et 4 indiquent cellules gagnantes
        ArrayList<String> reponse = new ArrayList<>();
        String couleurPrecedente = "";
        int nbreDeCasesMemeCouleur = 1;
        boolean gagnantTrouve = false;
        reponse.add(0, "");
        reponse.add(1,"");
        reponse.add(2,"");
        reponse.add(3,"");
        reponse.add(4,"");
        String position = "";

        for (int departLigne = 0; departLigne < 3 && !gagnantTrouve; departLigne++ ) {
            int j = 0;
            nbreDeCasesMemeCouleur = 1;
            couleurPrecedente = plateauJeu[departLigne][0];
            position = departLigne + "0";
            reponse.set(1, position);
            for (int i = departLigne+1; i < 6 && !gagnantTrouve; i++) {
                j++;
                if (plateauJeu[i][j] == couleurPrecedente) {
                    nbreDeCasesMemeCouleur ++;
                    if (couleurPrecedente == "jaune" || couleurPrecedente == "rouge") {
                        position = "" + i + j;
                        reponse.set(nbreDeCasesMemeCouleur, position);
                        if (nbreDeCasesMemeCouleur == 4) {
                            gagnantTrouve = true;
                        }
                    }
                }
                else {
                    nbreDeCasesMemeCouleur = 1;
                    couleurPrecedente = plateauJeu[i][j];
                }
            }
        }

        for (int departLigne = 1; departLigne < 4 && !gagnantTrouve; departLigne++ ) {
            int i = 0;
            nbreDeCasesMemeCouleur = 1;
            couleurPrecedente = plateauJeu[i][departLigne];
            position = "0" + departLigne;
            reponse.set(1, position);
            for (int j = departLigne+1; j < 7 && !gagnantTrouve; j++) {
                i++;
                if (plateauJeu[i][j] == couleurPrecedente) {
                    nbreDeCasesMemeCouleur ++;
                    if (couleurPrecedente == "jaune" || couleurPrecedente == "rouge") {
                        position = "" + i + j;
                        reponse.set(nbreDeCasesMemeCouleur, position);
                        if (nbreDeCasesMemeCouleur == 4) {
                            gagnantTrouve = true;
                        }
                    }
                }
                else {
                    nbreDeCasesMemeCouleur = 1;
                    couleurPrecedente = plateauJeu[i][j];
                }
            }
        }

        if (gagnantTrouve) {
            reponse.set(0, couleurPrecedente);
        }

        return reponse;

    }

    private ArrayList<String> verifierDiagonalesDescendantes (String[][] plateauJeu) {

        // la fonction retourne en position 0 les valeurs possibles suivantes :
        //              jaune, rouge, finPartie (sans vainqueur), ou "" pour dire que la partie n'est pas finie
        // les positions 1, 2, 3, et 4 indiquent cellules gagnantes
        ArrayList<String> reponse = new ArrayList<>();
        String couleurPrecedente = "";
        int nbreDeCasesMemeCouleur = 1;
        boolean gagnantTrouve = false;
        reponse.add(0, "");
        reponse.add(1,"");
        reponse.add(2,"");
        reponse.add(3,"");
        reponse.add(4,"");
        String position = "";

        for (int departLigne = 3; departLigne < 6 && !gagnantTrouve; departLigne++ ) {
            System.out.println("depart ligne = " + departLigne);
            int j = 0;
            nbreDeCasesMemeCouleur = 1;
            couleurPrecedente = plateauJeu[departLigne][0];
            position = departLigne + "0";
            reponse.set(1, position);
            for (int i = departLigne-1; i >= 0 && !gagnantTrouve; i--) {
                System.out.println("couleur precedente : " + couleurPrecedente);
                j++;
                if (plateauJeu[i][j] == couleurPrecedente) {
                    System.out.println("plateauJeu[i][j] : " + plateauJeu[i][j]);
                    nbreDeCasesMemeCouleur ++;
                    System.out.println("nbreDeCasesMemeCouleur : " + nbreDeCasesMemeCouleur);
                    if (couleurPrecedente == "jaune" || couleurPrecedente == "rouge") {
                        position = "" + i + j;
                        reponse.set(nbreDeCasesMemeCouleur, position);
                        if (nbreDeCasesMemeCouleur == 4) {
                            gagnantTrouve = true;
                        }
                    }
                }
                else {
                    nbreDeCasesMemeCouleur = 1;
                    System.out.println("nbreDeCasesMemeCouleur : " + nbreDeCasesMemeCouleur);
                    couleurPrecedente = plateauJeu[i][j];
                }
            }
        }

        for (int departLigne = 0; departLigne < 4 && !gagnantTrouve; departLigne++ ) {
            int i = 6;
            nbreDeCasesMemeCouleur = 1;
            couleurPrecedente = plateauJeu[i-1][departLigne];
            position = "5" + departLigne;
            reponse.set(1, position);
            for (int j = departLigne+1; j >= 7 && !gagnantTrouve; j++) {
                i--;
                if (plateauJeu[i][j] == couleurPrecedente) {
                    nbreDeCasesMemeCouleur ++;
                    if (couleurPrecedente == "jaune" || couleurPrecedente == "rouge") {
                        position = "" + i + j;
                        reponse.set(nbreDeCasesMemeCouleur, position);
                        if (nbreDeCasesMemeCouleur == 4) {
                            gagnantTrouve = true;
                        }
                    }
                }
                else {
                    nbreDeCasesMemeCouleur = 1;
                    couleurPrecedente = plateauJeu[i][j];
                }
            }
        }

        if (gagnantTrouve) {
            reponse.set(0, couleurPrecedente);
        }

        return reponse;

    }
}
