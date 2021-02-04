import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import API.SendEmail;
import API.Ticket;
import DATA.Client;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Paiment_window {

	private JFrame Paimentframe;
	
	protected String main_path = System.getProperty("user.dir"); //main path 
	
	protected Image icon = Toolkit.getDefaultToolkit().getImage("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\logo1.png"); //import App icon
	
	protected Icon visaicon = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\visa.jpg");
	
	protected Icon mastercardicon = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\mastercard.jpg");
	
	protected Icon paypalicon = new ImageIcon("D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\imgs\\paypal.jpg");
	
	private JTextField CarteNbField;
	private JTextField securityField;

	public Paiment_window(JFrame old, Client client) {
		initialize(old, client);
	}

	private void initialize(JFrame old, Client client) {
		Paimentframe = new JFrame("Africa TGV: Paiment par carte bancaire");
		Paimentframe.setBounds(100, 100, 480, 300);
		Paimentframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Paimentframe.setIconImage(icon);
		Paimentframe.setResizable(false);
		Paimentframe.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 10, 446, 44);
		Paimentframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Total a payer");
		lblNewLabel.setBounds(181, 5, 84, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(client.reserv.Prix);
		lblNewLabel_1.setBounds(192, 21, 61, 13);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Confirmation de paiment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!CarteNbField.getText().isEmpty() && !securityField.getText().isEmpty()) {
				Object[] options = { "Oui", "Non" };
				int response = JOptionPane.showOptionDialog(null, "Confirmer le Paiment?", "Confirmation",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
						null, options, options[0]);
				
				if (response == JOptionPane.OK_OPTION)
				{
				   Paimentframe.dispose();
				   
				   old.dispose();
			   	   Client_window nw = new Client_window();
			   	   nw.loading(8000);
			   	   
	               
	               Thread TicketThread = new Thread(new Ticket(client));
	               TicketThread.start();
	               Thread EmailThread = new Thread(new SendEmail(client.Email));
	               EmailThread.start();

				}
				}else {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.","Information",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(128, 229, 210, 28);
		Paimentframe.getContentPane().add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 59, 446, 165);
		Paimentframe.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Selectioner une carte ci-dessous pour payer votre reservation.");
		lblNewLabel_2.setBounds(9, 5, 286, 13);
		panel_1.add(lblNewLabel_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setBounds(17, 32, 32, 21);
		rdbtnNewRadioButton.setSelected(true);
		panel_1.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(66, 28, 69, 33);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(visaicon);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		rdbtnNewRadioButton_1.setBounds(152, 34, 28, 21);
		panel_1.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(197, 27, 69, 33);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(mastercardicon);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");
		rdbtnNewRadioButton_2.setBounds(283, 33, 32, 21);
		panel_1.add(rdbtnNewRadioButton_2);
		
		ButtonGroup PaimG = new ButtonGroup();
		PaimG.add(rdbtnNewRadioButton);
		PaimG.add(rdbtnNewRadioButton_1);
		PaimG.add(rdbtnNewRadioButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(332, 25, 65, 33);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setIcon(paypalicon);
		
		JLabel lblNewLabel_6 = new JLabel("Num\u00E9ro de carte*");
		lblNewLabel_6.setBounds(7, 77, 101, 13);
		panel_1.add(lblNewLabel_6);
		
		CarteNbField = new JTextField();
		CarteNbField.setBounds(115, 74, 293, 19);
		panel_1.add(CarteNbField);
		CarteNbField.setColumns(10);
		
		securityField = new JTextField();
		securityField.setBounds(115, 106, 58, 19);
		panel_1.add(securityField);
		securityField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Code de securit\u00E9*");
		lblNewLabel_7.setBounds(9, 109, 113, 13);
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Expiration*");
		lblNewLabel_8.setBounds(183, 109, 65, 13);
		panel_1.add(lblNewLabel_8);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(362, 106, 46, 19);
		panel_1.add(yearChooser);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(258, 106, 96, 19);
		panel_1.add(monthChooser);
		
		JLabel lblNewLabel_9 = new JLabel("*les champs son obligatoire");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_9.setBounds(9, 142, 149, 13);
		panel_1.add(lblNewLabel_9);
		Paimentframe.setVisible(true);
		
	}
}
