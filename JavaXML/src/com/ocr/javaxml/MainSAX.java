package com.ocr.javaxml;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.IOException;

public class MainSAX {

    public static void main(String[] args) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();

            //Déclenchement de la validation
            factory.setValidating(true);

            SAXParser parser = factory.newSAXParser();
            MyXMLHandler handler = new MyXMLHandler();
            parser.parse("xml/arbre.xml", handler);

            Racine racine = handler.getRacine();

            System.out.println(racine);

            // juste pour tester la methode surchargée toString()
            Test test = new Test("titi");
            System.out.println(test);

        } catch (DOMException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
