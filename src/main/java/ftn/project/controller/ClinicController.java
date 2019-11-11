package ftn.project.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.ClinicDto;
import ftn.project.services.ClinicService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class ClinicController {

	private ClinicService clinicService;
	
	
	@GetMapping("/clinics")
	public ModelAndView showClinics(@ModelAttribute("clinicDto")ClinicDto clinicDto,ModelMap model) {
		model.addAttribute("clinicsDto",clinicService.allClinics());
		return new ModelAndView("clinics","Model",clinicService.allClinics());
	}
	
	@PostMapping("/clinics/create")
	public String createClinic(@Valid @ModelAttribute("clinicDto")ClinicDto clinicDto) {
		clinicService.createClinic(clinicDto);
		return "redirect:/clinics";
	}
	
	@GetMapping("clinics/delete/{idDto}") 
	public String deleteClinic(@PathVariable("idDto") Long idDto,ModelMap model) {
		clinicService.deleteClinic(idDto);
		return "redirect:/clinics";
	}
	
}
