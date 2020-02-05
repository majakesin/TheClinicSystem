package ftn.project.controller;

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

import ftn.project.dto.UserDto;
import ftn.project.mapper.RequestMapper;
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
	
	@Autowired
	private RequestService requestService;
	
	@Autowired 
	RequestMapper requestMapper;
	
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

	
}