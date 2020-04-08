package com.ludovic.algorithmes;

import java.util.HashMap;
import java.util.Set;

public class Dictionnaire implements Cloneable {

    // initialisation du dictionnaire : la clé est un mot, et la valeur est le poids d'importance du mot
    // 1 est le poids le plus fort -- 10 est le poids le plus faible
    private static HashMap<String, Integer> dictionnaire = new HashMap<>();

    public void initialiserDictionnaire() {
        dictionnaire.put("bonjour", 1);     // sequence en chiffres sur le telephone : 2665687
        dictionnaire.put("coucou", 2);      // 268268
        dictionnaire.put("chien", 3);        // 24436
        dictionnaire.put("ami", 10);          // 264
        dictionnaire.put("revolution", 1);   // 7386588466
        dictionnaire.put("voiture", 5);      // 8648873
        dictionnaire.put("velo", 4);         // 8356
        dictionnaire.put("chat", 5);         // 2428
        dictionnaire.put("chaton", 4);       // 242866
    }

    public Set<String> recupererSetDictionnaire() {

        Set<String> setDictionnaire = dictionnaire.keySet();
        return setDictionnaire;

    }

    public int getPoids(String mot) {
        return dictionnaire.get(mot);
    }

}
