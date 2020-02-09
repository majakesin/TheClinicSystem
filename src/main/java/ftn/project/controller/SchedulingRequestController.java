package ftn.project.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.UserDto;
import ftn.project.model.Appointment;
import ftn.project.model.User;


import ftn.project.repository.AppointmentRepository;


import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.ClinicService;
import ftn.project.services.RequestService;
import ftn.project.services.RoomService;
import ftn.project.services.UserService;
import ftn.project.validation.SchedulingValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SchedulingRequestController {

	
	
	private final RequestService requestService;


	private final AppoitmentRepository sRequestRepository;
	
	private final UserService userService;
	
	private final RoomService roomService;
	

	private final SchedulingValidator schedulingValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(schedulingValidator);
	}

	private final ClinicService clinicService;

	private final UserRepository userRepository;

	
	
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
	public String createTerms(@Validated @ModelAttribute("appointmentDto") AppointmentDto appointmentDto,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			Set<UserDto> medical=new HashSet<UserDto>();
			model.addAttribute("termsDto", requestService.allSchedulingRequest());
			model.addAttribute("allRooms",roomService.allRooms());
			medical.addAll(userService.allMedicalStaff());
			medical.addAll(userService.allNurse());
			model.addAttribute("allMedics",medical);
			return "createTerm";
		}
		
		
		roomService.takeRoomPredef(appointmentDto);
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
		Set<AppointmentDto> termini = requestService.allNotAcceptedMaja();
		
				model.addAttribute("appointmentDto", requestService.allNotAcceptedMaja());
		return new ModelAndView("schedulingRequest", "Model", requestService.allNotAcceptedMaja());
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


	//izmenjeno predefinisani
	@GetMapping("/listaKlinika")
	public ModelAndView listaKlinika(ModelMap model,HttpServletRequest request) {

  	userService.Autorizacija(request);
  //autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getPacijent()){
  
		model.addAttribute("klinikeDto",clinicService.allClinics() );
		return new ModelAndView("freeTerms", "Model", clinicService.allClinics());
        	}else { 
				return new ModelAndView("badUser");
			}
    }
}


	
	
	// izmenjeno predefinisani
	@GetMapping("/clinic/terms/{idDto}")
	public ModelAndView prikaziTermineKlinike(@PathVariable("idDto") Long idDto,ModelMap model) {
		
		Set<AppointmentDto> terminiSlobodni = requestService.allFreeTerms();
		Set<AppointmentDto> terminiKlinikeSl = new HashSet<AppointmentDto> ();
			for(AppointmentDto termin : terminiSlobodni) {
				Long id = termin.getDoctorDto();
				UserDto doc = userService.getUserById(id);
				if(doc.getClinicDto()==idDto) {
					terminiKlinikeSl.add(termin);
				}
				
			}
			model.addAttribute("terminiDto",terminiKlinikeSl);
			return new ModelAndView("predefinisaniPregledi","Model",terminiKlinikeSl);
	}

	//zakaziTerminPredefinisan
	@GetMapping("/zakazan/{idDto}")
	public String zakazanTerminPredefinisan(@PathVariable("idDto") Long idDto,HttpServletRequest request) {
		String username=(String)request.getSession().getAttribute("logUsername");
		Appointment app = requestService.getAppointmentById(idDto);
		app.setBusy(true);
		app.setAccept(true);
		
		User user=userRepository.findByUsername(username);
		app.setPacientId(user.getId());

		sRequestRepository.save(app);
		return "redirect:/listaKlinika";
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

		requestService.acceptRequest(idDto,username);
		

		//requestService.acceptRequest(idDto);



		
		

		return "redirect:/freeTerms";
			}else {
				return "redirect:/badUser";
			}
				
			}
	}
	
	
}
