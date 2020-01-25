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
import ftn.project.dto.UserDto;
import ftn.project.services.AppointmentService;
import ftn.project.services.RequestService;
import ftn.project.services.RoomService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SchedulingRequestController {

	private final AppointmentService appointmentService;
	
	private final RequestService requestService;
	
	private final UserService userService;
	
	private final RoomService roomService;

	@GetMapping("/createTerm")
	public ModelAndView createTerm(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto, ModelMap model) {
		Set<UserDto> medical=new HashSet<UserDto>();
		model.addAttribute("termsDto", requestService.allSchedulingRequest());
		model.addAttribute("allRooms",roomService.allRooms());
		medical.addAll(userService.allMedicalStaff());
		medical.addAll(userService.allNurse());
		model.addAttribute("allMedics",medical);
		return new ModelAndView("createTerm", "Model", requestService.allSchedulingRequest());

	}

	@PostMapping("/createTerm/create")
	public String createTerms(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto,Model model) {
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
		model.addAttribute("appointmentDto", requestService.allNotAccepted());
		return new ModelAndView("schedulingRequest", "Model", requestService.allNotAccepted());

	}

	@GetMapping("appointmentRequests/accept/{idDto}")
	public String acceptSRequest(@PathVariable("idDto") Long idDto, ModelMap model) {
		requestService.acceptSchedulingRequest(idDto);
		return "redirect:/appointmentRequests";
	}

	@GetMapping("appointmentRequests/reject/{idDto}")
	public String rejectSRequest(@PathVariable("idDto") Long idDto, ModelMap model) {
		requestService.rejectSchedulingRequest(idDto);
		return "redirect:/appointmentRequests";
	}

	@GetMapping("/freeTerms")
	public ModelAndView showTerms(ModelMap model) {
		model.addAttribute("termsDto", requestService.allFreeTerms());
		return new ModelAndView("freeTerms", "Model", requestService.allFreeTerms());

	}

	@GetMapping("/appointmentRequests/{idDto}")
	public String getSelectedTerms(@PathVariable("idDto") Long idDto,HttpServletRequest request) {
		String username=(String)request.getSession().getAttribute("logUsername");
		requestService.acceptRequest(idDto,username);
		return "redirect:/freeTerms";
	}
}
