import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExoCollectionVoyageur {

    public static void main(String[] args) {

        Voyageur voyageur1 = new Voyageur(1, 10, "Bob l'éponge", "Business" );
        Voyageur voyageur2 = new Voyageur(2, 1, "Spiderman", "Première" );
        Voyageur voyageur3 = new Voyageur(3, 11, "Mickey", "Business" );
        Voyageur voyageur4 = new Voyageur(4, 24, "Largo Winch", "Eco" );
        Voyageur voyageur5 = new Voyageur(5, 13, "Albator", "Business" );
        Voyageur voyageur6 = new Voyageur(6, 4, "Donald Trump", "Première" );
        Voyageur voyageur7 = new Voyageur(7, 7, "Emmanuel Macron", "Première" );
        Voyageur voyageur8 = new Voyageur(8, 6, "Pablo Escobar", "Première" );
        Voyageur voyageur9 = new Voyageur(9, 21, "Pacho Herrera", "Eco" );
        Voyageur voyageur10 = new Voyageur(10, 15, "Ironman", "Business" );

        ArrayList<Voyageur> collectionDeVoyageurs = new ArrayList<>();
        collectionDeVoyageurs.add(voyageur1);
        collectionDeVoyageurs.add(voyageur2);
        collectionDeVoyageurs.add(voyageur3);
        collectionDeVoyageurs.add(voyageur4);
        collectionDeVoyageurs.add(voyageur5);
        collectionDeVoyageurs.add(voyageur6);
        collectionDeVoyageurs.add(voyageur7);
        collectionDeVoyageurs.add(voyageur8);
        collectionDeVoyageurs.add(voyageur9);
        collectionDeVoyageurs.add(voyageur10);

        // aficher toutes les adresses mémoires des voyageurs
        System.out.println(collectionDeVoyageurs);

        // préparer l'embarquement

        // les premières classes d'abord ; en cas d'égalité, le siège le +petit en premier
        // on commence par selectionner les passagers 1ere classe, puis on trie ces passagers sur le numéro de siège
        System.out.println("DEBUT EMBARQUEMENT DES PREMIERES CLASSES");

        ArrayList<Voyageur> collectionDeVoyageursClassePremiere = new ArrayList<>();

        for (Voyageur voyage : collectionDeVoyageurs) {
            if (voyage.classe == "Première") {
                collectionDeVoyageursClassePremiere.add(voyage);
            }
        }
        embarquerVoyageur(collectionDeVoyageursClassePremiere);

        // traitement des classes Business
        System.out.println("DEBUT EMBARQUEMENT DE LA CLASSE BUSINESS");

        ArrayList<Voyageur> collectionDeVoyageursClasseBusiness = new ArrayList<>();

        for (Voyageur voyage : collectionDeVoyageurs) {
            if (voyage.classe == "Business") {
                collectionDeVoyageursClasseBusiness.add(voyage);
            }
        }
        embarquerVoyageur(collectionDeVoyageursClasseBusiness);

        // traitement de la classe ECO
        System.out.println("DEBUT EMBARQUEMENT DE LA CLASSE ECO");

        ArrayList<Voyageur> collectionDeVoyageursClasseEco = new ArrayList<>();

        for (Voyageur voyage : collectionDeVoyageurs) {
            if (voyage.classe == "Eco") {
                collectionDeVoyageursClasseEco.add(voyage);
            }
        }
        embarquerVoyageur(collectionDeVoyageursClasseEco);

    }

    public static void embarquerVoyageur(ArrayList<Voyageur> collectionDeVoyageursATraiter) {

        ArrayList<Voyageur> collectionDeVoyageursClasseATraiter = collectionDeVoyageursATraiter;

        Voyageur voyageurTampon; // début du tri des voyageurs par rapport au numéro de siège
        int longueurCollection = collectionDeVoyageursClasseATraiter.size();
        for (int j = 1; j < longueurCollection; j++) {
            for (int i = 1; i < longueurCollection; i++) {
                if (collectionDeVoyageursClasseATraiter.get(i).getNumeroDeSiege() < collectionDeVoyageursClasseATraiter.get(i - 1).getNumeroDeSiege()) {
                    voyageurTampon = collectionDeVoyageursClasseATraiter.get(i-1);
                    //collectionDeVoyageursClasseATraiter.add(i, collectionDeVoyageursClasseATraiter.get(i - 1));
                    //collectionDeVoyageursClasseATraiter.add(i - 1, voyageurTampon);
                    collectionDeVoyageursClasseATraiter.remove(i-1);
                    collectionDeVoyageursClasseATraiter.add(i, voyageurTampon);
                }
            }
        }
        for (int i = 0; i < longueurCollection; i++) {
            System.out.println("Embarquement : " + collectionDeVoyageursClasseATraiter.get(i).getNom());
        }

    }

}
