package ftn.project.services_impl;



import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ftn.project.services.EmailService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSenderImpl;
	
	private final Environment env;
	
	@Override
	public void sendMail(String email,String tekst,String subjekat) {
		final String username="tim44isa@gmail.com";
		final String password="timisa44";
		
		Properties properties=new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session=Session.getInstance(properties,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
				
			}
		
		});
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress("tim44isa@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));


			message.setSubject(subjekat);
			message.setContent(tekst,"text/html; charset=utf-8");
			message.setText(tekst);
			

			Transport.send(message);
			System.out.println("Done");
			
		}catch(MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
}
