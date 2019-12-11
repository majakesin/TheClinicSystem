package ftn.project.services_impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
public class EmailServiceImpl {

   @Autowired
   @Setter //using lombok
   private JavaMailSenderImpl mailSender;

   @Async
   public void send(String to, String subject, String text){

       SimpleMailMessage message = new SimpleMailMessage();
       message.setTo(to);
       message.setSubject(subject);
       message.setText(text);
       mailSender.send(message);
   }
}
