import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContactService } from '../services/contact.service';

@Component({
  selector: 'app-fiche-contact',
  templateUrl: './fiche-contact.component.html',
  styleUrls: ['./fiche-contact.component.css']
})
export class FicheContactComponent implements OnInit {

  prenomContact: string;
  contact: any;

  constructor(private route: ActivatedRoute, private contactService: ContactService) { }

  ngOnInit(): void {
      this.prenomContact = this.route.snapshot.params['prenom'];
      this.contact = this.contactService.getContact(this.prenomContact);
      console.log(this.prenomContact);
  }

}
