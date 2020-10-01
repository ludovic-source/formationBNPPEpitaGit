import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ContactService {

    url = 'http://localhost:8080/';

    listeContactsSubject = new Subject<any[]>();
    private listeContacts: any[];

    listeContactsFiltresSubject = new Subject<any[]>();
    private listeContactsFiltres: any[];   

    constructor(private httpClient: HttpClient) {
    }

    emitListeContactsSubject() {
        this.listeContactsSubject.next(this.listeContacts);
    }

    emitListeContactsFiltresSubject() {
        this.listeContactsFiltresSubject.next(this.listeContactsFiltres);
    }

    getAllContacts() {
        this.httpClient
            .get<any>(this.url + 'contacts/all/')
            .subscribe(
                (response) => {
                    this.listeContacts = response;
                    this.listeContactsFiltres = response; 
                    this.emitListeContactsSubject();  
                    this.emitListeContactsFiltresSubject();                       
                },
                (error) => {
                    console.log('Erreur ! : ' + error);
                }
        ); 
    }

    getFilteredContacts(searchContact) {
        console.log("searchContact: " + searchContact);
        this.listeContactsFiltres = this.listeContacts.filter( contact =>
            contact.nom.toLowerCase().includes(searchContact) || 
            contact.prenom.toLowerCase().includes(searchContact)
        );
        this.emitListeContactsFiltresSubject();
    }

    ajoutFavori(contact) {
        var index = this.listeContacts.indexOf(contact);
        if (index != null) {
            this.listeContacts[index].favori = true;
            this.emitListeContactsSubject(); 
        }
        index = this.listeContactsFiltres.indexOf(contact);
        if (index != null) {
            this.listeContacts[index].favori = true;
            this.emitListeContactsFiltresSubject(); 
        }
    }

    deleteFavori(contact) {
        var index = this.listeContacts.indexOf(contact);
        if (index != null) {
            this.listeContacts[index].favori = false;
            this.emitListeContactsSubject(); 
        }
        index = this.listeContactsFiltres.indexOf(contact);
        if (index != null) {
            this.listeContactsFiltres[index].favori = false;
            this.emitListeContactsFiltresSubject(); 
        }
    }

    ajouterContact(contact) {
        var index = this.listeContacts.indexOf(contact);
        this.listeContacts.push(contact);
        this.emitListeContactsSubject(); 
        index = this.listeContactsFiltres.indexOf(contact);
        this.listeContactsFiltres.push(contact);
        this.emitListeContactsFiltresSubject();        
    }

    suprimerContact(contact) {
        var index = this.listeContacts.indexOf(contact);
        if (index != -1) {
            this.listeContacts.splice(index, 1);
            this.emitListeContactsSubject(); 
        }
        index = this.listeContactsFiltres.indexOf(contact);
        if (index != -1) {
            this.listeContactsFiltres.splice(index, 1);
            this.emitListeContactsFiltresSubject(); 
        }
    }

    getContact(prenomContact: string) {
        return this.listeContacts.find(contact => contact.prenom == prenomContact);
    }

}