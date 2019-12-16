package ftn.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.ClinicDto;
import ftn.project.dto.RequestDto;
import ftn.project.services.RequestService;
import lombok.Data;

@Data
@Controller
public class RequestsController {

	@Autowired
	private RequestService requestService;
	
	@GetMapping("/requests")
	public ModelAndView showUsers(ModelMap model) {
		model.addAttribute("requestsDto",requestService.allRequests());
		return new ModelAndView("registrationRequests","Model",requestService.allRequests());
		
	}

	@GetMapping("requests/accept/{idDto}") 
	public String acceptRequest(@PathVariable("idDto") Long idDto,ModelMap model) {
		requestService.acceptUserRequest(idDto);
		return "redirect:/requests";
	}
	
	@GetMapping("requests/reject/{idDto}") 
	public String rejectRequest(@PathVariable("idDto") Long idDto,ModelMap model) {
		requestService.rejectRequest(idDto);
		return "redirect:/requests";
	}
}
