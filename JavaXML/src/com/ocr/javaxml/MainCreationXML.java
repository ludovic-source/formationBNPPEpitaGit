package com.ocr.javaxml;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainCreationXML {

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            //la première chose qui change, nous n'allons pas lire un fichier
            //mais nous allons en créer un de toutes pièces
            Document xml = builder.newDocument();

            //Création de notre élément racine
            Element root = xml.createElement("bibliotheque");

            //ensuite nous créons tous les nœuds de notre fichier XML
            Element trunk = xml.createElement("livre");
            trunk.setAttribute("auteur", "M@teo21");

            Element trunk2 = xml.createElement("livre");
            trunk2.setAttribute("auteur", "cysboy");

            Element branche = xml.createElement("titre");
            branche.setTextContent("Apprenez à programmer en C");

            Element branche2 = xml.createElement("titre");
            branche2.setTextContent("Apprenez à programmer en Java");

            //nous ajoutons donc les nœuds "livre" aux nœuds "bibliothèque"
            root.appendChild(trunk);
            root.appendChild(trunk2);

            //Nous lions les nœuds les uns aux autres pour faire notre structure XML
            //nous ajoutons donc les nœuds "titre" aux nœuds "livre"
            trunk.appendChild(branche);
            trunk2.appendChild(branche2);

            //On crée un fichier xml correspondant au résultat
            //construire la transformation inactive
            Transformer t = TransformerFactory.newInstance().newTransformer();
            //définir les propriétés de sortie pour obtenir un nœud DOCTYPE
            t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "maDtdBibon.dtd");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            // appliquer la transformation
            String resultFile = "monTest.xml";
            StreamResult XML = new StreamResult(resultFile);

            t.transform(new DOMSource(root), XML);

        } catch (DOMException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
