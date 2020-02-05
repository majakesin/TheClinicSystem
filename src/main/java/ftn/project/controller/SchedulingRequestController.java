package ftn.project.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.services.AppointmentService;
import ftn.project.services.ClinicService;
import ftn.project.services.RequestService;
import ftn.project.services.RoomService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SchedulingRequestController {

	
	
	private final RequestService requestService;
	
	private final UserService userService;
	
	private final RoomService roomService;
	
	
	@GetMapping("/createTerm")
	public ModelAndView createTerm(HttpServletRequest request,@ModelAttribute("appointmentDto") AppointmentDto appointmentDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		Set<UserDto> medical=new HashSet<UserDto>();
		model.addAttribute("termsDto", requestService.allSchedulingRequest());
		model.addAttribute("allRooms",roomService.allRooms());
		medical.addAll(userService.allMedicalStaff());
		medical.addAll(userService.allNurse());
		model.addAttribute("allMedics",medical);
		return new ModelAndView("createTerm", "Model", requestService.allSchedulingRequest());
			} else {
				return new ModelAndView("badUser");
			}
			
			}

	}

	@PostMapping("/createTerm/create")
	public String createTerms(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto,Model model) {
		requestService.createTerm(appointmentDto);
		
		return "redirect:/createTerm";
	}

	@GetMapping("createTerm/delete/{idDto}")
	public String deleteTerm(HttpServletRequest request,@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		requestService.deleteTerm(idDto);
		return "redirect:/createTerm";
			}else {
				return "redirect:/badUser";
			}
			}
			}
	

	@GetMapping("/appointmentRequests")
	public ModelAndView showRequests(HttpServletRequest request,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		model.addAttribute("appointmentDto", requestService.allNotAccepted());
		return new ModelAndView("schedulingRequest", "Model", requestService.allNotAccepted());
			}else {
				return new ModelAndView("badUser");
			}
			}
			}

	

	@GetMapping("appointmentRequests/accept/{idDto}")
	public String acceptSRequest(HttpServletRequest request,@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		requestService.acceptSchedulingRequest(idDto);
		return "redirect:/appointmentRequests";
			}else {
				return "redirect:/badUser";
			}
			
			}
	}

	@GetMapping("appointmentRequests/reject/{idDto}")
	public String rejectSRequest(HttpServletRequest request,@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			 return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		requestService.rejectSchedulingRequest(idDto);
		return "redirect:/appointmentRequests";
			}else {
				return "redirect:/badUser";
			}
				
			}
	}

	@GetMapping("/freeTerms")
	public ModelAndView showTerms(HttpServletRequest request,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCA() || userService.getPacijent()){
		
		model.addAttribute("termsDto", requestService.allFreeTerms());
		return new ModelAndView("freeTerms", "Model", requestService.allFreeTerms());
			}else { 
				return new ModelAndView("badUser");
			}
			
		}

	}

	@GetMapping("/appointmentRequests/{idDto}")

	public String getSelectedTerms(HttpServletRequest request,@PathVariable("idDto") Long idDto) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			 return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCA()){
		String username=(String)request.getSession().getAttribute("logUsername");
		//requestService.acceptRequest(idDto);


		
		

		return "redirect:/freeTerms";
			}else {
				return "redirect:/badUser";
			}
				
			}
	}
	
	
}
