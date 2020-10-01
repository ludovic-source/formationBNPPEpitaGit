import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormControl } from "@angular/forms";  // pour la barre de recherche
import { debounceTime } from "rxjs/operators"; // pour la barre de recherche
import { Subscription } from 'rxjs/Subscription';
import { ContactService } from '../services/contact.service';
import { Contact } from '../models/Contact';

@Component({
  selector: 'app-contact-app',
  templateUrl: './contact-app.component.html',
  styleUrls: ['./contact-app.component.css']
})
export class ContactAppComponent implements OnInit {

    listeContactsFiltres : any[];
    listeContactsFiltresSubscription : Subscription;

    editionContact: boolean

    public searchControl: FormControl;  // pour la barre de recherche

    constructor(private contactService: ContactService) { 
        this.searchControl = new FormControl();     // pour la barre de recherche
    }

    ngOnInit(): void {
        this.listeContactsFiltresSubscription = this.contactService.listeContactsFiltresSubject.subscribe(
          (listeContacts: any) => {
                                this.listeContactsFiltres = listeContacts;
                                      });
        this.contactService.emitListeContactsFiltresSubject();
        this.getAllContacts();
        this.editionContact = false;
        
    }

    // async permet d'attendre la liste des contacts avant d'initialiser l'observable pour la recherce
    async getAllContacts() {
        let response:any = await this.contactService.getAllContactsPromise();
        console.log("response = " + response);
        if (response) {
            this.listeContactsFiltres = response; 
            this.contactService.emitListeContactsFiltresSubject(); 
            // pour la barre de recherche  
            this.contactService.getFilteredContacts(""); 
            this.searchControl.valueChanges
                .pipe(debounceTime(700))
                .subscribe(search => {
                    this.contactService.getFilteredContacts(search.toLowerCase());
            });
        }    
    }

    ajoutFavori(contact) {
        this.contactService.ajoutFavori(contact);
    }

    deleteFavori(contact) {
        this.contactService.deleteFavori(contact);
    }

    supprimerContact(contact) {
        this.contactService.suprimerContact(contact);
    }

    editerContact() {
        this.editionContact = true;
    }

    ajouterContact(form: NgForm) {
        console.log(form.value);
        const contact = new Contact();
        contact.nom = form.value['nom'];
        contact.prenom = form.value['prenom'];
        contact.expression = form.value['expression'];
        contact.telephone = form.value['telephone'];
        contact.prime = form.value['prime'];
        contact.bateau = form.value['bateau'];
        contact.favori = false;
        contact.dateAjout = new Date();
        this.contactService.ajouterContact(contact);
        this.editionContact = false;
    }

}
