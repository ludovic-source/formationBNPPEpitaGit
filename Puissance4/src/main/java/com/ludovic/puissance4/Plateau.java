package com.ludovic.puissance4;

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

}
