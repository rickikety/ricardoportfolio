package com.ricardo.portfolio.email;

/**
*
* @author ricardo
*/
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
   
   public void enviarEmail(String destino,String assunto,String msg){
       
		final String username = "ricardoengdepc@gmail.com"; // enter your mail id
		final String password = "rickkety323";// enter ur password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username)); // same email id
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(destino));// whome u have to send mails that person id
			message.setSubject(assunto);
                       message.setContent(msg, "text/html; charset=utf-8");
			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
   }
   
}