package API;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;


public class SendEmail implements Runnable{
	private String Email;
	protected String main_path = System.getProperty("user.dir"); //main path 
	
	public SendEmail(String email) {
		Email = email;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

        String host="smtp.gmail.com";
        final String user="*************"; //your mail
        final String password="*********"; //your password

        String to = Email;

        //imported code
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //imported code
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Ticket du Reservation");
            
            //message.setText("Merci pour votre Reservation");
            BodyPart messageBodyPart = new MimeBodyPart(); 
            messageBodyPart.setText("Merci pour votre Reservation\n,"
            		+ "projet Realisee par Khalid kousta et El arssi zakaria,\n"
            		+ "pour plus d'information ==> https://github.com/KOUSTAkhalid");
            
            
            
            /*String path = "D:\\EDUCATION\\MECHATRONICS\\CYCLE D'ingenieur\\First Year\\Semester 1\\Programmation Orientee Objet\\Examen\\Train reservation\\tickets\\Clients\\";
            
            
	    	File folder = new File(path);
	    	File[] listOfFiles = folder.listFiles();
	    	File lastfile = listOfFiles[listOfFiles.length-1];
	    	String filename = lastfile.getName();
	    	*/
	    	
            
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource("C:\\Users\\IDEAPAD\\Desktop\\ticket.jpg");// change with path + filename
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("Ticket.jpg");
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            
            message.setContent(multipart);

            Transport.send(message);
            
		    JOptionPane.showMessageDialog(
				    null, 
				    "Merci pour votre Reservation,\n"
				    + "Une ticket a été envoyé sur votre boite email", 
				    "information",
				    JOptionPane.INFORMATION_MESSAGE);

        }
        catch (MessagingException mex)
        {
            JOptionPane.showMessageDialog(null, "veuillez saisir un e-mail fonctionnel","Information",JOptionPane.WARNING_MESSAGE);
        }

    }
}
