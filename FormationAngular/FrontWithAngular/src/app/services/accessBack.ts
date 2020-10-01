import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class AccessBack {

    url = 'http://localhost:8080/';

    messageSubject = new Subject<string>();
    private message: string;

    nomSubject = new Subject<string>();
    private nom: string;

    constructor(private httpClient: HttpClient) {
    }

    emitNomSubject() {
        this.nomSubject.next(this.nom);
    }

    emitMessageSubject() {
        this.messageSubject.next(this.message);
    }

    getNom(id: number) {
        this.httpClient
            .get<any>(this.url + 'get/nom/' + id)
            .subscribe(
                (response) => {
                    this.nom = response.nom;
                    this.emitNomSubject();                                    
                },
                (error) => {
                    console.log('Erreur ! : ' + error);
                }
            );   
    }

    getHello(nom: string) {
        this.httpClient
            .get<any>(this.url + 'get/hello/nom/' + nom)
            .subscribe(
                (response) => {
                     this.message = response.message;
                     this.emitMessageSubject();
                     console.log(this.message);
                },
                (error) => {
                    console.log('Erreur ! : ' + error);
                }
            );
    }

}
