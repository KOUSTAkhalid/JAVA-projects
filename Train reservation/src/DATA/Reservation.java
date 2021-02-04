package DATA;


public class Reservation {
	public String depart; //{"12 Janvier 2021","18:55"}
	public String arrivee;
	public String Heure;
	public String Prix;
	public Client client;
	public String Classe;
	public String Date;
	public String Place;
	
	public String train;
	
	public Reservation(String d, String a, String date, String h, String P, String classe, String place, String tr) {
		// TODO Auto-generated constructor stub
		depart = d;
		arrivee = a;
		Heure = h;
		Prix = P;
		Classe = classe;
		Date = date;
		Place = place;
		train = tr;
	}

}
