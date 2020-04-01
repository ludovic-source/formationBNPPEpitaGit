package com.ocr.javaxml;

import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

            // Nous récupérons une instance de factory qui se chargera de nous fournir
            // un parseur
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // pour ignorer les espaces blancs entre les balises - permet d'avoir un nbre d'enfants réels
            factory.setIgnoringElementContentWhitespace(true);

            try {
                //Ces trois lignes servent à informer que la validation se fait via un fichier XSD
                SchemaFactory sfactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                //On créé notre schéma XSD
                //Ici, c'est un schéma interne, pour un schéma externe il faut mettre l'URI
                Schema schema = sfactory.newSchema(new File("xml/arbre.xsd"));
                //On l'affecte à notre factory afin que le document prenne en compte le fichier XSD
                factory.setSchema(schema);

                //Méthode qui permet d'activer la vérification du fichier - à ajouter pour le cas d'une validation DTD
                // factory.setValidating(true);

                // Création de notre parseur via la factory
                DocumentBuilder builder = factory.newDocumentBuilder();

                //création de notre objet d'erreurs
                ErrorHandler errHandler = new SimpleErrorHandler();

                //Affectation de notre objet au document pour interception des erreurs éventuelles
                builder.setErrorHandler(errHandler);

                File fileXML = new File("xml/arbre.xml");

                //On rajoute un bloc de capture
                //pour intercepter les erreurs au cas où il y en a
                try {
                    // parsing de notre fichier via un objet File et récupération d'un
                    // objet Document
                    // Ce dernier représente la hiérarchie d'objet créée pendant le parsing
                    Document xml = builder.parse(fileXML);
                    // Via notre objet Document, nous pouvons récupérer un objet Element
                    // Ce dernier représente un élément XML mais, avec la méthode ci-dessous,
                    // cet élément sera la racine du document
                    Element root = xml.getDocumentElement();

                    // pour afficher tous les noeuds parents + enfants
                    System.out.println(description(root, ""));

                } catch (SAXParseException e){}

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        /*
        getFirstChild(): récupérer le premier enfant du nœud ;
        getLastChild(): récupérer le dernier enfant du nœud ;
        getNextSibling(): récupérer le nœud suivant ;
        getParentNode(): récupérer le nœud parent ;
        getAttribute(String name): retourne la valeur de l'attribut passé en paramètre ;
        getElementsByTagName(String name): retourne une NodeList contenant tous les enfants du nœud portant le nom passé en paramètre ;
        */
    }

    public static String description(Node n, String tab){
        String str = new String();

        //Nous nous assurons que le nœud passé en paramètre est une instance d'Element
        //juste au cas où il s'agisse d'un texte ou d'un espace, etc.
        if(n instanceof Element){

            //Nous sommes donc bien sur un élément de notre document

            String tab2 = tab;
            //Nous pouvons récupérer le nom du nœud actuellement parcouru
            //grâce à cette méthode, nous ouvrons donc notre balise
            str += tab2 + n.getNodeName() + "\n";
            tab2 = "   " + tab2;

            //nous contrôlons la liste des attributs présents
            if(n.getAttributes() != null && n.getAttributes().getLength() > 0){

                //nous pouvons récupérer la liste des attributs d'un élément
                NamedNodeMap att = n.getAttributes();
                int nbAtt = att.getLength();

                //nous parcourons tous les nœuds pour les afficher
                for(int j = 0; j < nbAtt; j++){
                    Node noeud = att.item(j);
                    //On récupère le nom de l'attribut et sa valeur grâce à ces deux méthodes
                    str += tab2 + "ATTRIBUT : " + noeud.getNodeName() + "=" + noeud.getNodeValue() + "\n";
                }

            }

            //La méthode getChildNodes retournant le contenu du nœud + les nœuds enfants
            //Nous récupérons le contenu texte uniquement lorsqu'il n'y a que du texte, donc un seul enfant
            if(n.getChildNodes().getLength() == 1) {
                str += tab2 + "VALEUR : " + n.getTextContent() + "\n";
            }

            //Nous allons maintenant traiter les nœuds enfants du nœud en cours de traitement
            int nbChild = n.getChildNodes().getLength();

            //Nous récupérons la liste des nœuds enfants
            NodeList list = n.getChildNodes();

            //nous parcourons la liste des nœuds
            for(int i = 0; i < nbChild; i++){
                Node n2 = list.item(i);

                //si le nœud enfant est un Element, nous le traitons
                if (n2 instanceof Element){
                    //appel récursif à la méthode pour le traitement du nœud et de ses enfants
                    str += description(n2, tab2);
                }
            }

        }

        return str;
    }

}
