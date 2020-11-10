public class ExerciceApiString {

    public static void main(String[] args) {

        String chaineAvant = "Coucou A a coucou";
        String chaineApres;
        chaineApres = transformerToutEnMajuscules(chaineAvant);
        System.out.println(chaineApres);

        System.out.println(compterNbreCaracteres(chaineAvant));

        System.out.println(transformeraEn4(chaineAvant));

        System.out.println(supprimerCharEntre5et10(chaineAvant));

        System.out.println(convertirEntierEnString(158));

        String chaineNumerique = "1458";
        System.out.println(convertirStringEnEntier(chaineNumerique));

    }

    private static String transformerToutEnMajuscules(String chaine) {
        String chaineApres = chaine.toUpperCase();
        return chaineApres;
    }

    private static int compterNbreCaracteres(String chaine) {
        int nbreCaracteres = 0;
//        char[] listechaine = chaine.toCharArray();
//        char a = 'a';

        for (int i=0; i < chaine.length(); i++) {
//            if (listechaine[i] == a) {
              if (chaine.charAt(i) == 'a') {
                nbreCaracteres +=1;
            }
        }
        return nbreCaracteres;

    }

    private static String transformeraEn4(String chaine) {
        String chaineConverti = chaine.replace('a', '4');
        return chaineConverti;
    }

    private static String supprimerCharEntre5et10(String chaine) {
        return chaine.substring(0,4) + chaine.substring(10, chaine.length());
    }

    private static String convertirEntierEnString(int nombre) {
        return Integer.toString(nombre);
    }

    private static int convertirStringEnEntier(String chaine) {
        return Integer.valueOf(chaine);
    }
}
