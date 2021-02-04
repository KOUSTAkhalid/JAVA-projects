
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import com.mysql.jdbc.ResultSet;
import com.toedter.calendar.JDateChooser;

import DATA.Gare;
import DATA.Train;

public class Admin_window {

	private JFrame adminframe;
	
	protected String main_path = System.getProperty("user.dir"); //main path 
	
	protected Image icon = Toolkit.getDefaultToolkit().getImage("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\logo1.png"); //import App icon
	
	protected Icon backicon = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\back.png"); //import App icon
	
	protected Icon Background = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\background3.jpg");
	
	JPanel BackgroundPanel = new JPanel();
	JPanel MainPanel = new JPanel();
	JPanel AddTrainPanel = new JPanel();
	JPanel AddGaresPanel = new JPanel();
	
	private JTextField PrixADDField;
	private JTextField HeurearriveeADDField;
	private JTextField HeuredepartADDField;
	private JTextField CodeADDField;
	
	//*******************DATABASE
	Connection conn;
	//****************************
	
	//*******************les gare dispo
	ArrayList<String> Gares_Available = new ArrayList<String>();
	//***************************
	
	//*******************Combo Boxs
	JComboBox GareAvailableADD = new JComboBox();
	JComboBox GareAvailablearrADD = new JComboBox();
	
	JComboBox Trainaviable = new JComboBox();
	JComboBox Garechoice = new JComboBox();
	//***************************
	
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model_1 = new DefaultTableModel();
	
	public Admin_window() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenwidth = (int)screenSize.getWidth();
		int a = screenwidth/2 - 800/2;
		
		adminframe = new JFrame("Africa TGV: Espace Administration");
		adminframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		adminframe.setBounds(a, (int)screenSize.getHeight()/6, 800, 600);
		adminframe.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  
					Object[] options = { "Oui", "Non" };
					int confirmed = JOptionPane.showOptionDialog(null, "Sûr de quitter le programme?", "Quitter",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
							null, options, options[0]);

