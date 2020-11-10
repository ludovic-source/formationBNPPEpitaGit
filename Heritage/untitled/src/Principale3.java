public class Principale3 {
    public static void main(String[] args) {

        System.out.println(Centaure.NBRE_PATTES);
        Centaure centaure1 = new Centaure();
        Centaure centaure2 = new Centaure();
        System.out.println(centaure2.getPopulation());
        // la population passe à 2 car la variable population est static

    }

}
