package ftn.project.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.UserDto;
import ftn.project.dto.VacationRequestDto;
import ftn.project.services.UserService;
import ftn.project.services.VacationRequestService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class NurseController {

	
	private UserService userService;
	private final VacationRequestService vqService;

	@GetMapping("/nurse")
	public ModelAndView showNursePage(@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		model.addAttribute("nurseDto", userService.allNurse());
		return new ModelAndView("nurseAdd", "Model", userService.allNurse());

	}
	
	
	@GetMapping("/patientSearch/nurse")
	public ModelAndView searchPatient(HttpServletRequest request, @ModelAttribute("patientDto") UserDto patientDto, ModelMap model) {
		ModelAndView mav=new ModelAndView("patientSearchNurse");
		
		Set<UserDto> pacijenti=(Set<UserDto>)request.getSession().getAttribute("patientsDto");
		
		if(pacijenti==null) {
			mav.addObject("patientsDto", userService.allUserByRole("pacijent"));
		}
		else {
	
		mav.addObject("patientsDto", pacijenti);
		}
		return mav;

	}
	
	@PostMapping("/patient/search")
	public String searchPatientR(HttpServletRequest request,@ModelAttribute("patientDto") UserDto patientDto,ModelMap model) {
		request.getSession().setAttribute("patientsDto", userService.searchPatient(patientDto.nameDto, patientDto.surnameDto, patientDto.getInsuranceNumberDto()));
		return "redirect:/patientSearch/nurse";
		
	}
	
	
	@GetMapping("/nurseProfile/edit/{idDto}")
	public ModelAndView showNurseProfileEdit(HttpServletRequest request,@ModelAttribute("userDto") UserDto userDto,@PathVariable("idDto") Long idDto, ModelMap model ) {
		
		model.addAttribute("userDto", userService.getUserById(idDto));
		return new ModelAndView("nurseProfileEdit", "Model",userService.getUserById(idDto));

	}
	
	@GetMapping("/nurseProfile")
	public ModelAndView showNurseProfile(HttpServletRequest request,@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		String username=(String)request.getSession().getAttribute("logUsername");
		model.addAttribute("userDto", userService.getUserProfile(username));
		return new ModelAndView("nurseProfile", "Model", userService.allNurse());

	}
	
	@PostMapping("/nurse/create")
	public String createNurse(@Valid @ModelAttribute("userDto") UserDto userDto) {
		userDto.setRoleDto("med. sestra");
		userDto.setPrviLoginDto(false);
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
		return "redirect:/nurseProfile";
	}
	
	
	//godisnji odmor
	@GetMapping("/godisnjiOdmorRezervisanjeSestra")
	public ModelAndView rezervisanjeGodisnjeg(HttpServletRequest request,@ModelAttribute("VacationReqDto") VacationRequestDto vacReqDto,ModelMap model) {
		String username = (String) request.getSession().getAttribute("logUsername");
		if(username!=null) {
		UserDto userTemp = userService.getUserByUsername(username);
		
		vacReqDto.setEmailDto(userTemp.emailDto);
		vacReqDto.setNameDto(userTemp.nameDto);
		vacReqDto.setSurnameDto(userTemp.getSurnameDto());
		vacReqDto.setUsernameDto(username);
		vacReqDto.setRoleDto("med. sestra"); }
	
		return new ModelAndView("zakazivanjeGodisnjegSestra");

	}
	
	@PostMapping("/kreirajZahtevGodisnjiSestra")
	public String kreirajZahtev(@Valid @ModelAttribute("VacationReqDto") VacationRequestDto vacReqDto) {
		
		vqService.createVacReq(vacReqDto);
		return "redirect:/godisnjiOdmorRezervisanje";
	}
	
	
}
