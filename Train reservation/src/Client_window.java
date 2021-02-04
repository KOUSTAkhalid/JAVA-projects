import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mysql.jdbc.ResultSet;
import com.toedter.calendar.JDateChooser;

import DATA.Client;

import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Client_window {

	protected JFrame clientframe;
	
	protected String main_path = System.getProperty("user.dir"); //main path 
	
	protected Image icon = Toolkit.getDefaultToolkit().getImage("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\logo1.png"); //import App icon
	
	protected Icon loadingicon = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\loading2.gif");
	private int loading_delay = 7000;
	
	protected Icon Background = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\background3.jpg");
	
	Timer timer = null;
	
	private JTable table;
	private JTable table_1;
	private JTextField NomField;
	private JTextField PrenomField;
	private JTextField CINField;
	private JTextField TeleField;
	private JTextField AdresseField;
	private JTextField EmailField;
	
	JPanel loadingPanel = new JPanel();
	JPanel MainPanel = new JPanel();
	JPanel availableTrainpanel = new JPanel();
	
	int dist0 = 60;
	int dist1 = 60;
	
	String[] villes = {"OUJDA", "TAZA", "FES", "RABAT", "CASABLANCA", "EL JADIDA", "MARRAKECH"};
	
	String[] Heures = {"Matinée", "Soir", "Nuit"};
	
	String email = "";

	String Choix_ville_dep= "";
	String Choix_ville_arr= "";
	String Choix_Prix = "";
	String Choix_Heure = "";
	//*******************DATABASE
	Connection conn;
	//****************************
	
	//*******************Combo Boxs
	JComboBox villededepartcb = new JComboBox();
	JComboBox villedariveecb = new JComboBox();
	
	JComboBox ChoixduTrain = new JComboBox();
	//******************************
	
	DefaultTableModel model = new DefaultTableModel();
	
	public Client_window() {
		Connect();
		//GET_Trains();
		GET_Gares();
		initialize();
		refrechDATA();
	}
	
	private String[] remove_lst(String[] v, String Value) {
		// This function to remove choosed cities from derpature
		String[] copy = new String[v.length - 1];
		
		for(int i = 0,j=0; i<v.length; i++) {
			if(v[i] != Value) {
				copy[j++]=v[i];
			}
		}
		return copy;
	}

	private void initialize() {
		clientframe = new JFrame("Africa TGV: Espace Client");
		clientframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		clientframe.setBounds((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/6, 450, 600);
		clientframe.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  
					Object[] options = { "Oui", "Non" };
					int confirmed = JOptionPane.showOptionDialog(null, "Sûr de quitter le programme?", "Quitter",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
							null, options, options[0]);

			    if (confirmed == 0) {
			    	clientframe.dispose();
			    }
			  }
			});
		
		clientframe.setIconImage(icon);
		clientframe.setResizable(false);
		clientframe.getContentPane().setLayout(null);
		clientframe.setVisible(true);
		
				
				
				loadingPanel.setBackground(SystemColor.control);
				loadingPanel.setBounds(10, 10, 416, 406);
				loadingPanel.setOpaque(false);
				clientframe.getContentPane().add(loadingPanel);
				loadingPanel.setLayout(null);
				loadingPanel.setVisible(false);
				
				JLabel lblNewLabel_6 = new JLabel("");
				lblNewLabel_6.setIcon(loadingicon);
				lblNewLabel_6.setBounds(58, 53, 300, 300);
				loadingPanel.add(lblNewLabel_6);
		
		
		MainPanel.setBounds(10, 10, 416, 534);
		MainPanel.setOpaque(false);
		clientframe.getContentPane().add(MainPanel);
		
		
		availableTrainpanel.setBounds(10, 10, 416, 534);
		availableTrainpanel.setOpaque(false);
		clientframe.getContentPane().add(availableTrainpanel);
		availableTrainpanel.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 33, 416, 103);
		availableTrainpanel.add(scrollPane);
		
		String[] columnNames = {"Code","Gare de départ", "Date de depart","Heure",
				"Gare de d'arrivée", "Heure","Prix"};
		
		model.setColumnIdentifiers(columnNames);
		
		JTable table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_8 = new JLabel("Train disponible:");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(10, 10, 129, 13);
		availableTrainpanel.add(lblNewLabel_8);
		
		
		ButtonGroup modePaimG = new ButtonGroup();
		
		JPanel InfopersoPanel = new JPanel();
		InfopersoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		InfopersoPanel.setBounds(0, 280, 416, 238);
		InfopersoPanel.setOpaque(false);
		availableTrainpanel.add(InfopersoPanel);
		InfopersoPanel.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("Information Personnelle: ");
		lblNewLabel_11.setForeground(Color.WHITE);
		lblNewLabel_11.setBounds(30, 10, 171, 13);
		InfopersoPanel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Nom*: ");
		lblNewLabel_12.setForeground(Color.WHITE);
		lblNewLabel_12.setBounds(30, 36, 52, 13);
		InfopersoPanel.add(lblNewLabel_12);
		
		NomField = new JTextField();
		NomField.setBounds(85, 33, 86, 19);
		InfopersoPanel.add(NomField);
		NomField.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Prénom*:");
		lblNewLabel_13.setForeground(Color.WHITE);
		lblNewLabel_13.setBounds(195, 36, 63, 13);
		InfopersoPanel.add(lblNewLabel_13);
		
		PrenomField = new JTextField();
		PrenomField.setColumns(10);
		PrenomField.setBounds(268, 33, 96, 19);
		InfopersoPanel.add(PrenomField);
		
		JLabel lblNewLabel_14 = new JLabel("CIN*:");
		lblNewLabel_14.setForeground(Color.WHITE);
		lblNewLabel_14.setBounds(30, 71, 52, 13);
		InfopersoPanel.add(lblNewLabel_14);
		
		CINField = new JTextField();
		CINField.setBounds(85, 68, 86, 19);
		InfopersoPanel.add(CINField);
		CINField.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Téléphone*:");
		lblNewLabel_15.setForeground(Color.WHITE);
		lblNewLabel_15.setBounds(195, 71, 70, 13);
		InfopersoPanel.add(lblNewLabel_15);
		
		TeleField = new JTextField();
		TeleField.setColumns(10);
		TeleField.setBounds(268, 68, 96, 19);
		InfopersoPanel.add(TeleField);
		
		JLabel lblNewLabel_16 = new JLabel("Adresse:");
		lblNewLabel_16.setForeground(Color.WHITE);
		lblNewLabel_16.setBounds(30, 110, 52, 13);
		InfopersoPanel.add(lblNewLabel_16);
		
		AdresseField = new JTextField();
		AdresseField.setColumns(10);
		AdresseField.setBounds(86, 107, 278, 19);
		InfopersoPanel.add(AdresseField);
		
		JLabel EmailLabel = new JLabel("Email*:");
		EmailLabel.setForeground(Color.WHITE);
		EmailLabel.setBounds(30, 146, 52, 13);
		InfopersoPanel.add(EmailLabel);
		
		EmailField = new JTextField();
		EmailField.setColumns(10);
		EmailField.setBounds(85, 143, 279, 19);
		InfopersoPanel.add(EmailField);
		
		JPanel ChoixTrainPanel = new JPanel();
		ChoixTrainPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ChoixTrainPanel.setBounds(0, 148, 416, 121);
		ChoixTrainPanel.setOpaque(false);
		availableTrainpanel.add(ChoixTrainPanel);
		ChoixTrainPanel.setLayout(null);
		
		JRadioButton parAgence = new JRadioButton("Par agence");
		parAgence.setForeground(Color.WHITE);
		parAgence.setBounds(160, 92, 165, 21);
		parAgence.setOpaque(false);
		ChoixTrainPanel.add(parAgence);
		modePaimG.add(parAgence);
		
		JRadioButton parCartebanc = new JRadioButton("Par Carte Bancaire");
		parCartebanc.setForeground(Color.WHITE);
		parCartebanc.setSelected(true);
		parCartebanc.setBounds(160, 54, 165, 21);
		parCartebanc.setOpaque(false);
		ChoixTrainPanel.add(parCartebanc);
		modePaimG.add(parCartebanc);
		

		ChoixduTrain.setBounds(160, 10, 77, 21);
		ChoixTrainPanel.add(ChoixduTrain);
		
		JLabel lblNewLabel_9 = new JLabel("Choix du Train*: ");
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBounds(10, 14, 98, 13);
		ChoixTrainPanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Mode de paiement*:");
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setBounds(10, 58, 129, 13);
		ChoixTrainPanel.add(lblNewLabel_10);
		availableTrainpanel.setVisible(false);
		MainPanel.setLayout(null);
		
		JPanel firstPanel = new JPanel();
		firstPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		firstPanel.setOpaque(false);
		firstPanel.setBounds(10, 5, 396, 519);
		MainPanel.add(firstPanel);
		firstPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ma gare de départ:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(40, dist0, 122, 13);
		firstPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ma gare d'arrivée:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(40, dist0+dist1, 122, 13);
		firstPanel.add(lblNewLabel_1);
		

		villededepartcb.setBounds(191, dist0-5, 130, 21);
		firstPanel.add(villededepartcb);
		
		
		villedariveecb.setBounds(191, dist0-5+dist1, 130, 21);
		firstPanel.add(villedariveecb);
		
		JLabel lblNewLabel_3 = new JLabel("Date de départ: ");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(40, dist0+dist1*2, 122, 13);
		firstPanel.add(lblNewLabel_3);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDate(new Date());
		dateChooser.setBounds(191, dist0-5+dist1*2, 130, 21);
		firstPanel.add(dateChooser);
		
		JLabel lblNewLabel_4 = new JLabel("Heure de départ: ");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(40, dist0+dist1*3, 122, 13);
		firstPanel.add(lblNewLabel_4);
		
		JComboBox Heurecb = new JComboBox(Heures);
		Heurecb.setBounds(191, dist0-5+dist1*3, 130, 21);
		firstPanel.add(Heurecb);
		
		JLabel lblNewLabel_5 = new JLabel("Nombre de voyageurs:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(40, dist0+dist1*4, 139, 13);
		firstPanel.add(lblNewLabel_5);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 9, 1); //default value,lower bound,upper bound,increment by
		JSpinner Nbvoyageurs = new JSpinner(sm);
		Nbvoyageurs.setBounds(191, dist0-5+dist1*4, 130, 20);
		firstPanel.add(Nbvoyageurs);
		
		ButtonGroup classeG = new ButtonGroup();
		
		JRadioButton firstclassrd = new JRadioButton("Première classe");
		firstclassrd.setActionCommand("1ère classe");
		firstclassrd.setForeground(Color.WHITE);
		firstclassrd.setBounds(40, dist0+dist1*5, 122, 21);
		firstclassrd.setOpaque(false);
		firstPanel.add(firstclassrd);
		classeG.add(firstclassrd);
		
		JRadioButton scndclasserd = new JRadioButton("Deuxième classe");
		scndclasserd.setActionCommand("2ème classe");
		scndclasserd.setForeground(Color.WHITE);
		scndclasserd.setSelected(true);
		scndclasserd.setBounds(191, dist0+dist1*5, 130, 21);
		scndclasserd.setOpaque(false);
		firstPanel.add(scndclasserd);
		classeG.add(scndclasserd);
		
		JLabel lblNewLabel_17 = new JLabel("Centre de Relation Client:  2244");
		lblNewLabel_17.setForeground(Color.WHITE);
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_17.setBounds(40, dist0-5+dist1*6, 266, 13);
		firstPanel.add(lblNewLabel_17);
		
		JLabel lblNewLabel_2 = new JLabel("Je prépare mon voyage");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Quicksand Medium", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(111, 10, 174, 13);
		firstPanel.add(lblNewLabel_2);
		
		
		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.setBounds(144, 458, 107, 33);
		firstPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (villededepartcb.getSelectedItem() != villedariveecb.getSelectedItem()) {
					Object[] options = { "Oui", "Non" };
					int n = JOptionPane.showOptionDialog(null, "Confirmer la recherche?", "Confirmation",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
							null, options, options[0]);
					
					
					if(n == 0) {
						
						Choix_ville_dep = villededepartcb.getSelectedItem().toString(); 
						Choix_ville_arr = villedariveecb.getSelectedItem().toString();
						refrechDATA();
						
				        timer = new Timer(loading_delay, new ActionListener(){      // Timer 4 seconds
				            public void actionPerformed(ActionEvent e) {
								firstPanel.setVisible(false);
								loadingPanel.setVisible(false);
								availableTrainpanel.setVisible(true);
								timer.restart();
								timer.stop();
				            }
				        });
						
						firstPanel.setVisible(false);
						loadingPanel.setVisible(true);
						timer.start();  
						n = 0;
					}
				}else {
					JOptionPane.showMessageDialog(null,"veuillez choisir différentes Gares","erreur",JOptionPane.WARNING_MESSAGE);
				}
		
			}
		});
		
		JLabel lblNewLabel_7 = new JLabel("Version 1.0 réalisée par khalid kousta et El arssi zakaria");
		lblNewLabel_7.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_7.setBounds(10, 540, 284, 13);
		clientframe.getContentPane().add(lblNewLabel_7);
		
		JButton btnNewButton_1 = new JButton("Confirmation");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(parAgence.isSelected()) {
					JOptionPane.showMessageDialog(null, "Code de paiment : AA188452365");
					clientframe.dispose();
					new Client_window();
					
				}else if(!NomField.getText().isEmpty() && !PrenomField.getText().isEmpty() && !CINField.getText().isEmpty() && !TeleField.getText().isEmpty()
						&& !EmailField.getText().isEmpty()) {
					
						if (parCartebanc.isSelected()) {
							email = EmailField.getText();
							
							//******************************************
							
							Get_Prix_heure(ChoixduTrain.getSelectedItem().toString());
							
							Client c = new Client(CINField.getText(), NomField.getText(), PrenomField.getText(), AdresseField.getText(), TeleField.getText(), EmailField.getText(),
									villededepartcb.getSelectedItem().toString(), villedariveecb.getSelectedItem().toString(), DateFormat.getDateInstance().format(dateChooser.getDate()), Choix_Heure,
									Choix_Prix, classeG.getSelection().getActionCommand(), "A55", ChoixduTrain.getSelectedItem().toString());
							
							new Paiment_window(clientframe, c);
							//*******************************************
						}
				}else {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.","Information",JOptionPane.WARNING_MESSAGE);
				}
				
				}
		});
		btnNewButton_1.setBounds(63, 186, 113, 35);
		InfopersoPanel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Retour");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				firstPanel.setVisible(false);
				loadingPanel.setVisible(true);
				availableTrainpanel.setVisible(false);
				
				timer = new Timer(3000, new ActionListener(){      // Timer 4 seconds
		            public void actionPerformed(ActionEvent e) {
						firstPanel.setVisible(true);
						loadingPanel.setVisible(false);
						availableTrainpanel.setVisible(false);
						timer.restart();
						timer.stop();
		            }
		        });
				
				timer.start(); 
			}
		});
		btnNewButton_1_1.setBounds(239, 186, 113, 35);
		InfopersoPanel.add(btnNewButton_1_1);
		
		JPanel BackgroundPanel = new JPanel();
		BackgroundPanel.setBounds(0, 0, 436, 563);
		clientframe.getContentPane().add(BackgroundPanel);
		BackgroundPanel.setLayout(null);
		
		JLabel Backgrountlbl = new JLabel("");
		Backgrountlbl.setIcon(Background);
		Backgrountlbl.setBounds(0, 0, 436, 563);
		BackgroundPanel.add(Backgrountlbl);
		

		
	}
	
	protected void loading(int dur) {
		loadingPanel.setVisible(true);
		MainPanel.setVisible(false);
		availableTrainpanel.setVisible(false);
		
		timer = new Timer(dur, new ActionListener(){      // Timer 4 seconds
            public void actionPerformed(ActionEvent e) {
				MainPanel.setVisible(true);
				loadingPanel.setVisible(false);
				availableTrainpanel.setVisible(false);
				timer.restart();
				timer.stop();
            }
        });
		
		timer.start(); 
	}
	
	
	public void Connect(){
		try
		{
			
		      // create a mysql database connection
		      String myDriver = "com.mysql.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost/africatgv";
		      Class.forName(myDriver);
		      conn = DriverManager.getConnection(myUrl, "root", "");
			  //System.out.print("Database is connected !");

		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données.","Information",JOptionPane.WARNING_MESSAGE);
		}
	
	}
	

	
	
	
	private void refrechDATA() {
		// TODO Auto-generated method stub
		try
		{
			ChoixduTrain.removeAllItems();
			
			int rows = model.getRowCount(); 
			for(int i = rows - 1; i >=0; i--)
			{
			   model.removeRow(i); 
			}
		
			String sql = "select Code, Gare_dep, Date, Heure_dep, Gare_dar, Heure_dar, Prix, statut from trains";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			
	        rs.beforeFirst();
	        
	        
			while (rs.next()) {
			    String code = rs.getString("Code");
			    String Gare_dep = rs.getString("Gare_dep");
			    String date = rs.getString("Date");
			    String Heure_dep = rs.getString("Heure_dep");
			    String Gare_dar = rs.getString("Gare_dar");
			    String Heure_dar = rs.getString("Heure_dar");
			    String Prix = rs.getString("Prix");
			    String Statut = rs.getString("Statut");
	
			    // and add this row of data into the table model
			    if (Statut.equals("ACTIVER") && Gare_dep.equals(Choix_ville_dep) && Gare_dar.equals(Choix_ville_arr)) {
			    model.addRow(new Object[] {code, Gare_dep, date, Heure_dep, Gare_dar, Heure_dar, Prix});
			    ChoixduTrain.addItem(code);
			    }
			}
		}catch(Exception ex)
			{
			 JOptionPane.showMessageDialog(null, "not working","Information",JOptionPane.WARNING_MESSAGE);
			}
		}
	
	
	private void GET_Gares() {
		// TODO Auto-generated method stub
		try
		{
		
			String sql = "select Nom from gares";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			
	        rs.beforeFirst();
	        
			while (rs.next()) {
			    String Nom = rs.getString("Nom");
	
			    // create a single array of one row's worth of data
			    villededepartcb.addItem(Nom);
			    villedariveecb.addItem(Nom);
	
			}
		}catch(Exception ex)
			{
			 JOptionPane.showMessageDialog(null, "not working","Information",JOptionPane.WARNING_MESSAGE);
			}
		}
	
	private void Get_Prix_heure(String T) {
    	try
		{
    		String prix = "";
			String sql = "select Prix,Heure_dep FROM trains WHERE Code ='"+T+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			
			while (rs.next()) {
			Choix_Prix = rs.getString("Prix");
			Choix_Heure = rs.getString("Heure_dep");
			}
	
		
		}catch(Exception ex)
			{
			 JOptionPane.showMessageDialog(null, "not working","Information",JOptionPane.WARNING_MESSAGE);
			}
	}
	
}
