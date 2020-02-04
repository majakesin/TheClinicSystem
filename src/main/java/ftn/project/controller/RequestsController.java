package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import lombok.Data;

@Data
@Controller
public class RequestsController {

	@Autowired
	private RequestService requestService;
	private final UserService userService;
	
	@GetMapping("/requests")
	public ModelAndView showUsers(HttpServletRequest request,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		model.addAttribute("requestsDto",requestService.allRequests());
		return new ModelAndView("registrationRequests","Model",requestService.allRequests());
			}else {
				return new ModelAndView("badUser");
			}
			}
		
	}

	@GetMapping("requests/accept/{idDto}") 
	public String acceptRequest(HttpServletRequest request,@PathVariable("idDto") Long idDto,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		requestService.acceptUserRequest(idDto);
		return "redirect:/requests";
			}else {
				return "redirect:/badUser";
			}
			}
			}
	
	
	@GetMapping("requests/reject/{idDto}") 
	public String rejectRequest(HttpServletRequest request,@PathVariable("idDto") Long idDto,ModelMap model) {
userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		requestService.rejectRequest(idDto);
		return "redirect:/requests";
			}else {
				return "redirect:/badUser";
			}
			}
	}
}
