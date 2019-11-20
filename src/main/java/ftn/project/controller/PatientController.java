package ftn.project.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.repository.UserRepository;
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class PatientController {
	
	private UserService userService;
	
	
	@GetMapping("/registracija")
	public ModelAndView showRegistrationPage(@ModelAttribute("userDto") UserDto userto,ModelMap model) {
		
		return new ModelAndView("registration");
	}
	@PostMapping("/patient/create")
	public String createPatient(@Valid @ModelAttribute("userDto") UserDto userDto) {
	
			userDto.setRoleDto("pacijent");
			userService.createUser(userDto);
			return "redirect:/logovanje";
	}
	
	
	@GetMapping("/logovanje")
	public ModelAndView showLoginPage(@ModelAttribute("userDto") UserDto us, ModelMap m) {
	
		return new ModelAndView("login");
	}
	
	
	@PostMapping("/patient/login/{username}")
	public String loginPatient(@PathParam("username") String username ,ModelMap model) {
		
		if(userService.getUserById(username)!=null) {
			model.addAttribute("ulogovanPacijent", userService.getUserById(username));
		System.out.println("BIO SAM OVDJE");
		return "redirect:/home";
	}
	return "redirect:/logovanje";
}
}