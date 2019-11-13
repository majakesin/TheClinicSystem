package ftn.project.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.services.ClinicService;
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class DoctorController {

	private UserService userService;

	@GetMapping("/doctors")
	public ModelAndView showUsers(@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		model.addAttribute("doctorsDto", userService.allMedicalStaff());
		return new ModelAndView("doctors", "Model", userService.allMedicalStaff());

	}

	@PostMapping("/doctors/create")
	public String createClinic(@Valid @ModelAttribute("userDto") UserDto userDto) {
		userDto.setRoleDto("doktor");
		userService.createUser(userDto);
		return "redirect:/doctors";
	}

	@GetMapping("doctors/delete/{idDto}")
	public String deleteDoctor(@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.deleteUser(idDto);
		return "redirect:/doctors";
	}

}
