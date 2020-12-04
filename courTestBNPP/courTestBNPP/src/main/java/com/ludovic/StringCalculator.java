package com.ludovic;

public class StringCalculator {

    public String add(String valeurATester) {

        // controle entrée
        controleInputMethodeAdd(valeurATester);

        // verifier si présence d'un separateur personnalisé
        // --- si separateur personnalisé, alors il faut retirer les premiers caractères de la chaine;
        String separateur = rechercheSeparateurPersonnalise(valeurATester);
        System.out.println(separateur);
        if (separateur.equals("")) {
            separateur = "[,(\n)]";
        } else {
            valeurATester = valeurATester.substring(separateur.length() + 3);
            System.out.println(valeurATester);
        }

        String[] tabNbres = valeurATester.split(separateur);
        int resultatAddition = 0;
        for (int i = 0; i < tabNbres.length; i++) {
            if (tabNbres[i] == "") {
                tabNbres[i] = "0";
            }
            resultatAddition += Integer.parseInt(tabNbres[i]);
        }
        return String.valueOf(resultatAddition);

    }

    private String rechercheSeparateurPersonnalise(String valeurATester) {
        String separateurPersonnalise = "";
        if (valeurATester.length() > 3) {
            if (valeurATester.substring(0, 2).equals("//")) {
                int indexFinPersonnalisation = valeurATester.indexOf('\n');
                separateurPersonnalise = valeurATester.substring(2, indexFinPersonnalisation);
            }
        }
        return separateurPersonnalise;
    }

    private void controleInputMethodeAdd(String valeurATester) {

        // TEST DU DERNIER CARACTERE POUR VERIFIER QU'IL N'Y A PAS DE SEPARATEUR EN FIN DE CHAINE
        // ---- exclure le cas où la chaine est vide ""
        if (valeurATester.length() > 0) {
            if (valeurATester.charAt(valeurATester.length() - 1) == '\n'
                    || valeurATester.charAt(valeurATester.length() - 1) == ',') {
                throw (new EntryInvalidException("Number expected but EOF found"));
            }
        }

        // TEST POUR VERIFIER QU'IL N'Y A PAS DE CARACTERE SPECIFIQUE SUCCESSIF
        char caracterePrecedent = '0';
        for (int i = 0; i < valeurATester.length(); i++) {
            if (valeurATester.charAt(i) == '.' || valeurATester.charAt(i) == '\n' || valeurATester.charAt(i) == ',') {
                if (caracterePrecedent == '.' || caracterePrecedent == '\n' || caracterePrecedent == ',') {
                    throw (new EntryInvalidException(valeurATester.charAt(i), i));
                }
            }
            caracterePrecedent = valeurATester.charAt(i);
        }

    }
}
