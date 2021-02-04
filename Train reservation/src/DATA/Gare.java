package DATA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Gare {
	public String Nom;
	public String Adress;
	public int Num_Train;

	public Gare(String N, String A) {
		// TODO Auto-generated constructor stub
		Nom = N;
		Adress = A;
		Connect();
	}
	
	
	/*public static void main(String[] args) {
	new Gare("CASABLANCA");
}*/
	
	String sql = "INSERT INTO Trains VALUES(?)";
	Connection con;
	//PreparedStatement pst = con.prepareStatement(sql);
	
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
		      String query = " insert into gares (Nom, Adress)"
		        + " values (?, ?)";

		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, Nom);
		      preparedStmt.setString (2, Adress);

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