			    if (confirmed == 0) {
			    	adminframe.dispose();
			    }
			  }
			});
		adminframe.setIconImage(icon);
		adminframe.setResizable(false);
		adminframe.getContentPane().setLayout(null);
		
		Connect();
		GET_Trains(); 
		ADD_TRAIN_function();
		
		initialize();
		Show_Background();
		refrechDATA();
		GET_Gares();
	}

	private void initialize() {

		MainPanel.setBounds(0, 0, 786, 563);
		adminframe.getContentPane().add(MainPanel);
		MainPanel.setLayout(null);
		MainPanel.setOpaque(false);
		
		JLabel Userlogo = new JLabel("");
		Userlogo.setBounds(318, 28, 150, 150);
		MainPanel.add(Userlogo);
		Userlogo.setIcon(new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\utilsateur1.png"));
		
		JLabel lblNewLabel = new JLabel("bienvenue Mr Ahmed");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(311, 203, 163, 20);
		MainPanel.add(lblNewLabel);
		
		
		JButton AddTrainButton = new JButton("");
		AddTrainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show_ADD_train();
			}
		});
		AddTrainButton.setIcon(new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\add_train.png"));
		AddTrainButton.setBounds(115, 271, 100, 100);
		MainPanel.add(AddTrainButton);
		AddTrainButton.setOpaque(false);
		AddTrainButton.setContentAreaFilled(false);
		AddTrainButton.setBorderPainted(false);
		
		JButton AddTrainStationButton = new JButton("");
		AddTrainStationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show_ADD_Gares();
			}
		});
		AddTrainStationButton.setIcon(new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\add_trainstation.png"));
		AddTrainStationButton.setBounds(570, 271, 100, 100);
		MainPanel.add(AddTrainStationButton);
		AddTrainStationButton.setOpaque(false);
		AddTrainStationButton.setContentAreaFilled(false);
		AddTrainStationButton.setBorderPainted(false);
		
		JLabel addtraintxt = new JLabel("Ajouter un Train");
		addtraintxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addtraintxt.setForeground(Color.WHITE);
		addtraintxt.setBounds(110, 370, 112, 20);
		MainPanel.add(addtraintxt);
		
		JLabel addstationtxt = new JLabel("Ajouter une Gare");
		addstationtxt.setForeground(Color.WHITE);
		addstationtxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addstationtxt.setBounds(570, 370, 112, 20);
		MainPanel.add(addstationtxt);
		
		
		JButton DeleteButton = new JButton("");
		DeleteButton.setIcon(new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\delete.png"));
		DeleteButton.setBounds(343, 385, 100, 100);
		MainPanel.add(DeleteButton);
		DeleteButton.setOpaque(false);
		DeleteButton.setContentAreaFilled(false);
		DeleteButton.setBorderPainted(false);
		
		JLabel deleteTxt = new JLabel("Suprimer ou Modifier");
		deleteTxt.setForeground(Color.WHITE);
		deleteTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteTxt.setBounds(330, 496, 125, 14);
		MainPanel.add(deleteTxt);
		

	}
	
	
	
	
	/*Object[][] data = {
			{" ", " "," ", " ", " "},
		    {" ", " "," ", " ", " "},
		    {" ", " "," ", " ", " "},
		    {" ", " "," ", " ", " "},
		    {" ", " "," ", " ", " "},
		    {" ", " "," ", " ", " "},
		    {" ", " "," ", " ", " "},
		    {" ", " "," ", " ", " "},
		    {" ", " "," ", " ", " "}
		};*/
	
	String[] columnNames = {"Code","Gare de départ", "Date de depart","Heure",
			"Gare de d'arrivée", "Heure","Prix", "ETAT"};
	String[] columnNames_1 = {"NOM","ADRESSE"};
	private JTable table_1;
	private JTextField Garename;
	private JTextField Gareadresse;
	
	
	
	private void ADD_TRAIN_function() {
		
		AddGaresPanel.setOpaque(false);
		AddGaresPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		AddGaresPanel.setVisible(false);
		AddGaresPanel.setBounds(10, 11, 764, 539);
		adminframe.getContentPane().add(AddGaresPanel);
		AddGaresPanel.setLayout(null);
		
		JLabel lblVousPouvezAjouter = new JLabel("Vous pouvez ajouter ou Supprimer les Gares Ici ");
		lblVousPouvezAjouter.setForeground(Color.WHITE);
		lblVousPouvezAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVousPouvezAjouter.setBounds(203, 11, 357, 25);
		AddGaresPanel.add(lblVousPouvezAjouter);
		
		model_1.setColumnIdentifiers(columnNames_1);
		//********************************************************************
		
		
		AddTrainPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		AddTrainPanel.setBounds(10, 11, 764, 539);
		adminframe.getContentPane().add(AddTrainPanel);
		AddTrainPanel.setOpaque(false);
		AddTrainPanel.setVisible(false);
		AddTrainPanel.setLayout(null);
		
		JLabel LBL = new JLabel("Vous pouvez ajouter et modifier les Trains Ici ");
		LBL.setBounds(211, 11, 342, 25);
		LBL.setFont(new Font("Tahoma", Font.BOLD, 15));
		LBL.setForeground(Color.WHITE);
		AddTrainPanel.add(LBL);
		
		//***************************Table add Trains********************************
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 724, 136);
		
		
		model.setColumnIdentifiers(columnNames);
		
		JTable table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		//********************************************************************
		
		JPanel ADD = new JPanel();
		ADD.setBorder(new TitledBorder(null, "Ajouter un Train", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		ADD.setBounds(10, 50, 744, 144);
		ADD.setOpaque(false);
		AddTrainPanel.add(ADD);
		ADD.setLayout(null);
		
		PrixADDField = new JTextField();
		PrixADDField.setBounds(650, 66, 70, 20);
		ADD.add(PrixADDField);
		PrixADDField.setColumns(10);
		
		HeurearriveeADDField = new JTextField();
		HeurearriveeADDField.setBounds(558, 66, 70, 20);
		ADD.add(HeurearriveeADDField);
		HeurearriveeADDField.setColumns(10);
		
		HeuredepartADDField = new JTextField();
		HeuredepartADDField.setBounds(349, 66, 70, 20);
		ADD.add(HeuredepartADDField);
		HeuredepartADDField.setColumns(10);
		
		CodeADDField = new JTextField();
		CodeADDField.setBounds(23, 66, 70, 20);
		ADD.add(CodeADDField);
		CodeADDField.setColumns(10);
		
		JDateChooser dateChooserADD = new JDateChooser();
		dateChooserADD.setBounds(232, 66, 95, 20);
		ADD.add(dateChooserADD);
		
		
		

		
		JLabel lblNewLabel_1 = new JLabel("Code*");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(23, 47, 50, 14);
		ADD.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date*");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(232, 47, 89, 14);
		ADD.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Heure*");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(349, 47, 50, 14);
		ADD.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Heure*");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(558, 47, 63, 14);
		ADD.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Prix*");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(650, 47, 60, 14);
		ADD.add(lblNewLabel_5);
		

		GareAvailableADD.setBounds(115, 64, 95, 22);
		ADD.add(GareAvailableADD);
		
		JLabel lblNewLabel_1_2 = new JLabel("Gare de d\u00E9part*");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setBounds(115, 47, 89, 14);
		ADD.add(lblNewLabel_1_2);
		

		GareAvailablearrADD.setBounds(441, 64, 95, 22);
		ADD.add(GareAvailablearrADD);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Gare d'ariv\u00E9e*");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setBounds(441, 47, 89, 14);
		ADD.add(lblNewLabel_1_2_1);
		
		JPanel DELETE = new JPanel();
		DELETE.setBorder(new TitledBorder(null, "Supprimer ou Desactiver un Train", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		DELETE.setBounds(10, 205, 744, 125);
		DELETE.setOpaque(false);
		AddTrainPanel.add(DELETE);
		DELETE.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Code");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(206, 45, 97, 14);
		DELETE.add(lblNewLabel_1_1);
		
		
		Trainaviable.setBounds(206, 60, 97, 22);
		
		
		DELETE.add(Trainaviable);
		//====================================================================
		JButton btnSuprimer = new JButton("Suprimer");
		btnSuprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String T = Trainaviable.getSelectedItem().toString();
					delete_train(T);
					refrechDATA();
			}
		});
		btnSuprimer.setBounds(434, 60, 90, 23);
		DELETE.add(btnSuprimer);

		
		JButton btnactive = new JButton("Desactiver");
		btnactive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String T = Trainaviable.getSelectedItem().toString();
				String train_stat = get_train_stat(T);
				
				if (train_stat.equals("ACTIVER")) {
					//System.out.println("act");
					btnactive.setText("Activer");
					change_stat(T, "DESACTIVER");
				}else {
					//System.out.println("dcc");
					btnactive.setText("Desactiver");
					change_stat(T, "ACTIVER");
				}
				
				
				
			}
		});
		btnactive.setBounds(313, 60, 111, 23);
		DELETE.add(btnactive);
		
		
		//============================================================================
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(backicon);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show_Main_Panel();
			}
		});
		btnNewButton.setBounds(656, 1, 126, 50);
		AddTrainPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setIcon(backicon);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show_Main_Panel();
			}
		});
		btnNewButton_1.setBounds(656, 1, 126, 50);
		AddGaresPanel.add(btnNewButton_1);
		
		JPanel ADD_gares = new JPanel();
		ADD_gares.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ajouter Gare", TitledBorder.LEFT, TitledBorder.TOP, null, Color.WHITE));
		ADD_gares.setOpaque(false);
		ADD_gares.setBounds(10, 62, 362, 140);
		AddGaresPanel.add(ADD_gares);
		ADD_gares.setLayout(null);
		
		Garename = new JTextField();
		Garename.setBounds(10, 37, 160, 20);
		ADD_gares.add(Garename);
		Garename.setColumns(10);
		
		Gareadresse = new JTextField();
		Gareadresse.setBounds(10, 76, 342, 20);
		ADD_gares.add(Gareadresse);
		Gareadresse.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Nom");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(10, 22, 46, 14);
		ADD_gares.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Adresse");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(10, 60, 56, 14);
		ADD_gares.add(lblNewLabel_8);
		
		JButton btnNewButton_2 = new JButton("Ajouter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Gare(Garename.getText(), Gareadresse.getText());
				refrechDATA();
				Garename.setText("");
				Gareadresse.setText("");
			}
		});
		btnNewButton_2.setBounds(136, 107, 89, 23);
		ADD_gares.add(btnNewButton_2);
		
		JPanel tableTraindispo = new JPanel();
		tableTraindispo.setOpaque(false);
		tableTraindispo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gares disponible", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		tableTraindispo.setBounds(392, 62, 362, 230);
		AddGaresPanel.add(tableTraindispo);
		tableTraindispo.setLayout(null);
		
		//***************************Table add Gares********************************
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 16, 342, 203);
		tableTraindispo.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(model_1);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_1.setFillsViewportHeight(true);
		table_1.setEnabled(false);
		scrollPane_1.setViewportView(table_1);
		
		JPanel DELETE_gares = new JPanel();
		DELETE_gares.setLayout(null);
		DELETE_gares.setOpaque(false);
		DELETE_gares.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Supprimer Gare", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		DELETE_gares.setBounds(10, 213, 362, 79);
		AddGaresPanel.add(DELETE_gares);
		

		Garechoice.setBounds(10, 38, 160, 22);
		DELETE_gares.add(Garechoice);
		
		JButton btnNewButton_3 = new JButton("Supprimer");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete_gare(Garechoice.getSelectedItem().toString());
			}
		});
		btnNewButton_3.setBounds(214, 38, 100, 23);
		DELETE_gares.add(btnNewButton_3);
		
		JLabel lblNewLabel_9 = new JLabel("Gare");
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(10, 23, 46, 14);
		DELETE_gares.add(lblNewLabel_9);
		
		//***************************************GOOGLE MAP*************************************************
		
		Icon MAP = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\Map.png");
		
		
		JPanel MAPpanel = new JPanel();
		MAPpanel.setOpaque(false);
		MAPpanel.setBorder(new TitledBorder(null, "Carte", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		MAPpanel.setBounds(10, 303, 744, 225);
		AddGaresPanel.add(MAPpanel);
		MAPpanel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(MAP);
		lblNewLabel_6.setBounds(10, 16, 724, 198);
		MAPpanel.add(lblNewLabel_6);
		
		JPanel traindispopanel = new JPanel();
		traindispopanel.setBounds(10, 360, 744, 168);
		AddTrainPanel.add(traindispopanel);
		traindispopanel.setBorder(new TitledBorder(null, "Train disponible", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		traindispopanel.setOpaque(false);
		traindispopanel.setLayout(null);
		traindispopanel.add(scrollPane);
		
		

		
		//*********************************************************************************************************
		//===========================================================================================================================
				JButton addbutton = new JButton("Ajouter");
				addbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
						if(!CodeADDField.getText().isEmpty() && !HeuredepartADDField.getText().isEmpty()
								&& !HeurearriveeADDField.getText().isEmpty() && !PrixADDField.getText().isEmpty()) {
							if(!GareAvailablearrADD.getSelectedItem().toString().equals(GareAvailableADD.getSelectedItem().toString())) {
								
								
								new Train(CodeADDField.getText(), GareAvailableADD.getSelectedItem().toString(), DateFormat.getDateInstance().format(dateChooserADD.getDate()), HeuredepartADDField.getText()
										, GareAvailablearrADD.getSelectedItem().toString(), HeurearriveeADDField.getText(), PrixADDField.getText()+" DH");
								
								CodeADDField.setText("");
								dateChooserADD.setCalendar(null);
								HeuredepartADDField.setText("");
								HeurearriveeADDField.setText("");
								PrixADDField.setText("");
								
								refrechDATA();
								
								
								
							}else {
								JOptionPane.showMessageDialog(null,"veuillez choisir différentes Gares","erreur",JOptionPane.WARNING_MESSAGE);
							}

						
						}else {
							JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.","Information",JOptionPane.WARNING_MESSAGE);
						}
						}catch(Exception e2) {
							JOptionPane.showMessageDialog(null, "Veuillez choisie une date.","Information",JOptionPane.WARNING_MESSAGE);
						}
					}

				});
				addbutton.setBounds(262, 110, 89, 23);
				ADD.add(addbutton);
				
				JButton btnModifier = new JButton("Modifier");
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String T = CodeADDField.getText();
						
						try {
						if(!CodeADDField.getText().isEmpty() && !HeuredepartADDField.getText().isEmpty()
								&& !HeurearriveeADDField.getText().isEmpty() && !PrixADDField.getText().isEmpty()) {
							
						
							
							
									if(((DefaultComboBoxModel)Trainaviable.getModel()).getIndexOf(T) != -1)  {
										update_Train(CodeADDField.getText(), GareAvailableADD.getSelectedItem().toString(), DateFormat.getDateInstance().format(dateChooserADD.getDate()), HeuredepartADDField.getText()
												, GareAvailablearrADD.getSelectedItem().toString(), HeurearriveeADDField.getText(), PrixADDField.getText()+" DH");
										
										CodeADDField.setText("");
										dateChooserADD.setCalendar(null);
										HeuredepartADDField.setText("");
										HeurearriveeADDField.setText("");
										PrixADDField.setText("");
										
										refrechDATA();
										
									}else {
										JOptionPane.showMessageDialog(null, "Aucun Train avec ce Code","Information",JOptionPane.WARNING_MESSAGE);
									}
									
									
									
						}else {
							JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.","Information",JOptionPane.WARNING_MESSAGE);
						}
						}catch(Exception e1) {
							JOptionPane.showMessageDialog(null, "Veuillez choisie une date.","Information",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnModifier.setBounds(392, 110, 90, 23);
				ADD.add(btnModifier);
			//===========================================================================================================================
				
				
		        ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
		            @Override
		            public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
		            	try {
						String T = Trainaviable.getSelectedItem().toString();
						String train_stat = get_train_stat(T);
						
						if (train_stat.equals("ACTIVER")) {
							//System.out.println("act");
							btnactive.setText("Desactiver");
						}else {
							//System.out.println("dcc");
							btnactive.setText("Activer");
						}
					}catch(Exception ee) {
						System.out.println("ex A");
					}}
					
				};
				Trainaviable.addActionListener(cbActionListener);
	}
	
	private void Show_Background() {
		BackgroundPanel.setBounds(0, 0, 786, 563);
		adminframe.getContentPane().add(BackgroundPanel);
		BackgroundPanel.setLayout(null);
		
		JLabel BackgroundLBL = new JLabel("");
		BackgroundLBL.setBounds(0, 0, 786, 563);
		BackgroundLBL.setIcon(new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\background4.jpg"));
		BackgroundPanel.add(BackgroundLBL);
		adminframe.setVisible(true);
	}
	
	
	
	private void Show_ADD_train() {
		MainPanel.setVisible(false);
		AddTrainPanel.setVisible(true);
		AddGaresPanel.setVisible(false);
	}
	
	private void Show_ADD_Gares() {
		MainPanel.setVisible(false);
		AddTrainPanel.setVisible(false);
		AddGaresPanel.setVisible(true);
	}
	
	private void Show_Main_Panel() {
		MainPanel.setVisible(true);
		AddTrainPanel.setVisible(false);
		AddGaresPanel.setVisible(false);
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
			int rows = model.getRowCount(); 
			for(int i = rows - 1; i >=0; i--)
			{
			   model.removeRow(i); 
			}
		
			String sql = "select Code, Gare_dep, Date, Heure_dep, Gare_dar, Heure_dar, Prix, statut from trains";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			
	        rs.beforeFirst();
	        
            //refrech trains
			GET_Trains(); 
			GET_Gares();
	        
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
			    model.addRow(new Object[] {code, Gare_dep, date, Heure_dep, Gare_dar, Heure_dar, Prix, Statut});
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
			Garechoice.removeAllItems();
		    GareAvailableADD.removeAllItems();
			GareAvailablearrADD.removeAllItems();
			
			int rows = model_1.getRowCount(); 
			for(int i = rows - 1; i >=0; i--)
			{
			   model_1.removeRow(i); 
			}
		
			String sql = "select Nom, Adress from gares";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			
	        rs.beforeFirst();
	        
			while (rs.next()) {
			    String Nom = rs.getString("Nom");
			    String Adresse = rs.getString("Adress");
	
			    // create a single array of one row's worth of data
			    GareAvailableADD.addItem(Nom);
				GareAvailablearrADD.addItem(Nom);
				Garechoice.addItem(Nom);
				
				model_1.addRow(new Object[] {Nom, Adresse});
	
			}
		}catch(Exception ex)
			{
			 JOptionPane.showMessageDialog(null, "not working","Information",JOptionPane.WARNING_MESSAGE);
			}
		}
	
	
	private void GET_Trains() {
		// TODO Auto-generated method stub
		try
		{
			Trainaviable.removeAllItems();
		
			String sql = "select Code from trains";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			
	        rs.beforeFirst();
	        
			while (rs.next()) {
			    String Code = rs.getString("Code");
	
			    // create a single array of one row's worth of data
			    Trainaviable.addItem(Code);
			}
		}catch(Exception ex)
			{
			 JOptionPane.showMessageDialog(null, "not working","Information",JOptionPane.WARNING_MESSAGE);
			}
		}
	
	
	
	
	//UPDATE DATA
    public void update_Train(String code, String Garedep, String Date, String Heuredepart
			, String Garearr, String Heurearrivee, String Prix)
    {

        try
        {
        	
        	String sql="UPDATE trains SET Gare_dep ='" + Garedep + "', Date ='" + Date + "', Heure_dep = '" + Heuredepart + "', Gare_dar = '" + Garearr + "', 	Heure_dar = '" + Heurearrivee + "'"
        			+ ", Prix = '" + Prix + "'  WHERE Code='" + code + "'";
            //STATEMENT
        	PreparedStatement ps = conn.prepareStatement(sql);

            //EXECUTE
            ps.execute(sql);
            
            //refrech trains
			GET_Trains(); 


        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
	
    
    //DELETE
    public void delete_train(String code)
    {
        try
        {
    		String sql="DELETE FROM trains WHERE Code ='"+code+"'";

             //STATEMENT
    		PreparedStatement ps = conn.prepareStatement(sql);

            //EXECUTE
            ps.execute(sql);
            
            //refrech trains
			GET_Trains(); 


        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    
    //DELETE
    public void delete_gare(String nom)
    {
        try
        {
    		String sql="DELETE FROM gares WHERE Nom ='"+nom+"'";

             //STATEMENT
    		PreparedStatement ps = conn.prepareStatement(sql);

            //EXECUTE
            ps.execute(sql);
            
            //refrech gares
			GET_Gares(); 


        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    
    public String get_train_stat(String T) {
    	
    	try
		{
    		String stat = "";
			String sql = "select statut FROM trains WHERE Code ='"+T+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			
			while (rs.next()) {
			stat = rs.getString("statut");
			}
			return stat;
	
		
		}catch(Exception ex)
			{
			 JOptionPane.showMessageDialog(null, "not working","Information",JOptionPane.WARNING_MESSAGE);
			 return null;
			}
		}
    
    public void change_stat(String Train, String Statut) {
        try
        {
        	
        	String sql="UPDATE trains SET statut ='" + Statut + "'  WHERE Code='" + Train + "'";
            //STATEMENT
        	PreparedStatement ps = conn.prepareStatement(sql);

            //EXECUTE
            ps.execute(sql);
            
            //refrech trains
			GET_Trains(); 
			refrechDATA();


        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void dispose() {
    	adminframe.dispose();
    }
}

