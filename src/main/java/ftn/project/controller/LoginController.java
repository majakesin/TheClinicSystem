package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.services.UserService;
import ftn.project.services.VacationRequestService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class LoginController {
	
	
	private final UserService userService;
	
	@GetMapping("/badUser")
	public ModelAndView showUser(ModelMap model) {
		
		return new ModelAndView("badUser");
	}
	
	
	@GetMapping("/izmeniSifru")
	public ModelAndView izmeniStranica(HttpServletRequest request,ModelMap model) {
		String username = (String) request.getSession().getAttribute("logUsername");
		model.addAttribute("UserDto",userService.getUserByUsername(username));		
				return new ModelAndView("izmenaSifre");

	}

	@PostMapping("/izmeniSifruAkcija") 
	public String izmeniAkcija(HttpServletRequest request,ModelMap model,@ModelAttribute("UserDto") UserDto userDto) {
		
		String username = (String) request.getSession().getAttribute("logUsername");
		UserDto temp=userService.getUserByUsername(username);
		
		if(userDto.getPomocnaSifraDto().equals(userDto.getPasswordDto())) {
			temp.setPomocnaSifraDto("");
			temp.setPasswordDto(userDto.getPasswordDto());
			temp.setPrviLoginDto(true);
			userService.createUser(temp);
			
			
			
			if(temp.getRoleDto().equals("doktor")) {
				return "redirect:/patientSearch/doctor";
			}
			else if(temp.getRoleDto().equals("med. sestra"))
					
					return "redirect:/nurseProfile";
			else if(temp.getRoleDto().equals("Clinic Administrator")) {
				return "redirect:/doctors";
				
			}
			
		
		
		}
		return "redirect:/izmeniSifruAkcija";
	}
	
	
	@GetMapping("/odjava")
	public String odjaviSe(HttpServletRequest request,ModelMap model) {
		request.getSession().setAttribute("logUsername", null);
		request.getSession().invalidate();
			
				return  "redirect:/logovanje";

	}
	
}
