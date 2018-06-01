import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
/**
 * This class is used to send attachment email to the user
 * @author Joe
 *
 */
public class SendEmail {
	/**
	 * This method is used to create an new email and send a email with an file attachment to the user
	 * itself
	 * @param email that the user input 
	 * @param password of the user email
	 * @return 0 if the email send successfully
	 */
	public static int sendReportToEmail(String email, String password){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		//check if the use has the correct email and password
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(email, password);
			}
		});
		
		try { 			      
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));//send the email to 
			message.setSubject("Report");//set the subject of the email as "Report"
			MimeBodyPart attachmentPart=new MimeBodyPart();
			String dir = System.getProperty("user.dir"); //get the path of the current directory 
			FileDataSource fileDataSource= new FileDataSource("obtainData.txt");
			attachmentPart.setDataHandler(new DataHandler(fileDataSource));
			attachmentPart.setFileName(fileDataSource.getName());
	
			Multipart multipart=new MimeMultipart();
			multipart.addBodyPart(attachmentPart);// add the attachment to the body of the email
			message.setContent(multipart);
			Transport.send(message); //send the email
			JOptionPane.showMessageDialog(null, "Email send successfully."); 
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error in sending email.");
			return 1;
		}
		return 0;
	}//sendReportToEmail

}//sendEmail
 
