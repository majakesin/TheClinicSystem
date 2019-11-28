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
public class NurseController {

	
	private UserService userService;

	@GetMapping("/nurse")
	public ModelAndView showNursePage(@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		model.addAttribute("nurseDto", userService.allNurse());
		return new ModelAndView("nurseAdd", "Model", userService.allNurse());

	}

	
	@GetMapping("/nurseProfile/edit/{idDto}")
	public ModelAndView showNurseProfileEdit(@ModelAttribute("userDto") UserDto userDto,@PathVariable("idDto") Long idDto, ModelMap model ) {
		
		model.addAttribute("userDto", userService.getUserById(idDto));
		return new ModelAndView("nurseProfileEdit", "Model",userService.getUserById(idDto));

	}
	
	@GetMapping("/nurseProfile")
	public ModelAndView showNurseProfile(@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		Long id=(long) 291;
		model.addAttribute("userDto", userService.getUserById(id));
		return new ModelAndView("nurseProfile", "Model", userService.allNurse());

	}
	
	@PostMapping("/nurse/create")
	public String createNurse(@Valid @ModelAttribute("userDto") UserDto userDto) {
		userDto.setRoleDto("med. sestra");
		userService.createUser(userDto);
		return "redirect:/nurse";
	}
	
	@GetMapping("/nurse/edit/{idDto}")
	public String getEditPage(@PathVariable("idDto") Long idDto, ModelMap model) {
		model.addAttribute("userDto",userService.getUserById(idDto));
		return "nurseEdit";
	}
	

	@GetMapping("/nurse/delete/{idDto}")
	public String deleteNurse(@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.deleteUser(idDto);
		return "redirect:/nurse";
	}
	
	@PostMapping("/nurse/edit/create")
	public String editNurse(@Valid @ModelAttribute("userDto") UserDto userDto) {
		userDto.setRoleDto("med. sestra");
		userService.createUser(userDto);
		return "redirect:/nurse";
	}

	
}
