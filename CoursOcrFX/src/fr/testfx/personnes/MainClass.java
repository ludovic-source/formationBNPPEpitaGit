package fr.testfx.personnes;

import java.io.IOException;
import java.time.LocalDate;

import fr.testfx.personnes.model.Personne;
import fr.testfx.personnes.model.Sexe;
import fr.testfx.personnes.view.PersonneMapping;
import fr.testfx.personnes.view.PersonneMenuMapping;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainClass extends Application {

	//Nous cr�ons des variable de classes afin de pouvoir y acc�der partout
	//Ceci afin de pouvoir y positionner les �l�ments que nous avons fait
	//Il y a un BorderPane car le conteneur principal de notre IHM
	//est un BorderPane, nous reparlerons de l'objet Stage
	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;
	private ObservableList<Personne> listDePersonne = FXCollections.observableArrayList();
	
	public MainClass() {
		listDePersonne.add(new Personne("Proviste", "Alain", LocalDate.of(1970, 1, 1), Sexe.MASCULIN));
		listDePersonne.add(new Personne("D'Arc", "Jeanne", LocalDate.of(1431, 5, 30), Sexe.FEMININ));
		listDePersonne.add(new Personne("Caisse", "Jean", LocalDate.of(1950, 3, 3), Sexe.MASCULIN));
	}
	
	@Override
	public void start(Stage primaryStage) {
		stagePrincipal = primaryStage;
		//Ca ne vous rappelle pas une JFrame ?
		stagePrincipal.setTitle("Application de gestion de personnes");
		
		//Nous allons utiliser nos fichier FXML dans ces deux m�thodes
		initialisationConteneurPrincipal();
		initialisationContenu();
	}

	private void initialisationConteneurPrincipal() {
		//On cr�� un chargeur de FXML
		FXMLLoader loader = new FXMLLoader();
		//On lui sp�cifie le chemin relatif � notre classe
		//du fichier FXML a charger : dans le sous-dossier view
		loader.setLocation(MainClass.class.getResource("view/ConteneurPrincipal.fxml"));
		//*
		try {
			//Le chargement nous donne notre conteneur
			conteneurPrincipal = (BorderPane) loader.load();
			System.out.println(conteneurPrincipal);
			//On d�finit une sc�ne principale avec notre conteneur
			Scene scene = new Scene(conteneurPrincipal);
			//Que nous affectons � notre Stage
			stagePrincipal.setScene(scene);
			
			//Initialisation de notre contr�leur
			PersonneMenuMapping controleur = loader.getController();
			//On sp�cifie la classe principale afin de pour r�cup�rer le Stage
			//Et ainsi fermer l'application
			//controleur.setMainApp(this);
			
			stagePrincipal.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void initialisationContenu() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainClass.class.getResource("view/PersonView.fxml"));
		try {
			//Nous r�cup�rons notre conteneur qui contiendra les donn�es
			//Pour rappel, c'est un AnchorPane...
			AnchorPane conteneurPersonne = (AnchorPane) loader.load();
			//Qui nous ajoutons � notre conteneur principal
			//Au centre, puisque'il s'agit d'un BorderPane
			conteneurPrincipal.setCenter(conteneurPersonne);
			
			//Nous r�cup�rons notre mappeur via l'objet FXMLLoader
			PersonneMapping controleur = loader.getController();
			//Nous lui passons notre instance de classe
			//pour qu'il puisse r�cup�rer notre liste observable
			controleur.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	public ObservableList<Personne> getListDePersonne(){
		return listDePersonne;
	}

}
