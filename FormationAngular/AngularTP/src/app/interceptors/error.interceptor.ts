import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor() {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            if (err.status === 102) {
                // auto logout if 102 response returned from api
                alert("Désolé le site n'est pas disponible");
            }
            if (err.status === 500) {
                console.log("err : " + err.error);
                alert("erreur 500 " + err.error);
                console.log(err.error);
            }
            if (err.status === 400) {
                console.log("err : " + err);
                alert("erreur 400 - mauvaise requête : " + err.error);
                console.log(err.error);
            }
            if (err.status === 404) {
                console.log("err : " + err);
                alert("erreur 404 - non trouvé : " + err.error);
                console.log(err.error);
            }

            const error = err.error.message || err.statusText;
            return throwError(error);
        }))
    }
}