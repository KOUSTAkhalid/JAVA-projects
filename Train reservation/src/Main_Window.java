import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


import DATA.Compagnie;

import javax.swing.JTextField;

public class Main_Window {

	protected JFrame mainframe;
	protected Admin_window adm_frame = null;
	
	protected String main_path = System.getProperty("user.dir"); //main path 
	
	protected Image icon = Toolkit.getDefaultToolkit().getImage("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\logo1.png"); //import App icon
	
	protected Icon animationicon = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\animation5.gif");
	
	protected Icon Background = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\background3.jpg");
	
	protected Icon loadingicon = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\loading2.gif");
	
	Compagnie compagnie;
	
	private JTextField loginField;
	private JTextField mtpasseFiled;
	
	JPanel loadingPanel = new JPanel();
	JPanel LoginPanel = new JPanel();
	JPanel panel = new JPanel();
	JPanel BackgroundPanel = new JPanel();
	JPanel bgitems = new JPanel();
	
	Timer timer = null;

	public Main_Window(Compagnie Comp) {
		compagnie = Comp;
		initialize();
		show_widgets();
		set_loading_Panel();
		show_BG();
		
	}

	private void initialize() {
		mainframe = new JFrame(compagnie.nom + " Reservation APP");
		mainframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainframe.setBounds((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/6, 450, 600);
		mainframe.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  
					Object[] options = { "Oui", "Non" };
					int confirmed = JOptionPane.showOptionDialog(null, "Sûr de quitter le programme?", "Quitter",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
							null, options, options[0]);

			    if (confirmed == 0) {
			    	mainframe.dispose();
			    	if (adm_frame != null) {
			    		adm_frame.dispose();
			    	}
			    }
			  }
			});
		mainframe.setIconImage(icon);
		mainframe.setResizable(false);
		mainframe.setVisible(true);
	}
	
	private void show_widgets() {
		
		mainframe.getContentPane().setLayout(null);

		
		
		LoginPanel.setBounds(0, 0, 436, 563);
		mainframe.getContentPane().add(LoginPanel);
		LoginPanel.setOpaque(false);
		LoginPanel.setVisible(false);
		LoginPanel.setLayout(null);
		
		JLabel emailLbl = new JLabel("Nom d'utilisateur");
		emailLbl.setBounds(156, 294, 123, 30);
		emailLbl.setForeground(Color.WHITE);
		emailLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoginPanel.add(emailLbl);
		
		loginField = new JTextField();
		loginField.setBounds(113, 323, 210, 30);
		LoginPanel.add(loginField);
		loginField.setColumns(10);
		
		JLabel lblMonMotDe = new JLabel("Mot de passe");
		lblMonMotDe.setBounds(171, 363, 94, 30);
		lblMonMotDe.setForeground(Color.WHITE);
		lblMonMotDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoginPanel.add(lblMonMotDe);
		
		mtpasseFiled = new JPasswordField();
		mtpasseFiled.setBounds(113, 393, 210, 30);
		mtpasseFiled.setColumns(10);
		LoginPanel.add(mtpasseFiled);
		
		
		
		panel.setBounds(10, 310, 416, 236);
		panel.setOpaque(false);
		mainframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Espace Client");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loading(5000, true);
            	
            	//cw.loading(5000);
			}
		});
		btnNewButton_1.setBounds(32, 155, 165, 40);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Espace Administration");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginPanel.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnNewButton_1_1.setBounds(224, 155, 165, 40);
		panel.add(btnNewButton_1_1);
		
		JButton Retourbutton = new JButton("Retour");
		Retourbutton.setBounds(235, 466, 163, 38);
		Retourbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel.setVisible(false);
				panel.setVisible(true);
			}
		});
		LoginPanel.add(Retourbutton);
		
		
		
		JButton loginbutton = new JButton("Je m'identifie");
		loginbutton.setBounds(42, 466, 163, 38);
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//loading(1000, false);
				if(loginField.getText().equals("ENSET") && mtpasseFiled.getText().equals("ENSET")) {
	            	//mainframe.dispose();
					loading(5000, false);
				}else {
					
				    JOptionPane.showMessageDialog(
						    null, 
						    "quelle est la meilleure filière à l'ENSET Mohammedia :) ?", 
						    "aide",
						    JOptionPane.INFORMATION_MESSAGE);
				
			}
			}
		});
		LoginPanel.add(loginbutton);
		
		
		JTextPane txtpnBienvenueALapplication = new JTextPane();
		txtpnBienvenueALapplication.setBounds(5, 10, 406, 118);
		panel.add(txtpnBienvenueALapplication);
		txtpnBienvenueALapplication.setToolTipText("");
		txtpnBienvenueALapplication.setOpaque(false);
		txtpnBienvenueALapplication.setEditable(false);
		txtpnBienvenueALapplication.setForeground(Color.WHITE);
		txtpnBienvenueALapplication.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtpnBienvenueALapplication.setText("  \t      __________Bienvenue__________\r\n\r\n                       a l'application Desktop du Africa TGV\r\n              ici vous pouvez acheter un billet, retourner un billet,\r\n         v\u00E9rifier les horaires des trains, v\u00E9rifier les villes de passage\r\n   _____________________________________________________");
		
		
	}
	
	protected void set_loading_Panel() {
		
		JLabel IntroLogotxt = new JLabel("");
		IntroLogotxt.setBounds(100, 41, 236, 236);
		IntroLogotxt.setIcon(animationicon);
		bgitems.add(IntroLogotxt);
		
		JLabel madebyPaneltxt = new JLabel("Version 1.0 réalisée par khalid kousta et El arssi zakaria");
		madebyPaneltxt.setForeground(Color.LIGHT_GRAY);
		madebyPaneltxt.setFont(new Font("Tahoma", Font.PLAIN, 9));
		madebyPaneltxt.setBounds(10, 540, 284, 13);
		BackgroundPanel.add(madebyPaneltxt);
		loadingPanel.setBackground(SystemColor.control);
		loadingPanel.setBounds(10, 10, 416, 406);
		loadingPanel.setOpaque(false);
		mainframe.getContentPane().add(loadingPanel);
		loadingPanel.setVisible(false);
		loadingPanel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(58, 53, 300, 300);
		lblNewLabel_6.setIcon(loadingicon);
		lblNewLabel_6.setOpaque(false);
		loadingPanel.add(lblNewLabel_6);
	}
	
	protected void show_BG() {
		BackgroundPanel.setBounds(0, 0, 436, 563);
		mainframe.getContentPane().add(BackgroundPanel);
		BackgroundPanel.setLayout(null);

		bgitems.setBounds(0, 0, 436, 287);
		bgitems.setOpaque(false);
		BackgroundPanel.add(bgitems);
		bgitems.setLayout(null);
		

		
		JLabel bgimage = new JLabel("");
		bgimage.setIcon(Background);
		bgimage.setBounds(0, 0, 436, 563);
		BackgroundPanel.add(bgimage);
		
	}
	
	
	protected void loading(int dur, boolean exit) {
		loadingPanel.setVisible(true);
		LoginPanel.setVisible(false);
		panel.setVisible(false);
		bgitems.setVisible(false);
		
		timer = new Timer(dur, new ActionListener(){      // Timer 4 seconds
            public void actionPerformed(ActionEvent e) {
        		loadingPanel.setVisible(false);
        		LoginPanel.setVisible(false);
        		panel.setVisible(true);
        		bgitems.setVisible(true);
				timer.restart();
				timer.stop();

				
				if (exit) {
					mainframe.dispose();
					new Client_window();
				}else {
					adm_frame = new Admin_window();
				}
            }
        });
		
		timer.start(); 
	}
}
