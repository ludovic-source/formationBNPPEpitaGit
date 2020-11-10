public class ExerciceEnumerations {
    public static void main(String[] args) {

        // System.out.println((EnumJourDeLaSemaine.JEUDI));

        EnumJourDeLaSemaine[] listeJourDeLaSemaine = EnumJourDeLaSemaine.values();
        int positionJeudi;
        int positionSamedi;

        for (int i = 0; i < 7; i++) {
            System.out.println(listeJourDeLaSemaine[i]);
            System.out.println(listeJourDeLaSemaine[i].getIndiceWeekEnd());
            System.out.println(listeJourDeLaSemaine[i].getSmiley());
        }

        int ordinalJEUDI = EnumJourDeLaSemaine.JEUDI.ordinal();
        int ordinalSAMEDI = EnumJourDeLaSemaine.SAMEDI.ordinal();

        if (ordinalJEUDI < ordinalSAMEDI) {
            System.out.println("Jeudi est bien avant samedi");
        }
        else {
            System.out.println("Jeudi n'est pas devant samedi !!!!!!!");
        }

    }
}
