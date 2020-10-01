import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { AccessBack } from './services/accessBack';
import { ToDoListService } from './services/todolist.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
    title = 'FrontWithAngular';

    listeDesTaches = [];

    nom: string;
    nomSubscription : Subscription;
    //langue: string;
    //message: string;
    //messageSubscription : Subscription;
    //surprise: boolean;
    //nbreSurprise = 0;    

    constructor(private accessBack: AccessBack, private toDoListService: ToDoListService) {}

    ngOnInit(): void {
        this.getListeTaches();
        //this.surprise = false;
        this.nomSubscription = this.accessBack.nomSubject.subscribe(
          (nom: string) => {
                                this.nom = nom;
                             });
        /*
        this.messageSubscription = this.accessBack.messageSubject.subscribe(
          (message: string) => {
                                this.message = message;
                             });   
        */                                                       
    }

    getListeTaches() {
        this.listeDesTaches = this.toDoListService.getAllTaches();
    }

    addTache() {
        this.toDoListService.addTache();
        this.getListeTaches();
    }

    actualiser() {
        console.log("actualisation");
        this.getListeTaches();
        let listeDesTachesCopy = [...this.listeDesTaches];
        this.listeDesTaches = listeDesTachesCopy;
    }

    vider() {
        this.toDoListService.vider();
        this.actualiser();
    }

    /*
    clickSurprise() {
        this.nbreSurprise += 1;
        this.surprise = true;
        this.getNom();
        
        setTimeout(
            () => {
                this.getHello();
            }, 3000
          );              
    }
    */
    /*
    getSurprise() {
        return this.surprise;
    }
    */
    getNom() {
        this.accessBack.getNom(1);
    }

    /*
    getHello() {
        this.accessBack.getHello(this.nom);
    }
    */

}
