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
	public ModelAndView showClinics(@ModelAttribute("clinicDto")ClinicDto clinicDto,ModelMap model) {
		model.addAttribute("clinicsDto",clinicService.allClinics());
	//	model.addAttribute("allClinicsAdmins",userService.allUserByRole("Clinic Administrator"));
		return new ModelAndView("clinics","Model",clinicService.allClinics());
	}
	
	
	
	@PostMapping("/clinics/create")
	public String createClinic(HttpServletRequest request,@Valid @ModelAttribute("clinicDto")ClinicDto clinicDto) {
		clinicService.createClinic(clinicDto);
		return "redirect:/clinics";
	}
	
	@GetMapping("/clinicsProfileCA")
	public String showClinicProfile(HttpServletRequest request,@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		String username=(String)request.getSession().getAttribute("logUsername");
		model.addAttribute("clinicDto", clinicService.getClinicProfile(username));
		return "clinicProfileCA";

	}
	
	@GetMapping("clinics/delete/{idDto}") 
	public String deleteClinic(@PathVariable("idDto") Long idDto,ModelMap model) {
		clinicService.deleteClinic(idDto);
		return "redirect:/clinics";
	}
	
	@GetMapping("/clinicProfileCA/edit/{idDto}")
	public ModelAndView showNurseProfileEdit(@ModelAttribute("clinicDto") ClinicDto clinicDto,@PathVariable("idDto") Long idDto, ModelMap model ) {
		
		model.addAttribute("clinicDto", clinicService.getClinicById(idDto));
		return new ModelAndView("clinicProfileCAEdit", "Model",clinicService.getClinicById(idDto));

	}
	
	@PostMapping("/clinic/edit/ca")
	public String edtClinicCA(@Valid @ModelAttribute("clinicDto")ClinicDto clinicDto) {
		clinicService.editClinicProfile(clinicDto);
		
	return "redirect:/clinicsProfileCA";
	
	}
}
