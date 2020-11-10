public class Centaure {

    final static int NBRE_BRAS = 2;
    final static int NBRE_PATTES = 4;

    private static int population; // permet de ne pas réinitialiser la valeur à chaque instanciation

    public Centaure() {
        this.population += 1;
    }

    public int getPopulation() {
        return population;
    }


}
