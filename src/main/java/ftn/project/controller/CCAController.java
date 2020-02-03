package ftn.project.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.services.ClinicService;
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class CCAController {

	private final UserService userService;
	private final ClinicService clinicService;
	
	@GetMapping("/administrators")
	public ModelAndView showUsers(@ModelAttribute("userDto")UserDto userDto,ModelMap model) {
		model.addAttribute("usersDto",userService.allUsers());
		
		return new ModelAndView("administratorRegistration","Model",userService.allUsers());
	}
	
	@PostMapping("/administrators/create")
	public String createAdministrator(@Valid @ModelAttribute("userDto") UserDto userDto) {
		userDto.setPrviLoginDto(false);
		userService.createUser(userDto);

		if(userDto.roleDto.equals("Clinic Centar Administrator")) {
			
			return "redirect:/cca";
		}else
			
		return "redirect:/ca";

		
//		return "redirect:/administrators";

	}
	@GetMapping("/administrators/user/delete/{idDto}")
	public String deleteUser(@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.deleteUser(idDto);
		return "redirect:/administrators";
	}
	
	
	//dodala novo
	@GetMapping("/cca")
	public ModelAndView showCCA(@ModelAttribute("userDto")UserDto userDto,ModelMap model) {
		model.addAttribute("usersDto",userService.allUserByRole("Clinic Centar Administrator"));
		return new ModelAndView("CCARegistration","Model",userService.allUsers());
	}
	
	@GetMapping("/ca")
	public ModelAndView showCA(@ModelAttribute("userDto")UserDto userDto,ModelMap model) {
		model.addAttribute("usersDto",userService.allUserByRole("Clinic Administrator"));
		model.addAttribute("allClinics",clinicService.allClinics());
		return new ModelAndView("CARegistration","Model",userService.allUsers());
	}
	
}
