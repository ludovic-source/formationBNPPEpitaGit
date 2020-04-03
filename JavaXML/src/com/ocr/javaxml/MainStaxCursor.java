package com.ocr.javaxml;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class MainStaxCursor {

    public static void main(String[] args) {

        //Création de notre factory
        XMLInputFactory factory = XMLInputFactory.newInstance();

        //On utilise notre fichier de test
        File file = new File("xml/arbre.xml");

        //Nous allons stocker deux fruits et arrêter la lecture
        ArrayList<String> listFruit = new ArrayList<>();

        try {
            //Obtention de notre reader
            XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(file));

            while (reader.hasNext()) {
                // Récupération de l'événement
                int typeEvenement = reader.next();

                if (typeEvenement == XMLStreamReader.START_ELEMENT) {
                    System.out.println("Nous sommes sur un élement " + reader.getLocalName());

                    // Si c'est un début de balise, on vérifie qu'il s'agit de la balise du nom du fruit
                    if(reader.getLocalName().equals("nom")){
                        //On récupère l'évenement suivant, qui sera le contenu de la balise
                        //et on stocke dans la collection
                        reader.next();
                        listFruit.add(reader.getText());
                        System.out.println("\t -> Fruit récupérée ! ");
                    }
                }

                //Si nous avons deux fruits, on stop la lecture
                if(listFruit.size() > 1){
                    System.out.println("\t --> Nombre de fruit > 1 => fin de boucle !");
                    break;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        System.out.println((listFruit));

        // voici un exemple plus complet
        MainStaxCursor.autreExemple();

    }

    public static void autreExemple() {

        //Création de notre factory
        XMLInputFactory factory = XMLInputFactory.newInstance();

        //On utilise notre fichier de test
        File file = new File("xml/arbre.xml");

        //Nous allons stocker deux feuilles et arrêter la lecture
        ArrayList<Integer> listEvent = new ArrayList<>();

        try {
            //Obtention de notre reader
            XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(file));
            while (reader.hasNext()) {
                // Récupération de l'événement
                int type = reader.next();

                // je ne vois pas l'intérêt de ces 2 lignes
                if(!listEvent.contains(type))
                    listEvent.add(type);

                switch (type) {
                    case XMLStreamReader.START_ELEMENT:
                        System.out.println("Début d'élement " + reader.getLocalName() + " nb attributs : " + reader.getAttributeCount());
                        for(int i = 0; i < reader.getAttributeCount(); i++){
                            System.out.println("\t ==> " + reader.getAttributeName(i) + " : " + reader.getAttributeValue(i));
                        }
                        break;

                    case XMLStreamReader.END_ELEMENT:
                        System.out.println("Fin d'élement " + reader.getLocalName());
                        break;

                    // ce cas ne se produit pas avec notre exemple - pour le provoquer il faut créer une erreur dans le xml
                    case XMLStreamReader.DTD:
                        System.out.println("DTD : " + reader.getText());
                        break;

                    case XMLStreamReader.COMMENT:
                        System.out.println(" \n Commentaire : " + reader.getText() + "\n");
                        break;

                    case XMLStreamReader.CHARACTERS:
                        System.out.println("Caractères : " + reader.getText());
                        break;

                    // ce cas ne se produit pas avec notre exemple
                    case XMLStreamReader.ATTRIBUTE:
                        System.out.println("Attribut : " + reader.getElementText());
                        break;

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        System.out.println((listEvent));
    }


}
