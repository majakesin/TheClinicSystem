package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import ftn.project.dto.UserDto;
import ftn.project.services.ClinicService;
import ftn.project.services.UserService;
import ftn.project.validation.CaCcaValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Controller
public class CCAController {

	private final UserService userService;
	private final ClinicService clinicService;
	private final CaCcaValidator CaCcaValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(CaCcaValidator);
	}
	
	@GetMapping("/administrators")
	public ModelAndView showUsers( HttpServletRequest request , @ModelAttribute("userDto")UserDto userDto,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
//		if(userService.getNull()) {
//			return new ModelAndView("badUser");
//		}
//		else {
//			//autorizacija
//			if(userService.getCCA()){
		
		model.addAttribute("usersDto",userService.allUsers());
		
		return new ModelAndView("administratorRegistration","Model",userService.allUsers());
//			}else {
//				return new ModelAndView("badUser");
//			}
//			}
	}
	
	@PostMapping("/administrators/create")
	public String createAdministrator(@Validated @ModelAttribute("userDto") UserDto userDto,BindingResult result) {
		userDto.setPrviLoginDto(false);
		
		if(result.hasErrors()) {
			
			if(userDto.roleDto.equals("Clinic Centar Administrator")) {
				
				return "CCARegistration";
				
			}else 
				
				
			return "CARegistration";
		} 
		
		userService.createUser(userDto);

		if(userDto.roleDto.equals("Clinic Centar Administrator")) {
			
			return "redirect:/cca";
			
		}else 
			
			
		return "redirect:/ca";
			
				
			
		
//		return "redirect:/administrators";

	}
	@GetMapping("/administrators/user/delete/{idDto}")
	public String deleteUser(HttpServletRequest request,@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		userService.deleteUser(idDto);
		return "redirect:/administrators";
			}else {
				return "redirect:/badUser";
			}
			}
	}
	
	
	//dodala novo
	@GetMapping("/cca")
	public ModelAndView showCCA(@ModelAttribute("userDto")UserDto userDto,ModelMap model) {
		model.addAttribute("usersDto",userService.allUserByRole("Clinic Centar Administrator"));
		return new ModelAndView("CCARegistration","Model",userService.allUsers());
	}
	
	@GetMapping("/ca")
	public ModelAndView showCA(@ModelAttribute("userDto")UserDto userDto,ModelMap model) {
		model.addAttribute("usersDto",userService.allUserByRole("Clinic Administrator"));
		model.addAttribute("allClinics",clinicService.allClinics());
		return new ModelAndView("CARegistration","Model",userService.allUsers());
	}
	
}
