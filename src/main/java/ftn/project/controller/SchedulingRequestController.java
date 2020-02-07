package ftn.project.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import ftn.project.repository.SchedulingRequestRepository;
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
	
	@Autowired
	private final SchedulingRequestRepository sRequestRepository;
	
	private final UserService userService;
	
	private final RoomService roomService;
	
	private final ClinicService clinicService;

	private final UserRepository userRepository;
	
	
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

	//izmenjeno predefinisani
	@GetMapping("/listaKlinika")
	public ModelAndView listaKlinika(ModelMap model) {
		model.addAttribute("klinikeDto",clinicService.allClinics() );
		return new ModelAndView("freeTerms", "Model", clinicService.allClinics());

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

	//zakaziTermin
	@GetMapping("/zakazan/{idDto}")
	public String zakazanTerminPredefinisan(@PathVariable("idDto") Long idDto,HttpServletRequest request) {
		String username=(String)request.getSession().getAttribute("logUsername");
		Appointment app = requestService.getAppointmentById(idDto);
		app.setBusy(true);
		app.setAccept(true);
		
		User user=userRepository.findByUsername(username);
		
		sRequestRepository.save(app);
		return "redirect:/listaKlinika";
	}
	
	@GetMapping("/appointmentRequests/{idDto}")
	public String getSelectedTerms(@PathVariable("idDto") Long idDto,HttpServletRequest request) {
		String username=(String)request.getSession().getAttribute("logUsername");
		requestService.acceptRequest(idDto,username);
		return "redirect:/freeTerms";
	}
	
	
}
