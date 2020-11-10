public class Dictionnaire {

    private String titre;

    private static Dictionnaire dictionnaireUnique;

    private Dictionnaire(String titre) {
        this.titre = titre;
    }

    public static Dictionnaire getInstance() {
        if (dictionnaireUnique == null) {
            dictionnaireUnique = new Dictionnaire("Larousse");
        }
        return dictionnaireUnique;
    }

}
