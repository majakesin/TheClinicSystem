package ftn.project.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.services.AppointmentService;
import ftn.project.services.ClinicService;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@Controller
public class UserController {

	
	
	
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private  RequestService requestService;

	@GetMapping("/kreirajNP1/{idDto}")
	public ModelAndView kreirajNP1(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto user,ModelMap model) {
		
		model.addAttribute("doktor",userService.getUserById(idDto));
		
		return new ModelAndView("zakaziNPD1","Model",userService.getUserById(idDto));
	}
	
	
	//zakaziPregledNepredfinisan1
	@PostMapping("/zakazanNP1/{idDto}")
	public String zakazanTerminNEPredefinisan1(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto userVreme , HttpServletRequest request,ModelMap model) {
		UserDto doktor = userService.getUserById(idDto);
		String usernamePacijent = (String) request.getSession().getAttribute("logUsername");
		UserDto pacijent = userService.getUserByUsername(usernamePacijent);
		AppointmentDto app = new AppointmentDto();
		app.setOperationTypeDto("Pregled");
		app.setDateDto(userVreme.getDatumPregledaDto());
		app.setTimeDto(userVreme.getVremePregledaDto());
		app.setTypeDto(doktor.getTipPregledaDto());
		app.setDoctorDto(doktor.getIdDto());
		app.setPacientId(pacijent.getIdDto());
		app.setPatientIdDto(pacijent.getIdDto());
		app.setAcceptDto(true);
		
		requestService.createTerm(app);
		
		return "redirect:/clincsSearchDateType";
	
		
	}	
	
	@GetMapping("/kreirajNP2/{idDto}")
	public ModelAndView kreirajNP2(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto user,ModelMap model) {
		
		model.addAttribute("doktor",userService.getUserById(idDto));
		
		return new ModelAndView("zakazanNPD2","Model",userService.getUserById(idDto));
	}
	
	@GetMapping("/kreirajNepredef2/{idDto}")
	public ModelAndView kreirajNP2Profi(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto user,ModelMap model) {
		
		model.addAttribute("doktor",userService.getUserById(idDto));
		
		return new ModelAndView("ZakazanProfil","Model",userService.getUserById(idDto));
	}
	
	//zakaziPregledNepredfinisan1
	@PostMapping("/zakazanNP2/{idDto}")
	public String zakazanTerminNEPredefinisan2(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto userVreme , HttpServletRequest request,ModelMap model) {
		UserDto doktor = userService.getUserById(idDto);
		String usernamePacijent = (String) request.getSession().getAttribute("logUsername");
		UserDto pacijent = userService.getUserByUsername(usernamePacijent);
		AppointmentDto app = new AppointmentDto();
		app.setDateDto(userVreme.getDatumPregledaDto());
		app.setOperationTypeDto("Pregled");
		app.setTimeDto(userVreme.getVremePregledaDto());
		app.setTypeDto(doktor.getTipPregledaDto());
		app.setDoctorDto(doktor.getIdDto());
		app.setPacientId(pacijent.getIdDto());
		app.setPatientIdDto(pacijent.getIdDto());
		app.setAcceptDto(true);
		
		requestService.createTerm(app);
		
		return "redirect:/clincsSearchDateType";
	
		
	}
	
	//zakaziPregledNepredfinisan1
		@PostMapping("/zakazanNPD3/{idDto}")
		public String zakazanTerminNEPredefinisan3(@PathVariable("idDto") Long idDto,@ModelAttribute("docVreme") UserDto userVreme , HttpServletRequest request,ModelMap model) {
			UserDto doktor = userService.getUserById(idDto);
			String usernamePacijent = (String) request.getSession().getAttribute("logUsername");
			UserDto pacijent = userService.getUserByUsername(usernamePacijent);
			AppointmentDto app = new AppointmentDto();
			app.setOperationTypeDto("Pregled");
			app.setDateDto(userVreme.getDatumPregledaDto());
			app.setTimeDto(userVreme.getVremePregledaDto());
			app.setTypeDto(doktor.getTipPregledaDto());
			app.setDoctorDto(doktor.getIdDto());
			app.setPacientId(pacijent.getIdDto());
			app.setPatientIdDto(pacijent.getIdDto());
			app.setAcceptDto(true);
			
			requestService.createTerm(app);
			
			return "redirect:/listaKlinikaProfil";
		
			
		}
}
