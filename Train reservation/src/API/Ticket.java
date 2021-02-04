package API;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JPanel;

import DATA.Client;

public class Ticket implements Runnable{

	private JFrame Ticketframe;
	
	protected Client client;
	
	protected String main_path = System.getProperty("user.dir"); //main path 
	
	protected Icon Background = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\tickets\\Ticket1.png");

	public Ticket(Client c) {
		client = c;
	}
	
	//public static void main(String[] args) {
	//	new Ticket(new Client("FG", "kousta", "khalid", "figuig", "060606", "kousta90@gmail.com", "oujda", "rabat", "10/20/2020", "10:00", "50dh", "1ere", "A20", new Train("A0002")));
	//}
	
	
	private void saveImage(JPanel Ticket){
		    BufferedImage img = new BufferedImage(Ticket.getWidth(), Ticket.getHeight(), BufferedImage.TYPE_INT_RGB);
		    Ticket.print(img.getGraphics()); // or: panel.printAll(...);
		    try {
		    	/*int nwname;
		    	try {
			    	File folder = new File(main_path + "\\tickets\\Clients");
			    	File[] listOfFiles = folder.listFiles();
			    	File lastfile = listOfFiles[listOfFiles.length-1];
			    	nwname = Integer.parseInt(lastfile.getName().substring(0, lastfile.getName().length()-4)) + 1;
		    	}
			    catch(Exception e) {
			    	nwname = 1;
			    }*/
		    	
		    	
		        //ImageIO.write(img, "jpg", new File("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\tickets\\Clients\\" + nwname + ".jpg"));
		    	ImageIO.write(img, "jpg", new File("C:\\Users\\IDEAPAD\\Desktop\\ticket.jpg"));
		    	Ticketframe.dispose();
		    }
		    catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Ticketframe = new JFrame("Ticket");
		Ticketframe.getContentPane().setBackground(Color.WHITE);
		Ticketframe.setBounds(10, 10, 781, 393);
		Ticketframe.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 771, 363);
		Ticketframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel PRIX = new JLabel(client.reserv.Prix);
		PRIX.setFont(new Font("Tahoma", Font.BOLD, 14));
		PRIX.setBounds(95, 147, 121, 13);
		panel.add(PRIX);
		
		JLabel NOMtxt = new JLabel(client.Nom + " " + client.Prenom);
		NOMtxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		NOMtxt.setBounds(407, 147, 121, 13);
		panel.add(NOMtxt);
		
		JLabel DATEtxt = new JLabel(client.reserv.Date);
		DATEtxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		DATEtxt.setBounds(407, 176, 121, 13);
		panel.add(DATEtxt);
		
		JLabel HEUREtxt = new JLabel(client.reserv.Heure);
		HEUREtxt.setBounds(407, 202, 121, 13);
		HEUREtxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(HEUREtxt);
		
		JLabel CLASSEtxt = new JLabel(client.reserv.Classe);
		CLASSEtxt.setBounds(362, 289, 131, 13);
		CLASSEtxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(CLASSEtxt);
		
		JLabel TRAINtxt = new JLabel(client.reserv.train);
		TRAINtxt.setBounds(503, 289, 103, 13);
		TRAINtxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(TRAINtxt);
		
		JLabel PLACEtxt = new JLabel(client.reserv.Place);
		PLACEtxt.setBounds(641, 289, 84, 13);
		PLACEtxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(PLACEtxt);
		
		JLabel departTXT = new JLabel(client.reserv.depart);
		departTXT.setBounds(660, 147, 101, 13);
		departTXT.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(departTXT);
		
		JLabel arriveeTXT = new JLabel(client.reserv.arrivee);
		arriveeTXT.setBounds(660, 176, 101, 13);
		arriveeTXT.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(arriveeTXT);
		
		JLabel BGticket = new JLabel("");
		BGticket.setIcon(Background);
		BGticket.setBounds(0, 0, 771, 363);
		panel.add(BGticket);
		Ticketframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ticketframe.setVisible(false);
		
		
		
		saveImage(panel);
	}
}

