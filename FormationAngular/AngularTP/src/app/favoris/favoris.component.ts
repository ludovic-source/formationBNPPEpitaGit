import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ContactService } from '../services/contact.service';

@Component({
    selector: 'app-favoris',
    templateUrl: './favoris.component.html',
    styleUrls: ['./favoris.component.css']
})
export class FavorisComponent implements OnInit {

    listeContacts : any[];
    listeContactsSubscription : Subscription;

    constructor(private contactService: ContactService) { }

    ngOnInit(): void {
        this.listeContactsSubscription = this.contactService.listeContactsSubject.subscribe(
          (listeContacts: any) => {
                                this.listeContacts = listeContacts;
                                      });
        this.contactService.emitListeContactsSubject();
        this.contactService.getAllContacts();
    }

}
