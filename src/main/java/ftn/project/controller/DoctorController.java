package ftn.project.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Scheduled;
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
import ftn.project.dto.VacationRequestDto;
import ftn.project.services.ClinicService;
import ftn.project.services.UserService;
import ftn.project.services.VacationRequestService;
import ftn.project.validation.DoctorCreateValidator;
import ftn.project.validation.VacationValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Controller
public class DoctorController {

	private final UserService userService;

	private final ClinicService clinicService;

	private final DoctorCreateValidator doctorValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(doctorValidator);
	}

	@GetMapping("/doctors")
	public ModelAndView showUsers(HttpServletRequest request, @ModelAttribute("userDto") UserDto userDto,
			ModelMap model) {

		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getCA()) {

				model.addAttribute("doctorsDto", userService.allMedicalStaff());
				model.addAttribute("allClinics", clinicService.allClinics());
				return new ModelAndView("doctors", "Model", userService.allMedicalStaff());

			}

			else {
				return new ModelAndView("badUser");
			}
		}
	}

	@PostMapping("/doctors/create")
	public String createClinic(Model model,HttpServletRequest request, @Validated @ModelAttribute("userDto") UserDto userDto,
			BindingResult result) {
		
		
		if (result.hasErrors()) {
			model.addAttribute("doctorsDto", userService.allMedicalStaff());
			model.addAttribute("allClinics", clinicService.allClinics());
			return "doctors";
		}

		userDto.setRoleDto("doktor");

		userDto.setPrviLoginDto(false);

		userService.createUser(userDto);
		return "redirect:/doctors";
	}

	@GetMapping("/doctors/delete/{idDto}")
	public String deleteDoctor(HttpServletRequest request, @PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return "redirect:/badUser";
		} else {
			// autorizacija
			if (userService.getCA()) {

				userService.deleteUser(idDto);
				return "redirect:/doctors";
			} else {
				return "redirect:/badUser";
			}
		}
	}

	@GetMapping("/doctors/edit/{idDto}")
	public String getEditPage(HttpServletRequest request, @PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return "redirect:/badUser";
		} else {
			// autorizacija
			if (userService.getCA()) {
				model.addAttribute("userDto", userService.getUserById(idDto));
				return "doctorEdit";
			} else {
				return "redirect:/badUser";
			}
		}
	}

	@PostMapping("/doctors/edit/create")
	public String editDoctor(@Validated @ModelAttribute("userDto") UserDto userDto, BindingResult result) {

		if (result.hasErrors()) {
			return "doctorEdit";
		}

		userDto.setRoleDto("doktor");

		userService.createUser(userDto);
		return "redirect:/doctors";
	}

	// za pretragu

	@GetMapping("/doctorsSearch")
	public ModelAndView searchDoctor(HttpServletRequest request, @ModelAttribute("doctorDto") UserDto doctorDto,
			ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getCA()) {
				ModelAndView mav = new ModelAndView("doctorsSearchAll");

				Set<UserDto> doktori = (Set<UserDto>) request.getSession().getAttribute("doctorsDto");

				if (doktori == null) {
					mav.addObject("doctorsDto", userService.allUserByRole("doktor"));
				} else {
					mav.addObject("doctorsDto", doktori);
				}
				return mav;
			} else {
				return new ModelAndView("badUser");
			}
		}
	}

	@PostMapping("/doctors/search")
	public String searchDoctors(HttpServletRequest request, @ModelAttribute("doctorDto") UserDto doctorDto,
			ModelMap model) {
		request.getSession().setAttribute("doctorsDto",
				userService.searchDoctor(doctorDto.nameDto, doctorDto.surnameDto, doctorDto.getMarkDto()));
		return "redirect:/doctorsSearch";
	}

	// pretraga pacijenata HomePageDoktor
	@GetMapping("/patientSearch/doctor")
	public ModelAndView searchPatientHomePageDoctor(HttpServletRequest request,
			@ModelAttribute("patientDto") UserDto patientDto, ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getDoktor()) {

				ModelAndView mav = new ModelAndView("PatientSearchDoctor");

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

	@PostMapping("/patient/searchD")
	public String searchPatientR(HttpServletRequest request, @ModelAttribute("patientDto") UserDto patientDto,
			ModelMap model) {
		request.getSession().setAttribute("patientsDto", userService.searchPatient(patientDto.nameDto,
				patientDto.surnameDto, patientDto.getInsuranceNumberDto()));
		return "redirect:/patientSearch/doctor";

	}

}
