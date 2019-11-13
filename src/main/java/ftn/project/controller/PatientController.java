package ftn.project.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.model.Role;
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
		
		return new ModelAndView("registrationPatient");
	}
	@PostMapping("/patient/create")
	public String createPatient(@PathParam("potvrda") String potvrda,@Valid @ModelAttribute("userDto") UserDto userDto) {
	
			userDto.setRoleDto("pacijent");
			userService.createUser(userDto);
			return "redirect:/registracija";
	}
}
