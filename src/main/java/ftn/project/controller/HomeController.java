package ftn.project.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ftn.project.services_impl.ClinicServiceImpl;
import lombok.Data;

@Data
@Controller("/")
public class HomeController {
	
	private ClinicServiceImpl clinicService;
	
	public HomeController(ClinicServiceImpl clinicServiceImpl) {
		this.clinicService=clinicServiceImpl;
	}
	
	@GetMapping("home")
	public String home(Map<String, Object>model) {
		
		return "home";
	}
	
}
