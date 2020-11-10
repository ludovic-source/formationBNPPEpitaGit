public class Principale4 {

    public static void main(String[] args) {

        // Dictionnaire dictionnaire = new Dictionnaire("toto");
        // comme le constructeur de la classe Dictionnaire est privée, on ne peut pas créer un new dictionnaire
        // en dehors de la classe
        System.out.println(Dictionnaire.getInstance());

    }
}
