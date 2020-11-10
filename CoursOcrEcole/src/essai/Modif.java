package essai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Modif {

	  public static void main(String[] args) {
		    try {
		      Class.forName("org.postgresql.Driver");
		      String url = "jdbc:postgresql://localhost:5432/Ecole";
		      String user = "postgres";
		      String passwd = "PLATON";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      //On autorise la mise � jour des donn�es 
		      //Et la mise � jour de l'affichage
		      Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		      //On va chercher une ligne dans la base de donn�es
		      String query = "SELECT prof_id, prof_nom, prof_prenom FROM professeur " + "WHERE prof_nom = 'MAMOU'";         
		      ResultSet res = state.executeQuery(query);
		      res.first();

		      //On affiche ce que l'on trouve
		      System.out.println("NOM : " + res.getString("prof_nom") + " - PRENOM : " + res.getString("prof_prenom"));

		      //On met � jour les champs
		      res.updateString("prof_nom", "ROI DES PIRATES");
		      res.updateString("prof_prenom", "Luffy");
		      //On valide
		      res.updateRow();

		      //On affiche les modifications
		      System.out.println("*********************************");
		      System.out.println("APRES MODIFICATION : ");
		      System.out.println("\tNOM : " + res.getString("prof_nom") + " - PRENOM : " + res.getString("prof_prenom") + "\n");

		      //On remet les informations de d�part
		      res.updateString("prof_nom", "MAMOU");
		      res.updateString("prof_prenom", "Daniel");
		      //On valide � nouveau
		      res.updateRow();

		      //Et voil� !
		      System.out.println("*********************************");
		      System.out.println("APRES REMODIFICATION : ");
		      System.out.println("\tNOM : " + res.getString("prof_nom") + " - PRENOM : " + res.getString("prof_prenom") + "\n");

		      res.close();
		      state.close();

		    } catch (Exception e) {
		      e.printStackTrace();
		  }
	  }		
	
}
