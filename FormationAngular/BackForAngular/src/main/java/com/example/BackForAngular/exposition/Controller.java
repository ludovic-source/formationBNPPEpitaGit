package com.example.BackForAngular.exposition;

import com.example.BackForAngular.domaine.Contact;
import com.example.BackForAngular.domaine.Nom;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @GetMapping("/get/hello/langue/{langue}")
    @CrossOrigin
    public String getHello(@PathVariable("langue") String langue) {
        if (langue.equals("français")) {
            return "Bonjour";
        } else {
            if (langue.equals("anglais")) {
                return "Hello";
            }
        }
        return "Désolé, je ne parle que français ou anglais";
    }

    @GetMapping("/get/nom/{id}")
    @CrossOrigin
    public Nom getNom(@PathVariable("id") Integer id) {
        String nom[] = {"Luffy", "Robin", "Sanji", "Zoro", "Nami", "Uzop"};
        return new Nom(nom[id-1]);
    }

    @GetMapping("/get/hello/nom/{nom}")
    @CrossOrigin
    public Message getHelloWithNom(@PathVariable("nom") String nom) {
        return new Message("Hello " + nom);
    }

    private class Message {
        String message;

        public Message(String message) {
            this.message = message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    @GetMapping("/contacts/all")
    @CrossOrigin
    public List<Contact> getAllContacts() {
        return creerContacts();
    }

    private List<Contact> creerContacts() {
        List<Contact> listeContacts = new ArrayList<>();
        Contact contact1 = new Contact("D.Monkey", "Luffy",
                "En route pour Grand Line", "06-45-18-29-31",
                "100 millions de Berrys", "Vogue Merry", true        );
        listeContacts.add(contact1);
        Contact contact2 = new Contact("", "Nami",
                "Reine de la navigation", "06-45-18-29-32",
                "?? millions de Berrys", "Vogue Merry", true        );
        listeContacts.add(contact2);
        Contact contact3 = new Contact("Roronoa", "Zoro",
                "Technique aux 3 sabres", "06-45-18-29-33",
                "80 millions de Berrys", "Vogue Merry", true        );
        listeContacts.add(contact3);
        Contact contact4 = new Contact("Robin", "Nico",
                "A la recherche de l'histoire manquante", "06-45-18-29-34",
                "100 millions de Berrys", "Vogue Merry", true        );
        listeContacts.add(contact4);
        Contact contact5 = new Contact("", "Sanji",
                "Grand maître cuisinier", "06-45-18-29-35",
                "?? millions de Berrys", "Vogue Merry", false        );
        listeContacts.add(contact5);
        Contact contact6 = new Contact("", "Usopp",
                "Le plus grand des menteurs", "06-45-18-29-36",
                "?? millions de Berrys", "Vogue Merry", false        );
        listeContacts.add(contact6);
        Contact contact7 = new Contact("", "Chopper",
                "Le renne médecin", "06-45-18-29-37",
                "?? millions de Berrys", "Vogue Merry", true        );
        listeContacts.add(contact7);

        return listeContacts;
    }


}
