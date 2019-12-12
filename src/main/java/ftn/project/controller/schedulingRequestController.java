package ftn.project.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.AppointmentDto;
import ftn.project.services.RequestService;
import lombok.Data;

@Data
@Controller
public class schedulingRequestController {

	
	private final RequestService requestService;
	
	@GetMapping("/createTerm")
	public ModelAndView createTerm(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto, ModelMap model) {
		model.addAttribute("termsDto", requestService.allSchedulingRequest());
		return new ModelAndView("createTerm", "Model", requestService.allSchedulingRequest());

	}
	
	@PostMapping("/createTerm/create")
	public String createTerms(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto) {
		
		requestService.createTerm(appointmentDto);
		return "redirect:/createTerm";
	}
	
	
	@GetMapping("createTerm/delete/{idDto}")
	public String deleteTerm(@PathVariable("idDto") Long idDto, ModelMap model) {
		requestService.deleteTerm(idDto);
		return "redirect:/createTerm";
	}

	
	
	@GetMapping("/appointmentRequests")
	public ModelAndView showRequests(ModelMap model) {
		model.addAttribute("aDto",requestService.allSchedulingRequest());
		return new ModelAndView("schedulingRequest","Model",requestService.allSchedulingRequest());
		
	}
	
	@GetMapping("appointmentRequests/accept/{idDto}") 
	public String acceptSRequest(@PathVariable("idDto") Long idDto,ModelMap model) {
		requestService.acceptSchedulingRequest(idDto);
		return "redirect:/appointmentRequests";
	}
	
	@GetMapping("appointmentRequests/reject/{idDto}") 
	public String rejectSRequest(@PathVariable("idDto") Long idDto,ModelMap model) {
		requestService.rejectSchedulingRequest(idDto);
		return "redirect:/appointmentRequests";
	}
	
	@GetMapping("/freeTerms")
	public ModelAndView showTerms(ModelMap model) {
		model.addAttribute("termsDto",requestService.allSchedulingRequest());
		return new ModelAndView("freeTerms","Model",requestService.allSchedulingRequest());
		
	}
	
	@GetMapping("/appointmentRequests/{idDto}") 
		public ModelAndView getSelectedTerms(@PathVariable("idDto") Long idDto, ModelMap model) {
			AppointmentDto app=requestService.getAppointmentById(idDto);
			Set<AppointmentDto> apoint=new HashSet<AppointmentDto>();
			apoint.add(app);
			model.addAttribute("appointmentDto",apoint) ;
			return new ModelAndView("schedulingRequest","Model",requestService.allSchedulingRequest() );
	}
}
