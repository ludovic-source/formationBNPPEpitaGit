public class ExerciceExceptions {

    public static void main(String[] args) {

        int nombre = -18;
        int factorielleDeNombre = 0;

        try {
            factorielleDeNombre = factorielle(nombre);
            System.out.println("La factorielle de " + nombre + " est " + factorielleDeNombre);
        } catch (FactorielleExceptionNbreNegatif e) {
            e.printStackTrace();
            System.out.println("Le nbre saisi en entrée est négatif");
        } catch (FactorielleExceptionIncoherenceCalcul e) {
            e.printStackTrace();
            System.out.println("Le résultat dépasse les limites du programme");
        } finally {
            System.out.println("Fin traitement");
        }

    }

	public static int factorielle (int nombre) {
        int resultatFactorielle = 1;

        if (nombre < 0) {
            throw (new FactorielleExceptionNbreNegatif());
        }

        for (int i = 1; i <= nombre; i++) {
            resultatFactorielle = resultatFactorielle * i;
        }

        if (resultatFactorielle <= 0) {
            throw (new FactorielleExceptionIncoherenceCalcul());
        }
        else {
            return resultatFactorielle;
        }
    }
}
