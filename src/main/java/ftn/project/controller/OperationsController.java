package ftn.project.controller;

import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.OperationDto;
import ftn.project.services.OperationService;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/operations")
public class OperationsController {

	private final UserService userService;
	
	private final RequestService requestService;
	
	private final OperationService operationService;

	@GetMapping
	public String getPage(Model model) throws MailException, MessagingException {
		model.addAttribute("doctorsList",userService.allUserByRole("doktor"));
		model.addAttribute("appointmentsList",requestService.allSchedulingRequest());
		return "operations";
		
	}
	
	@PostMapping("/sendmail")
	public String getIds(@RequestBody OperationDto operationDto ) {
		operationService.scheduleOperation();
		return "operations";
	}
}
