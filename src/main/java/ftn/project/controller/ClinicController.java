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
import ftn.project.dto.UserDto;
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
	
	
	
	
	public String createClinic(@Valid @ModelAttribute("clinicDto")ClinicDto clinicDto) {
		clinicService.createClinic(clinicDto);
		return "redirect:/clinics";
	}
	
	@GetMapping("/clinicsProfileCA")
	public ModelAndView showClinicProfile(@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		Long id = (long) 100;
		model.addAttribute("clinicDto", clinicService.getClinicById(id));
		return new ModelAndView("clinicProfileCA", "Model", clinicService.getClinicById(id));

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
		clinicService.createClinic(clinicDto);
		
	return "redirect:/clinicsProfileCA";
	
	}
}
