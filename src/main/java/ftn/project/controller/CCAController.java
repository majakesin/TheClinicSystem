package ftn.project.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class CCAController {

	private UserService userService;
	
	@GetMapping("/administrators")
	public ModelAndView showUsers(@ModelAttribute("userDto")UserDto userDto,ModelMap model) {
		model.addAttribute("usersDto",userService.allUsers());
		return new ModelAndView("administratorRegistration","Model",userService.allUsers());
		
	}
	
	@PostMapping("/administrators/create")
	public String createAdministrator(@Valid @ModelAttribute("userDto") UserDto userDto) {
		userService.createUser(userDto);
		return "redirect:/administrators";
	}
	
	
}
