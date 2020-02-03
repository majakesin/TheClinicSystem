package ftn.project.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ftn.project.services.PrescriptionService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/nurse/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

	private final PrescriptionService prescriptionService;

	@GetMapping
	public String getPage(Model model) {
		model.addAttribute("prescriptionsDto", prescriptionService.getAllPrescriptionsByCertified(false));
		return "prescriptions";
	}
	
	@GetMapping("/uncertified")
	public String getUncertified(Model model) {
		model.addAttribute("allUncertified",prescriptionService.getAllPrescriptionsByCertified(false));
		return "uncertified";
	}
	
	@GetMapping("/certified/{idDto}")
	public String certified(@PathVariable("idDto")Long idDto) {
		prescriptionService.certifiedPrescription(idDto);
		return "redirect:/nurse/prescriptions/uncertified";
	}
	
	
	
	
}
