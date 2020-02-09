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
import ftn.project.model.Appointment;
import ftn.project.model.User;

import ftn.project.repository.AppointmentRepository;


import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.ClinicService;
import ftn.project.services.RequestService;
import ftn.project.services.RoomService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SchedulingRequestController {

	
	
	private final RequestService requestService;


	private final AppoitmentRepository sRequestRepository;
	
	private final UserService userService;
	
	private final RoomService roomService;
	
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
		//requestService.acceptRequest(idDto);


		
		

		return "redirect:/freeTerms";
			}else {
				return "redirect:/badUser";
			}
				
			}
	}
	
	@GetMapping("/kreirajNP1/{idDto}")
	public ModelAndView kreirajNP1(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto user,ModelMap model) {
		
		model.addAttribute("doktor",userService.getUserById(idDto));
		
		return new ModelAndView("zakaziNPD1","Model",userService.getUserById(idDto));
	}
	
	
	//zakaziPregledNepredfinisan1
	@PostMapping("/zakazanNP1/{idDto}")
	public String zakazanTerminNEPredefinisan1(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto userVreme , HttpServletRequest request,ModelMap model) {
		UserDto doktor = userService.getUserById(idDto);
		String usernamePacijent = (String) request.getSession().getAttribute("logUsername");
		UserDto pacijent = userService.getUserByUsername(usernamePacijent);
		AppointmentDto app = new AppointmentDto();
		app.setDateDto(userVreme.getDatumPregledaDto());
		app.setTimeDto(userVreme.getVremePregledaDto());
		app.setTypeDto(doktor.getTipPregledaDto());
		app.setDoctorDto(doktor.getIdDto());
		app.setPatientIdDto(pacijent.getIdDto());
		app.setAcceptDto(true);
		
		requestService.createTerm(app);
		
		return "redirect:/clincsSearchDateType";
	
		
	}	
	
	@GetMapping("/kreirajNP2/{idDto}")
	public ModelAndView kreirajNP2(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto user,ModelMap model) {
		
		model.addAttribute("doktor",userService.getUserById(idDto));
		
		return new ModelAndView("zakazanNPD2","Model",userService.getUserById(idDto));
	}
	
	@GetMapping("/kreirajNepredef2/{idDto}")
	public ModelAndView kreirajNP2Profi(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto user,ModelMap model) {
		
		model.addAttribute("doktor",userService.getUserById(idDto));
		
		return new ModelAndView("ZakazanProfil","Model",userService.getUserById(idDto));
	}
	
	//zakaziPregledNepredfinisan1
	@PostMapping("/zakazanNP2/{idDto}")
	public String zakazanTerminNEPredefinisan2(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto userVreme , HttpServletRequest request,ModelMap model) {
		UserDto doktor = userService.getUserById(idDto);
		String usernamePacijent = (String) request.getSession().getAttribute("logUsername");
		UserDto pacijent = userService.getUserByUsername(usernamePacijent);
		AppointmentDto app = new AppointmentDto();
		app.setDateDto(userVreme.getDatumPregledaDto());
		app.setTimeDto(userVreme.getVremePregledaDto());
		app.setTypeDto(doktor.getTipPregledaDto());
		app.setDoctorDto(doktor.getIdDto());
		app.setPatientIdDto(pacijent.getIdDto());
		app.setAcceptDto(true);
		
		requestService.createTerm(app);
		
		return "redirect:/clincsSearchDateType";
	
		
	}
	
	//zakaziPregledNepredfinisan1
		@PostMapping("/zakazanNPD3/{idDto}")
		public String zakazanTerminNEPredefinisan3(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto userVreme , HttpServletRequest request,ModelMap model) {
			UserDto doktor = userService.getUserById(idDto);
			String usernamePacijent = (String) request.getSession().getAttribute("logUsername");
			UserDto pacijent = userService.getUserByUsername(usernamePacijent);
			AppointmentDto app = new AppointmentDto();
			app.setDateDto(userVreme.getDatumPregledaDto());
			app.setTimeDto(userVreme.getVremePregledaDto());
			app.setTypeDto(doktor.getTipPregledaDto());
			app.setDoctorDto(doktor.getIdDto());
			app.setPatientIdDto(pacijent.getIdDto());
			app.setAcceptDto(true);
			
			requestService.createTerm(app);
			
			return "redirect:/listaKlinikaProfil";
		
			
		}
}
