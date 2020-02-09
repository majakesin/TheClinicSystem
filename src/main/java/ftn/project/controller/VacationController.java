package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class VacationController {
	
	private final VacationRequestService vqService;
	private final VacationValidator vacationValidator;
	private final UserService userService;
	
	@InitBinder
	protected void initBinder2(WebDataBinder binder) {
		binder.addValidators(vacationValidator);
	}
	
	
	//rezervisanje godisnjeg odmora, doktor
	
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
		public String kreirajZahtev(@Validated @ModelAttribute("VacationReqDto") VacationRequestDto vacReqDto, BindingResult result) {
			
			if(result.hasErrors()) {
				return "zakazivanjeGodisnjeg";
			}

			
			vqService.createVacReq(vacReqDto);
			return "redirect:/godisnjiOdmorRezervisanje";
		}
		
		
	//rezervisanje godisnjeg odmora, sestra
		
		@GetMapping("/godisnjiOdmorRezervisanjeSestra")
		public ModelAndView rezervisanjeGodisnjegSestra(HttpServletRequest request,@ModelAttribute("VacationReqDto") VacationRequestDto vacReqDto,ModelMap model) {
			userService.Autorizacija(request);
			
			//autorizacija
			if(userService.getNull()) {
				return new ModelAndView("badUser");
			}
			else {
				//autorizacija
				if(userService.getSestra()){
			
			String username = (String) request.getSession().getAttribute("logUsername");
			if(username!=null) {
			UserDto userTemp = userService.getUserByUsername(username);
			
			vacReqDto.setEmailDto(userTemp.emailDto);
			vacReqDto.setNameDto(userTemp.nameDto);
			vacReqDto.setSurnameDto(userTemp.getSurnameDto());
			vacReqDto.setUsernameDto(username);
			vacReqDto.setRoleDto("med. sestra"); }
		
			return new ModelAndView("zakazivanjeGodisnjegSestra");
				}else {
					return new ModelAndView("badUser");
				}
				}

		}
		
		@PostMapping("/kreirajZahtevGodisnjiSestra")
		public String kreirajZahtevSestra(@Validated @ModelAttribute("VacationReqDto") VacationRequestDto vacReqDto,BindingResult result) {
			
			if(result.hasErrors()) {
				return "zakazivanjeGodisnjegSestra";
			}
			
			vqService.createVacReq(vacReqDto);
			return "redirect:/godisnjiOdmorRezervisanje";
		}
		

}
