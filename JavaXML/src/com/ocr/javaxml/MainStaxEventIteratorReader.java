package com.ocr.javaxml;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;


public class MainStaxEventIteratorReader {

    public static void main(String[] args) {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        //On utilise notre fichier de test
        File file = new File("xml/arbre.xml");

        int tourDeBoucle = 0;

        try {

            //On initialise le reader
            XMLEventReader reader = factory.createXMLEventReader(new FileReader(file));

            //On lit le document
            while (reader.hasNext()) {
                //On récupère l'événement avec la méthode nextEvent()
                XMLEvent event = reader.nextEvent();

                //Lors d'un événement d'ouverture de balise
                if(event.isStartElement()){
                    //On cast notre objet
                    StartElement start = (StartElement)event;
                    //start.
                    System.out.println(start);

                    //Traitement des attributs de balises
                    //On fait ceci sur l'ouverture de balises...
                    //Logique car l'attribut est sur la balise ouvrante
                    Iterator it = start.getAttributes();
                    while(it.hasNext()){
                        //On récupère un objet attribut
                        Attribute att = (Attribute) it.next();
                        System.out.println("\t\t " + att.getName() + " = " + att.getValue());
                    }
                    tourDeBoucle++;
                }

                //Traitement des événements de clôture de balises
                else if(event.isEndElement()){
                    //On cast notre objet
                    EndElement end = (EndElement)event;
                    System.out.println(end);
                }

                //Traitement des chaînes de caractères, les valeurs...
                else if(event.isCharacters() && !event.asCharacters().getData().equals("")){
                    //On cast notre objet
                    Characters carac = (Characters)event;
                    System.out.println(carac);
                }

                //Pour le traitement des DTD, il n'y a pas de méthode dédiée
                //Nous devons donc ruser et utiliser les types numériques...
                else if(event.getEventType() == XMLStreamReader.DTD){
                    //On cast notre objet
                    DTD dtd = (DTD)event;
                    System.out.println(dtd);
                }

                //Idem que pour les DTD, les commentaires n'ont pas de méthodes dédiées
                else if(event.getEventType() == XMLStreamReader.COMMENT){
                    //On cast notre objet
                    Comment com = (Comment)event;
                    System.out.println(com);
                }

                //On arrête la lecture après 5 ouvertures d'éléments
                if(tourDeBoucle > 5)break;
            }

            //récupération du prochain événement, sans le consommer
            XMLEvent event = reader.peek();
            System.out.println("Evénement suivant : " + event.getEventType());
            System.out.println(event);

            //affiche la même chose car la méthode peek ne fait pas avancer l'itérateur
            event = reader.peek();
            System.out.println(event);

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}



