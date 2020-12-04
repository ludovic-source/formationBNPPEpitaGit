package com.ludovic;

public class FizzBuzz {

    public String fizzBuzz(int nbre) throws FizzBuzzNegatifException {
        if (nbre < 0) {
            throw (new FizzBuzzNegatifException());
        }

        /** AVANT REFACTORING
        if (nbre % 5 == 0 && nbre % 3 == 0 && nbre % 7 ==0) {
            return "FIZZBUZZQIX";
        }
        if (nbre % 5 == 0 && nbre % 3 == 0) {
            return "FIZZBUZZ";
        }
        if (nbre % 5 == 0 && nbre % 7 == 0) {
            return "BUZZQIX";
        }
        if (nbre % 3 == 0 && nbre % 7 == 0) {
            return "FIZZQIX";
        }
        if (nbre % 3 == 0) {
            return "FIZZ";
        }
        if (nbre % 5 == 0) {
            return "BUZZ";
        }
        if (nbre % 7 == 0) {
            return "QIX";
        }
        return String.valueOf(nbre);
        */

        // APRES REFACTORING
        String resultat = "";
        if (nbre % 3 !=0 && nbre % 5 !=0 && nbre % 7 !=0) {
            resultat += nbre;
        } else {
            if (nbre % 3 == 0) {
                resultat += "FIZZ";
            }
            if (nbre % 5 == 0) {
                resultat += "BUZZ";
            }
            if (nbre % 7 == 0) {
                resultat += "QIX";
            }

            String nbreString = "" + nbre;
            for (int i = 0; i < nbreString.length(); i++) {
                if (nbreString.charAt(i) == '3') {
                    resultat += "FIZZ";
                }
                if (nbreString.charAt(i) == '5') {
                    resultat += "BUZZ";
                }
                if (nbreString.charAt(i) == '7') {
                    resultat += "QIX";
                }
                if (nbreString.charAt(i) == '0') {
                    resultat += "*";
                }
            }
        }

        resultat = resultat.replace('0', '*');
        return resultat;

    }

}
