package fr.testfx.personnes.view;

import fr.testfx.personnes.MainClass;
import fr.testfx.personnes.model.Personne;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonneMapping {
	@FXML
    private TableView<Personne> personneTable;
    @FXML
    private TableColumn<Personne, String> nomColonne;
    @FXML
    private TableColumn<Personne, String> prenomColonne;
    @FXML
    private Label nomValeur;
    @FXML
    private Label prenomValeur;
    @FXML
    private Label dateValeur;
    @FXML
    private Label sexeValeur;
    
    //Objet servant de référence à notre classe principale
    //afin de pouvoir récupérer la liste de nos objets.
    private MainClass main;

    //Un constructeur par défaut
    public PersonneMapping() { }

    //Méthode qui initialise notre interface graphique
    //avec nos données métier
    @FXML
    private void initialize() {
        // Initialize the Personne table with the two columns.
        nomColonne.setCellValueFactory(cellData -> cellData.getValue().getNom());
        prenomColonne.setCellValueFactory(cellData -> cellData.getValue().getPrenom());
        
        //Nous récupérons le model de notre tableau (vous connaissez maintenant)
        //où nous récupérons l'item sélectionné et où nous y attachons un écouteur
        //Qui va utiliser notre méthode de mise à jour d'IHM
        personneTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> initializeDescription(newValue));
    }

    //Méthode qui sera utilisée dans l'initialisation de l'IHM
    //dans notre classe principale
    public void setMainApp(MainClass mainApp) {
        this.main = mainApp;
        // On lie notre liste observable au composant TableView
        personneTable.setItems(main.getListDePersonne());
    }
    
  //Méthode qui va mettre les valeurs de notre objet dans les composants
    public void initializeDescription(Personne p) {
    	//On réinitialise par défaut
    	nomValeur.setText("");
    	prenomValeur.setText("");
    	dateValeur.setText("");
    	sexeValeur.setText("");
    	
    	//Si un objet est passé en paramètre, on modifie l'IHM
    	if(p != null) {
    		//ATTENTION : les accesseurs retournent des objets Property Java FX
    		//Pour récupérer leurs vrais valeurs vous devez utiliser la méthode get()
    		nomValeur.setText(p.getNom().get());
    		prenomValeur.setText(p.getPrenom().get());
    		//Sur les deux champs si dessous, en plus de get()
    		//vous devez utiliser toString() car ce sont des objets particuliers
    		dateValeur.setText(p.getDateDeNaissance().get().toString());
    		sexeValeur.setText(p.getSexe().get().toString());
    	}
    }
    
    @FXML
    public void supprimerPersonne() {
    	int index = personneTable.getSelectionModel().getSelectedIndex();
    	//Si aucune ligne n'est sélectionnée, index vaudra -1
    	if (index > -1) {
    		personneTable.getItems().remove(index);
    	}
    	else {
    		Alert probleme = new Alert(AlertType.ERROR);
    		probleme.setTitle("Erreur");
    		probleme.setHeaderText("Veuillez sélectionnez une ligne dans le tableau");
    		probleme.showAndWait();
    	}
    }
    
}
