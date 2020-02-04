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
import ftn.project.repository.UserRepository;
import ftn.project.services.UserService;
import ftn.project.services.VacationRequestService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class DoctorController {

	private final UserService userService;
	private final VacationRequestService vqService;
	
	
	
	

	@GetMapping("/doctors")
	public ModelAndView showUsers(HttpServletRequest request,@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		model.addAttribute("doctorsDto", userService.allMedicalStaff());
		return new ModelAndView("doctors", "Model", userService.allMedicalStaff());

		}
		
		else {
			return new ModelAndView("badUser");
		}
		}
	}
	

	@PostMapping("/doctors/create")
	public String createClinic(HttpServletRequest request, @Valid @ModelAttribute("userDto") UserDto userDto) {
		

		userDto.setRoleDto("doktor");
		userDto.setPrviLoginDto(false);
		userService.createUser(userDto);
		return "redirect:/doctors";
	}

	
	@GetMapping("/doctors/delete/{idDto}")
	public String deleteDoctor(HttpServletRequest request,@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		userService.deleteUser(idDto);
		return "redirect:/doctors";
			}else {
				return "redirect:/badUser";
			}
		}
	}
	
	@GetMapping("/doctors/edit/{idDto}")
	public String getEditPage(HttpServletRequest request,@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
		//autorizacija
		if(userService.getCA()){
		model.addAttribute("userDto",userService.getUserById(idDto));
		return "doctorEdit";
		}else {
			return "redirect:/badUser";
		}
		}
	}
	
	@PostMapping("/doctors/edit/create")
	public String editDoctor(@Valid @ModelAttribute("userDto") UserDto userDto) {
		userDto.setRoleDto("doktor");
		userService.createUser(userDto);
		return "redirect:/doctors";
	}
	
	//za pretragu
	
	@GetMapping("/doctorsSearch")
	public ModelAndView searchDoctor(HttpServletRequest request, @ModelAttribute("doctorDto") UserDto doctorDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
		//autorizacija
		if(userService.getCA()){
		ModelAndView mav= new ModelAndView("doctorsSearchAll");
		
		Set<UserDto> doktori=(Set<UserDto>)request.getSession().getAttribute("doctorsDto");
		
		if(doktori==null) {
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
	public String searchDoctors(HttpServletRequest request, @ModelAttribute("doctorDto") UserDto doctorDto, ModelMap model ) {
		request.getSession().setAttribute("doctorsDto", userService.searchDoctor(doctorDto.nameDto, doctorDto.surnameDto, doctorDto.getMarkDto()));
		return "redirect:/doctorsSearch";
	}
	
	
	//pretraga pacijenata HomePageDoktor
	@GetMapping("/patientSearch/doctor")
	public ModelAndView searchPatientHomePageDoctor(HttpServletRequest request, @ModelAttribute("patientDto") UserDto patientDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
		//autorizacija
		if(userService.getDoktor()){
		
		ModelAndView mav=new ModelAndView("PatientSearchDoctor");
		
		Set<UserDto> pacijenti=(Set<UserDto>)request.getSession().getAttribute("patientsDto");
		
		if(pacijenti==null) {
			mav.addObject("patientsDto", userService.allUserByRole("pacijent"));
		}
		else {
	
		mav.addObject("patientsDto", pacijenti);
		}
		return mav;
		}else {
			return new ModelAndView("badUser");
		}
		}
		}

	
	@PostMapping("/patient/searchD")
	public String searchPatientR(HttpServletRequest request,@ModelAttribute("patientDto") UserDto patientDto,ModelMap model) {
		request.getSession().setAttribute("patientsDto", userService.searchPatient(patientDto.nameDto, patientDto.surnameDto, patientDto.getInsuranceNumberDto()));
		return "redirect:/patientSearch/doctor";
		
	}
	
	//rezervisanje godisnjeg odmora
	
	@GetMapping("/godisnjiOdmorRezervisanje")
	public ModelAndView rezervisanjeGodisnjeg(HttpServletRequest request,@ModelAttribute("VacationReqDto") VacationRequestDto vacReqDto,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
		//autorizacija
		if(userService.getDoktor()){
		String username = (String) request.getSession().getAttribute("logUsername");
		if(username!=null) {
		UserDto userTemp = userService.getUserByUsername(username);
		
		vacReqDto.setEmailDto(userTemp.emailDto);
		vacReqDto.setNameDto(userTemp.nameDto);
		vacReqDto.setSurnameDto(userTemp.getSurnameDto());
		vacReqDto.setUsernameDto(username);
		vacReqDto.setRoleDto("doktor"); }
	
		return new ModelAndView("zakazivanjeGodisnjeg");
		}else {
			return new ModelAndView("badUser");
		}
		
		}

	}
	
	@PostMapping("/kreirajZahtevGodisnji")
	public String kreirajZahtev(@Valid @ModelAttribute("VacationReqDto") VacationRequestDto vacReqDto) {
		
		vqService.createVacReq(vacReqDto);
		return "redirect:/godisnjiOdmorRezervisanje";
	}
	
	
}
