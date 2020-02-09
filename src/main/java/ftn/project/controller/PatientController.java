package ftn.project.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
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

import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.mapper.RequestMapper;
import ftn.project.services.ClinicService;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import ftn.project.validation.PatientValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Controller
public class PatientController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final UserService userService;
	
	@Autowired
	private ClinicService clinicService;
	
	
	@Autowired
	private RequestService requestService;
	
	@Autowired 
	RequestMapper requestMapper;
	

	

	
	

	
	
 
	@GetMapping("/logovanje")
	public ModelAndView showLoginPage(@ModelAttribute("userDto") UserDto us, ModelMap m) {
	
		return new ModelAndView("login");
	}
	@PostMapping("/patient/login/")
	public String login(HttpServletRequest request,@ModelAttribute("userDto") UserDto us,ModelMap m) {
		request.getSession().setAttribute("logUsername",us.getUsernameDto());
		return "redirect:/"+userService.autentification(us);
	}
	


	@GetMapping("/patientHome")
	public ModelAndView home(HttpServletRequest request,@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getPacijent()){
		
		
		return new ModelAndView("patientHome");
			}else {
				return new ModelAndView("badUser");
			}
			}
	}
	
	@GetMapping("/patientProfile")
	public ModelAndView profil(HttpServletRequest request,@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getPacijent()){
		
		String username=(String)request.getSession().getAttribute("logUsername");
		model.addAttribute("userDto", userService.getUserProfile(username));
		
		return new ModelAndView("patientProfile", "Model", userService.allUsers());
			}else {
				return new ModelAndView("badUser");
			}
			}
		
	}
	
	@GetMapping("/patientProfile/edit/{idDto}")
	public ModelAndView getEditPatient(HttpServletRequest request,@PathVariable("idDto") Long idDto, @ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getPacijent()){
		
		
		model.addAttribute("userDto",userService.getUserById(idDto));
		return new ModelAndView("patientEdit");
			}else {
				return new ModelAndView("badUser");
			}
			}
	}
	

	@PostMapping("/patientProfile/edit/create/")
	public String EditPatient(@Validated @ModelAttribute("userDto") UserDto userDto, BindingResult result) {
		
		if(result.hasErrors()) {
			return "patientEdit";
		}
		
		userService.createUser(userDto);
		return "redirect:/patientProfile";
	}

	
	

	
	//listaKlinikaProfil
	@GetMapping("/listaKlinikaProfil")
	public ModelAndView listaKlinika(ModelMap model) {
		model.addAttribute("klinikeDto",clinicService.allClinics() );
		return new ModelAndView("listaKlinikaProfil", "Model", clinicService.allClinics());

	}
	
	@GetMapping("/profilKlinikePacijent/{idDto}")
	public ModelAndView profilKlinike(ModelMap model,@PathVariable("idDto") Long idDto) {
		ClinicDto clinic = clinicService.getClinicById(idDto);
		model.addAttribute("clinicDto",clinic);
		
		return new ModelAndView("clinicProfilPatient","Model",clinic);
	}
	
	@GetMapping("/listaDoktoraKlinike/{idDto}")
	public ModelAndView pretragaDoktoraProfil( @ModelAttribute("doctorDto") UserDto doctorDto,ModelMap model,@PathVariable("idDto") Long idDto) {
		Set<UserDto> doktori = userService.allUserByRole("doktor");
		Set<UserDto> pomocna = new HashSet<UserDto>();
		for(UserDto doc : doktori) {
			if(doc.getClinicDto()==idDto) {
				pomocna.add(doc);
			}
		}
		model.addAttribute("doctorsDto",pomocna);
		
		return new ModelAndView("PretragaDoktoraProfil","Model",pomocna);
	}
}