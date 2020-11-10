package essai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactCommit {
	
	  public static void main(String[] args) {
		    try {
		      Class.forName("org.postgresql.Driver");
		         
		      String url = "jdbc:postgresql://localhost:5432/Ecole";
		      String user = "postgres";
		      String passwd = "PLATON";
		         
		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      
		      // POUR RETIRER LA VALIDATION AUTOMATIQUE EN FIN DE MISE A JOUR
		      conn.setAutoCommit(false);
		      Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		      String query = "UPDATE professeur SET prof_prenom = 'Cyrille' "+"WHERE prof_nom = 'MAMOU'";
		         
		      ResultSet result = state.executeQuery("SELECT * FROM professeur"+" WHERE prof_nom = 'MAMOU'");
		      result.first();
		      System.out.println("NOM : " + result.getString("prof_nom") + " - PRENOM : " + result.getString("prof_prenom"));
		         
		      state.executeUpdate(query);
		      
		      // SI ON VEUT VALIDER MANUELLEMENT LA MISE A JOUR
		      // conn.commit();
		         
		      result = state.executeQuery("SELECT * FROM professeur WHERE prof_nom = 'MAMOU'");
		      result.first();
		      System.out.println("NOM : " + result.getString("prof_nom") + " - PRENOM : " + result.getString("prof_prenom"));
		         
		      result.close();
		      state.close();         
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }
		}
