package ftn.project.controller;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.mapper.RequestMapper;
import ftn.project.model.User;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class PatientController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired 
	RequestMapper requestMapper;
	
	//pisi mapiranja metoda na engleskom
	@GetMapping("/registracija")
	public ModelAndView showRegistrationPage(@ModelAttribute("userDto") UserDto userto,ModelMap model) {
		
		return new ModelAndView("registration");
	}
	@PostMapping("/patient/create")
	public String createPatient(@Valid @ModelAttribute("userDto") UserDto userDto) {
	
			userDto.setRoleDto("pacijent");
			requestService.saveRequest(userDto);
//			userService.createUser(userDto);
      return"redirect:/logovanje";
	}
	
 
	@GetMapping("/logovanje")
	public ModelAndView showLoginPage(@ModelAttribute("userDto") UserDto us, ModelMap m) {
	
		return new ModelAndView("login");
	}
	@PostMapping("/patient/login/")
	public String login(@ModelAttribute("userDto") UserDto us,ModelMap m) {
		return "redirect:/"+userService.autentification(us);
	}
	
	@GetMapping("/badUser")
	public String showBad() {
	
		return "badUser";
	}

	
	
	

	
}