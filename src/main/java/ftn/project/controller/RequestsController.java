package ftn.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.Data;

@Data
@Controller
public class RequestsController {

	@GetMapping("/requests")
	public ModelAndView showRequests() {
		return null;
	}
	
}
