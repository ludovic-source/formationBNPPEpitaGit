package essai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSingleton {

		  //URL de connexion
		  private static String url = "jdbc:postgresql://localhost:5432/Ecole";
		  //Nom du user
		  private static String user = "postgres";
		  //Mot de passe de l'utilisateur
		  private static String passwd = "PLATON";
		  //Objet Connection
		  private static Connection connect;
		   
		  //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
		  public static Connection getInstance(){
			    if(connect == null){
			    	 try {
					      connect = DriverManager.getConnection(url, user, passwd);
					    } catch (SQLException e) {
					      e.printStackTrace();
					    }
			    }
			    return connect;   
		  }   
}