package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.services.ClinicService;
import ftn.project.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Controller
public class ClinicController {

	private final ClinicService clinicService;
	
	private final UserService userService;
	
	
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
		model.addAttribute("allClinicsAdmins",userService.allUserByRole("Clinic Administrator"));
		return new ModelAndView("clinics","Model",clinicService.allClinics());
			}else {
				return new ModelAndView("badUser");
			}
			}
	}
	
	
	
	@PostMapping("/clinics/create")
	public String createClinic(HttpServletRequest request,@Valid @ModelAttribute("clinicDto")ClinicDto clinicDto) {
		clinicService.createClinic(clinicDto);
		return "redirect:/clinics";
	}
	
	@GetMapping("/clinicsProfileCA")
	public String showClinicProfile(HttpServletRequest request,@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		String username=(String)request.getSession().getAttribute("logUsername");
		model.addAttribute("clinicDto", clinicService.getClinicProfile(username));
		return "clinicProfileCA";
			}else {
				return "redirect:/badUser";
			}
				
			}

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
	
	@GetMapping("/clinicProfileCA/edit/{idDto}")
	public ModelAndView showNurseProfileEdit(HttpServletRequest request,@ModelAttribute("clinicDto") ClinicDto clinicDto,@PathVariable("idDto") Long idDto, ModelMap model ) {
		
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		
		model.addAttribute("clinicDto", clinicService.getClinicById(idDto));
		return new ModelAndView("clinicProfileCAEdit", "Model",clinicService.getClinicById(idDto));

			}else {
				return new ModelAndView("badUser");
			}
			}
	}
	
	@PostMapping("/clinic/edit/ca")
	public String edtClinicCA(@Valid @ModelAttribute("clinicDto")ClinicDto clinicDto) {
		clinicService.editClinicProfile(clinicDto);
		
	return "redirect:/clinicsProfileCA";
	
	}
}
