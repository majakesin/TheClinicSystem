package ftn.project.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.services.ClinicService;
import ftn.project.services.UserService;
import ftn.project.validation.NurseValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Controller
public class NurseController {

	private final UserService userService;
	private final ClinicService clinicService;

	private final NurseValidator nurseValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(nurseValidator);
	}

	@GetMapping("/nurse")
	public ModelAndView showNursePage(HttpServletRequest request, @ModelAttribute("userDto") UserDto userDto,
			ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getCA()) {

				model.addAttribute("nurseDto", userService.allNurse());
				model.addAttribute("allClinics", clinicService.allClinics());
				return new ModelAndView("nurseAdd", "Model", userService.allNurse());
			} else {
				return new ModelAndView("badUser");
			}
		}

	}

	@GetMapping("/patientSearch/nurse")
	public ModelAndView searchPatient(HttpServletRequest request, @ModelAttribute("patientDto") UserDto patientDto,
			ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getSestra()) {

				ModelAndView mav = new ModelAndView("patientSearchNurse");

				Set<UserDto> pacijenti = (Set<UserDto>) request.getSession().getAttribute("patientsDto");

				if (pacijenti == null) {
					mav.addObject("patientsDto", userService.allUserByRole("pacijent"));
				} else {

					mav.addObject("patientsDto", pacijenti);
				}
				return mav;
			} else {
				return new ModelAndView("badUser");
			}
		}

	}

	@PostMapping("/patient/search")
	public String searchPatientR(HttpServletRequest request, @ModelAttribute("patientDto") UserDto patientDto,
			ModelMap model) {
		request.getSession().setAttribute("patientsDto", userService.searchPatient(patientDto.nameDto,
				patientDto.surnameDto, patientDto.getInsuranceNumberDto()));
		return "redirect:/patientSearch/nurse";

	}

	@GetMapping("/nurseProfile/edit/{idDto}")
	public ModelAndView showNurseProfileEdit(HttpServletRequest request, @ModelAttribute("userDto") UserDto userDto,
			@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getSestra()) {

				model.addAttribute("userDto", userService.getUserById(idDto));
				return new ModelAndView("nurseProfileEdit", "Model", userService.getUserById(idDto));
			} else {
				return new ModelAndView("badUser");
			}
		}

	}

	@GetMapping("/nurseProfile")
	public ModelAndView showNurseProfile(HttpServletRequest request, @ModelAttribute("userDto") UserDto userDto,
			ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getSestra()) {

				String username = (String) request.getSession().getAttribute("logUsername");
				model.addAttribute("userDto", userService.getUserProfile(username));
				return new ModelAndView("nurseProfile", "Model", userService.allNurse());
			} else {
				return new ModelAndView("badUser");
			}
		}

	}

	@PostMapping("/nurse/create")
	public String createNurse(Model model, @Validated @ModelAttribute("userDto") UserDto userDto,
			BindingResult result) {
		userDto.setRoleDto("med. sestra");
		userDto.setPrviLoginDto(false);
		if (result.hasErrors()) {
			model.addAttribute("nurseDto", userService.allNurse());
			model.addAttribute("allClinics", clinicService.allClinics());
			return "nurseAdd";
			
		}
		userService.createUser(userDto);
		return "redirect:/nurse";
	}

	@GetMapping("/nurse/edit/{idDto}")
	public String getEditPage(HttpServletRequest request, @PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return "redirect:/badUser";
		} else {
			// autorizacija
			if (userService.getSestra()) {

				model.addAttribute("userDto", userService.getUserById(idDto));
				return "nurseEdit";
			} else {
				return "redirect:/badUser";
			}
		}
	}

	@GetMapping("/nurse/delete/{idDto}")
	public String deleteNurse(HttpServletRequest request, @PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return "redirect:badUser";
		} else {
			// autorizacija
			if (userService.getCA()) {

				userService.deleteUser(idDto);
				return "redirect:/nurse";
			} else {
				return "redirect:badUser";
			}
		}
	}

	@PostMapping("/nurse/edit/create")
	public String editNurse(@Validated @ModelAttribute("userDto") UserDto userDto, BindingResult result) {
		userDto.setRoleDto("med. sestra");
		if (result.hasErrors()) {
			return "nurseProfileEdit";
		}
		userService.createUser(userDto);
		return "redirect:/nurseProfile";
	}

}
