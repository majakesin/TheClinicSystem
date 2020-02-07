package ftn.project.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class PatientController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final UserService userService;
	
	
	private ClinicService clinicService;
	
	
	@Autowired
	private RequestService requestService;
	
	@Autowired 
	RequestMapper requestMapper;
	
	private Set<UserDto> pomocnaTemp = new HashSet<UserDto> ();
	
	
	
	
	//pisi mapiranja metoda na engleskom
	@GetMapping("/registracija")
	public ModelAndView showRegistrationPage(@ModelAttribute("userDto") UserDto userto,ModelMap model) {
		
		return new ModelAndView("registration");
	}
	@PostMapping("/patient/create")
	public String createPatient(@Valid @ModelAttribute("userDto") UserDto userDto) {

			if(userDto.getPasswordDto().equals(userDto.getPomocnaSifraDto())) {
				userDto.setPomocnaSifraDto(null);
			userDto.setRoleDto("pacijent");
			requestService.saveRequest(userDto);
			
      return"redirect:/logovanje";
			}
		return "redirect:/registracija";
	}
	
 
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
	public ModelAndView getEditPatient(HttpServletRequest request,@PathVariable("idDto") Long idDto,@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
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
	public String EditPatient(@ModelAttribute("userDto") UserDto userDto) {
		
		
		userService.createUser(userDto);
		return "redirect:/patientProfile";
	}

	
	
	//za zakazivanje nepredefinisanih termina
	@GetMapping("/zakaziNepredefinisani1/{idDto}")
	public ModelAndView vratiPacijenteNepredefinisani(HttpServletRequest request,@PathVariable("idDto") Long idDto,ModelMap model,@ModelAttribute("doctorDtoPretraga") UserDto doktorDto) {
			ClinicDto clinicDto = clinicService.getClinicById(idDto);
			model.addAttribute("clinicDto",clinicDto);
			
			
			Set<UserDto >doktori = (Set<UserDto>) request.getSession().getAttribute("doktori1");
			Set<UserDto> doktoriPr = (Set<UserDto>) request.getSession().getAttribute("doctorsDto");
			Set<UserDto> pomocna = new HashSet<UserDto>();
			if(doktoriPr==null) {
			
			
			
			if(doktori!=null) {
			for(UserDto dok : doktori) {
				if(dok.getClinicDto()==clinicDto.getIdDto()) {
					pomocna.add(dok);
				}
			}
			}
			else {
				for(UserDto dok : userService.allUserByRole("doktor")) {
					if(dok.getClinicDto()==clinicDto.getIdDto()) {
						pomocna.add(dok);
					}
				}
			}
			model.addAttribute("doctorsDto",pomocna);
			
			pomocnaTemp.addAll(pomocna);
			}
			else {
				model.addAttribute("doctorsDto",doktoriPr);
				pomocnaTemp.addAll(doktoriPr);
			}
			return new ModelAndView("DoktoriNepredefPregled","Model", clinicDto);
			
	
			
	}
	
	@GetMapping("/zakaziNepredefinisani2/{idDto}")
	public ModelAndView vratiPacijenteNepredefinisani2(HttpServletRequest request,@PathVariable("idDto") Long idDto,ModelMap model,@ModelAttribute("doctorDtoP") UserDto doktorDto) {
			ClinicDto clinicDto = clinicService.getClinicById(idDto);
			model.addAttribute("clinicDto",clinicDto);
			
			
			
			Set<UserDto >doktori2 = (Set<UserDto>) request.getSession().getAttribute("doktori2");
			Set<UserDto> doktori1 = (Set<UserDto>) request.getSession().getAttribute("doktori1");
			Set<UserDto> doktoriPr = (Set<UserDto>) request.getSession().getAttribute("doctorsDto");
			Set<UserDto> pomocna = new HashSet<UserDto>();
			if(doktoriPr==null) {
			if(doktori2!=null) {
			for(UserDto dok : doktori2) {
				if(dok.getClinicDto()==clinicDto.getIdDto()) {
					pomocna.add(dok);
				}
			}
			}
			else {
				
				if(doktori1!=null) {
					for(UserDto dok : doktori1) {
						if(dok.getClinicDto()==clinicDto.getIdDto()) {
							pomocna.add(dok);
						}
					
					}
				}
					else {
						for(UserDto dok : userService.allUserByRole("doktor")) {
							if(dok.getClinicDto()==clinicDto.getIdDto()) {
								pomocna.add(dok);
							}
						}
					}
			}
			model.addAttribute("doctorsDto",pomocna);
			
			}
			else {
				model.addAttribute("doctorsDto",doktoriPr);
				pomocnaTemp.addAll(doktoriPr);
			}
			return new ModelAndView("DoktorNepredf2","Model", clinicDto);
			
	
			
	}
	
	//pretraga Doktori1 
	@PostMapping("/doctors/search1/{idDto}")
	public String searchDoctors1(HttpServletRequest request,@PathVariable("idDto") Long idDto, @ModelAttribute("doctorDtoPretraga") UserDto doctorDto, ModelMap model ) {
		
		
		
		
		
		request.getSession().setAttribute("doctorsDto", userService.searchDoctorsNepredefinsani(doctorDto.nameDto, doctorDto.surnameDto, doctorDto.getMarkDto(),pomocnaTemp));
		
		return "redirect:/zakaziNepredefinisani1/"+idDto;
	}
	
	//pretraga Doktori1 
	@PostMapping("/doctors/search2/{idDto}")
	public String searchDoctors2(HttpServletRequest request,@PathVariable("idDto") Long idDto, @ModelAttribute("doctorDtoPretraga") UserDto doctorDto, ModelMap model ) {
		
		
		
		
		
		request.getSession().setAttribute("doctorsDto", userService.searchDoctorsNepredefinsani(doctorDto.nameDto, doctorDto.surnameDto, doctorDto.getMarkDto(),pomocnaTemp));
		
		return "redirect:/zakaziNepredefinisani1/"+idDto;
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
		model.addAttribute(pomocna);
		
		return new ModelAndView("PretragaDoktoraProfil","Model",pomocna);
	}
}