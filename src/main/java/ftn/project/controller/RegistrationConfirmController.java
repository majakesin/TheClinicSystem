package ftn.project.controller;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import ftn.project.mapper.UserMapper;
import ftn.project.model.User;
import ftn.project.model.VerificationToken;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class RegistrationConfirmController {

	private UserService userService;
	
	private final RequestService requestService;
	
	private UserMapper userMapper;
	
	@Autowired
	private MessageSource messages;
	
	@GetMapping("/pacient/create")
	public String confirmRegistration(WebRequest request,Model model,@RequestParam("token")String token) {
		Locale locale = request.getLocale();
	     
	    VerificationToken verificationToken = userService.getVerificationToken(token);
	    if (verificationToken == null) {
	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
	        model.addAttribute("message", message);
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    }
	     
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        String messageValue = messages.getMessage("auth.message.expired", null, locale);
	        model.addAttribute("message", messageValue);
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    } 
	     
	    user.setEnabled(true); 
	    requestService.saveRequest(userMapper.UserToDto(user)); 
	    return "registrationConfirm"; 
	}
}
