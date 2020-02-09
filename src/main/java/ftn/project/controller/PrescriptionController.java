package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.services.PrescriptionService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/nurse/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

	private final PrescriptionService prescriptionService;
	private final UserService userService;

	@GetMapping
	public String getPage(Model model) {
		model.addAttribute("prescriptionsDto", prescriptionService.getAllPrescriptionsByCertified(false));
		return "prescriptions";
	}

	@GetMapping("/uncertified")
	public ModelAndView getUncertified(Model model,HttpServletRequest request) {
		
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getSestra()) {
				model.addAttribute("allUncertified", prescriptionService.getAllPrescriptionsByCertified(false));
				return new ModelAndView("uncertified");
			} else {
				return new ModelAndView("badUser");
			}
		}

		
	}

	@GetMapping("/certified/{idDto}")
	public String certified(@PathVariable("idDto") Long idDto) {
		prescriptionService.certifiedPrescription(idDto);
		return "redirect:/nurse/prescriptions/uncertified";
	}

}
