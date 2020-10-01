import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from "@angular/forms";   // pour la barre de recherche
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { ContactService } from './services/contact.service';
import { ErrorInterceptor } from './interceptors/error.interceptor';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FavorisComponent } from './favoris/favoris.component';
import { FicheContactComponent } from './fiche-contact/fiche-contact.component';
import { ContactAppComponent } from './contact-app/contact-app.component';

const appRoutes: Routes = [
  { path: 'contact/:prenom', component: FicheContactComponent },
  { path: '', component: ContactAppComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    FavorisComponent,
    FicheContactComponent,
    ContactAppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    ContactService,
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
