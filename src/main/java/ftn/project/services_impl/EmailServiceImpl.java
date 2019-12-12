package ftn.project.services_impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ftn.project.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSenderImpl;
	
	private final Environment env;
	
	@Override
	public void sendMail(String email) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Primer slanja emaila pomoću asinhronog Spring taska");
		mail.setText("Pozdrav "+ ",\n\nhvala što pratiš ISA.");
		javaMailSenderImpl.send(mail);
	}

}
