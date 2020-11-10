import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class ExerciceApiCollections {

    public static void main(String[] args) {

        // collection non triée

        ArrayList<String> listeChaineDeCaracteres = new ArrayList<>();
        listeChaineDeCaracteres.add("Chat");
        listeChaineDeCaracteres.add("Chien");
        listeChaineDeCaracteres.add("Oiseau");
        listeChaineDeCaracteres.add("Renard");
        listeChaineDeCaracteres.add("Elephant");
        System.out.println(listeChaineDeCaracteres.size());
        afficherListe(listeChaineDeCaracteres);

        Collections.sort(listeChaineDeCaracteres);
        System.out.println(listeChaineDeCaracteres);

        // collection triée
        TreeSet<String> arbreSet = new TreeSet<>();

        // collection à 2 dimensions
        ArrayList<ArrayList<String>> array2dimensions = new ArrayList<>();

    }

    private static void afficherListe(ArrayList<String> listeChaineDeCaracteres) {
        System.out.println(listeChaineDeCaracteres);
        // autre solution
        for (String animal : listeChaineDeCaracteres) {
            System.out.println(animal);
        }
    }

}
