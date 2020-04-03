package com.ocr.javaxml;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainStaxCursorWriter {

    public static void main(String[] args) {

        //Création de notre factory d'écriture
        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
            //On initialise le flux en écriture
            FileOutputStream fw = new FileOutputStream(new File("xml/CursorWriter1.xml"));
            // FileWriter fw = new FileWriter(new File("xml/CursorWriter1.xml"));

            //nous créons notre objet qui va écrire dans notre fichier
            // XMLStreamWriter writer = factory.createXMLStreamWriter(fw);
            XMLStreamWriter writer = new IndentingXMLStreamWriter(factory.createXMLStreamWriter(fw, "UTF-8"));

            // Ecriture du prologue
            writer.writeStartDocument();

            // Ecrire le DTD
            writer.writeDTD("<!DOCTYPE racine SYSTEM \"arbre.dtd\">");

            // Ecrire la balise racine
            writer.writeStartElement("racine");

                // Ecrire la balise tronc et son attribut
                writer.writeStartElement("tronc");
                    writer.writeAttribute("nom", "tronc secondaire");

                // Ecrire la balise branche et son attribut
                writer.writeStartElement("branche");
                    writer.writeAttribute("nom", "branche1");

                    // Ecrire la balise fruit, son nom et sa couleur, puis ferme la balise fruit
                    writer.writeStartElement("fruit");
                        writer.writeStartElement("nom");
                            writer.writeCharacters("Pommes");
                        writer.writeEndElement();
                        writer.writeStartElement("couleur");
                            writer.writeCharacters("Jaune");
                         writer.writeEndElement();
                    writer.writeEndElement();

                    // Ecrire la balise fruit, son nom et sa couleur, puis ferme la balise fruit
                    writer.writeStartElement("fruit");
                        writer.writeStartElement("nom");
                            writer.writeCharacters("Cerise");
                        writer.writeEndElement();
                        writer.writeStartElement("couleur");
                            writer.writeCharacters("Rouge");
                        writer.writeEndElement();
                    writer.writeEndElement();

                    // Ecrire la balise feuille et le referme
                    writer.writeStartElement("feuille");
                        writer.writeCharacters("Feuille normale");
                    writer.writeEndElement();

                // ferme l'élement branche
                writer.writeEndElement();

                // Ecrire la balise branche et son attribut
                writer.writeStartElement("branche");
                    writer.writeAttribute("nom", "branche2");

                    // Ecrire la balise fruit, son nom et sa couleur, puis ferme la balise fruit
                    writer.writeStartElement("fruit");
                        writer.writeStartElement("nom");
                            writer.writeCharacters("Pommes");
                        writer.writeEndElement();
                        writer.writeStartElement("couleur");
                            writer.writeCharacters("Jaune");
                        writer.writeEndElement();
                    writer.writeEndElement();

                    // Ecrire la balise fruit, son nom et sa couleur, puis ferme la balise fruit
                    writer.writeStartElement("fruit");
                         writer.writeStartElement("nom");
                            writer.writeCharacters("Cerise");
                         writer.writeEndElement();
                         writer.writeStartElement("couleur");
                             writer.writeCharacters("Rouge");
                         writer.writeEndElement();
                    writer.writeEndElement();

                    // Ecrire la balise feuille et le referme
                    writer.writeStartElement("feuille");
                        writer.writeCharacters("Feuille normale");
                    writer.writeEndElement();
                    writer.writeStartElement("feuille");
                        writer.writeCharacters("Deuxième feuille");
                    writer.writeEndElement();

                // ferme l'élement branche
                writer.writeEndElement();

                // ferme l'élement tronc
                writer.writeEndElement();

            // ferme l'élement racine
            writer.writeEndElement();

            // fin document
            writer.writeEndDocument();

            //Très important, on doit invoquer les méthodes flush() et close()
            //pour rendre l'écriture effective
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

}
