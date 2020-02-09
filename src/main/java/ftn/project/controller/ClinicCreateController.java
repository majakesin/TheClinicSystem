package ftn.project.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import ftn.project.services.AppointmentService;
import ftn.project.services.ClinicService;
import ftn.project.services.UserService;
import ftn.project.validation.ClinicCreateValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Controller
public class ClinicCreateController {
	
	private final ClinicService clinicService;
	
	private final ClinicCreateValidator ccValidator;
	
	private final UserService userService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(ccValidator);
	}
	
	@GetMapping("/clinics")
	public ModelAndView showClinics(HttpServletRequest request,@ModelAttribute("clinicDto")ClinicDto clinicDto,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		model.addAttribute("clinicsDto",clinicService.allClinics());
	//	model.addAttribute("allClinicsAdmins",userService.allUserByRole("Clinic Administrator"));
		return new ModelAndView("clinics","Model",clinicService.allClinics());
			}else {
				return new ModelAndView("badUser");
			}
			}
	}
	
	
	
	@PostMapping("/clinics/create")
	public String createClinic(@Validated @ModelAttribute("clinicDto")ClinicDto clinicDto,BindingResult result,HttpServletRequest request) {
		if(result.hasErrors()) {
			return "clinics";
		}
		
		clinicService.createClinic(clinicDto);
		return "redirect:/clinics";
	}

	
	@GetMapping("clinics/delete/{idDto}") 
	public String deleteClinic(HttpServletRequest request,@PathVariable("idDto") Long idDto,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:badUser";
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		clinicService.deleteClinic(idDto);
		return "redirect:/clinics";
			}else {
				return "redirect:badUser";
			}
			}
	}
}
