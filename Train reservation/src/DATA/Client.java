package DATA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Client {
	public String CIN;
	public String Nom;
	public String Prenom;
	public String adresse;
	public String N_tele;
	public String Email;
	
	public Reservation reserv;

	public Client(String cin, String nom, String pre, String ad, String num, String mail, String dep, String arriv,String date, String heure, String Prix, String classe, String place, String train) {
		// TODO Auto-generated constructor stub
		CIN = cin;
		Nom = nom;
		Prenom = pre;
		adresse = ad;
		N_tele = num;
		Email = mail;
		reserv = new Reservation(dep, arriv, date, heure, Prix, classe, place, train);
		Connect();
	}
	
	/*
	public void reserver(Train T, Client c, Gare depart, Gare arriver) {
		if (!c.is_reserved) {
			Reservation R = new Reservation(depart, arriver, c);
		}else {
			System.out.println("deja reserver");
		}
	}
	*/

	
	
	
	/*public static void main(String[] args) {
	new Gare("CASABLANCA");
}*/
	
	public void Connect(){
		try
		{
			
		      // create a mysql database connection
		      String myDriver = "com.mysql.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost/africatgv";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "");
			  //System.out.print("Database is connected !");

		      // the mysql insert statement
		      String query = " insert into clients (Nom, Prenom, CIN, Adresse, télé, Email, Départ, Arrivée, Date, Heure, Classe)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		      //INSERT DELETE SELECT UPDATE
		      
		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, Nom);
		      preparedStmt.setString (2, Prenom);
		      preparedStmt.setString (3, CIN);
		      preparedStmt.setString (4, adresse);
		      preparedStmt.setString (5, N_tele);
		      preparedStmt.setString (6, Email);
		      preparedStmt.setString (7, reserv.depart);
		      preparedStmt.setString (8, reserv.arrivee);
		      preparedStmt.setString (9, reserv.Date);
		      preparedStmt.setString (10, reserv.Heure);
		      preparedStmt.setString (11, reserv.Classe);

		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      conn.close();
		}
		catch(Exception e)
		{
			System.out.print("Do not connect to DB - Error:"+e);
		}
	
	}

	
	
	
}






