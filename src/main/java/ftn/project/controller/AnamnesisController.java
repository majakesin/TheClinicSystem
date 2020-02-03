package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ftn.project.services.AnamnesisService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/doctors/records/pacient/anamnesis")
public class AnamnesisController {
	
	private final AnamnesisService anamnesisService;
	
	@GetMapping
	public String getPage(HttpServletRequest request,Model model) {
		Long pacient=(Long)request.getSession().getAttribute("pacientId");
		model.addAttribute("allAnamnesis",anamnesisService.getAllAnamnesisByPacientId(pacient));
		return "anamnesis";
		
	}
	
	@GetMapping("/edit/{idDto}")
	public String getEditPage(@PathVariable("idDto")Long idDto,Model model) {
		model.addAttribute("anamDto",anamnesisService.getById(idDto));
		return "anamnesisEdit";
	}
	
	

}
