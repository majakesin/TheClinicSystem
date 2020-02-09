package ftn.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ftn.project.services.RequestService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/operationList")
public class ExaminationListController {

	private final RequestService requestService;
	
	@GetMapping
	public String getPage(Model model) {
		model.addAttribute("operationList",requestService.allNotAcceptedOperation());
		return "operationList";
	}
	
}
