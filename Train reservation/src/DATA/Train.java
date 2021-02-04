package DATA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Train {
	public String Code;
	protected String Gare_dep;
	protected String Date;
	protected String Heure_dep;
	protected String Gare_dar;
	protected String Heure_dar;
	protected String Prix; 
	
	protected String statut = "ACTIVER";
	
	//private int nombre_voiture = 10;
	//private int nombre_place = 50;
	
	
	/*public static void main(String[] args) {
		new Train("A001", "OUJDA", "10/01/2021", "10:00", "CASABLANCA", "20:00", "200 DH", true);
	}*/
	
	
	public Train(String c,String Gd, String dt, String hd,String Ga, String ha, String p) {
		// TODO Auto-generated constructor stub
		Code = c;
		Gare_dep = Gd;
		Date = dt;
		Heure_dep = hd;
		Gare_dar = Ga;
		Heure_dar = ha;
		Prix = p;
		Connect();
	}
	
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
		      String query = " insert into trains (Code, Gare_dep, Date, Heure_dep, Gare_dar, Heure_dar, Prix, statut)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?)";

		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, Code);
		      preparedStmt.setString (2, Gare_dep);
		      preparedStmt.setString (3, Date);
		      preparedStmt.setString (4, Heure_dep);
		      preparedStmt.setString (5, Gare_dar);
		      preparedStmt.setString (6, Heure_dar);
		      preparedStmt.setString (7, Prix);
		      preparedStmt.setString (8, statut);

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
	
