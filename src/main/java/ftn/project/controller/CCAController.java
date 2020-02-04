package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	private final UserService userService;
	
	@GetMapping("/administrators")
	public ModelAndView showUsers(HttpServletRequest request,@ModelAttribute("userDto")UserDto userDto,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		model.addAttribute("usersDto",userService.allUsers());
		return new ModelAndView("administratorRegistration","Model",userService.allUsers());
			}else {
				return new ModelAndView("badUser");
			}
			}
	}
	
	@PostMapping("/administrators/create")
	public String createAdministrator(@Valid @ModelAttribute("userDto") UserDto userDto) {
		userDto.setPrviLoginDto(false);
		userService.createUser(userDto);
		
		return "redirect:/administrators";
	}
	@GetMapping("/administrators/user/delete/{idDto}")
	public String deleteUser(HttpServletRequest request,@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		userService.deleteUser(idDto);
		return "redirect:/administrators";
			}else {
				return "redirect:/badUser";
			}
			}
	}
	
}
