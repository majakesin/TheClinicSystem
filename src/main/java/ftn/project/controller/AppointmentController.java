package ftn.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.AppointmentDto;
import ftn.project.services.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class AppointmentController {

	private AppointmentService appointmentService;
	
	@GetMapping("/appointment")
	public ModelAndView showAppointments(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto, ModelMap model) {
		model.addAttribute("appointmentsDto", appointmentService.allAppointments());
		return new ModelAndView("doctors", "Model", appointmentService.allAppointments());

	}
}
