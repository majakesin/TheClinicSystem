package ftn.project.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.services.UserService;

public class CCAHomeController {

private UserService userService;
	
	@GetMapping("/ccaHome")
	public ModelAndView showUsers(@ModelAttribute("userDto")UserDto userDto,ModelMap model) {
		model.addAttribute("usersDto",userService.allUsers());
		return new ModelAndView("CCAHomeTemplate","Model",userService.allUsers());
		
	}
	
}