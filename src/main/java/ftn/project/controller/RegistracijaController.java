package ftn.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import ftn.project.validation.IzmeniSifruValidator;
import ftn.project.validation.PatientValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Controller
public class RegistracijaController {

	
	private final UserService userService;
	
	private final PatientValidator patientValidator;
	
	@Autowired
	private RequestService requestService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(patientValidator);
	}
	
	
	
	
	// registracija
		//pisi mapiranja metoda na engleskom
			@GetMapping("/registracija")
			public ModelAndView showRegistrationPage(@ModelAttribute("userDto") UserDto userto,ModelMap model) {
				
				return new ModelAndView("registration");
			}
			@PostMapping("/patient/create")
			public String createPatient(@Validated @ModelAttribute("userDto") UserDto userDto, BindingResult result) {
						
					if(result.hasErrors()) {
					return "registration";
				}
					
					if(userDto.getPasswordDto().equals(userDto.getPomocnaSifraDto())) {
						userDto.setPomocnaSifraDto(null);
						if(userDto.getInsuranceNumberDto().length()==13) {
							
						
					userDto.setRoleDto("pacijent");
					requestService.saveRequest(userDto);
					
		      return"redirect:/logovanje";
					}
					}
				return "redirect:/registracija";
			}
}
