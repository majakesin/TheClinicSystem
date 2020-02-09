package ftn.project.controller;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.services.ClinicService;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import ftn.project.services.VacationRequestService;
import ftn.project.validation.VacationValidator;
import ftn.project.validation.ZakaziTerminValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@Controller
public class UserController {

	
	@Autowired
	private ClinicService clinicService;

	private Set<UserDto> pomocnaTemp = new HashSet<UserDto> ();
	
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
	public String zakazanTerminNEPredefinisan1(@Validated @ModelAttribute("docVreme") UserDto userVreme ,BindingResult result, HttpServletRequest request,ModelMap model, @PathVariable("idDto") Long idDto) {
		
		
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
		
		if (result.hasErrors()) {
			return "zakaziNPD1";
		}
		
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
	public String zakazanTerminNEPredefinisan2(@Validated @ModelAttribute("docVreme") UserDto userVreme ,BindingResult result, HttpServletRequest request,ModelMap model, @PathVariable("idDto") Long idDto) {
		
		
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
		
		if (result.hasErrors()) {
			return "zakazanNPD2";
		}
		
		requestService.createTerm(app);
		
		return "redirect:/clincsSearchDateType";
	
		
	}
	
	//zakaziPregledNepredfinisan1
		@PostMapping("/zakazanNPD3/{idDto}")
		public String zakazanTerminNEPredefinisan3(@Validated @ModelAttribute("docVreme") UserDto userVreme ,BindingResult result, HttpServletRequest request,ModelMap model,@PathVariable("idDto") Long idDto) {
		
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
			
			if (result.hasErrors()) {
				return "ZakazanProfil";
			}
			
			
			requestService.createTerm(app);
			
			return "redirect:/listaKlinikaProfil";
		
			
		}
		
		
		
		//za zakazivanje nepredefinisanih termina
		@GetMapping("/zakaziNepredefinisani1/{idDto}")
		public ModelAndView vratiPacijenteNepredefinisani(HttpServletRequest request,@PathVariable("idDto") Long idDto,ModelMap model,@ModelAttribute("doctorDtoPretraga") UserDto doktorDto) {
				ClinicDto clinicDto = clinicService.getClinicById(idDto);
				
				model.addAttribute("clinicDto",clinicDto);
				
				
				Set<UserDto >doktori = (Set<UserDto>) request.getSession().getAttribute("doktori1");
				Set<UserDto> doktoriPr = (Set<UserDto>) request.getSession().getAttribute("doctorsDto");
				Set<UserDto> pomocna = new HashSet<UserDto>();
				if(doktoriPr==null) {
				
				
				
				if(doktori!=null) {
				for(UserDto dok : doktori) {
					if(dok.getClinicDto()==clinicDto.getIdDto()) {
						pomocna.add(dok);
					}
				}
				}
				else {
					for(UserDto dok : userService.allUserByRole("doktor")) {
						if(dok.getClinicDto()==clinicDto.getIdDto()) {
							pomocna.add(dok);
						}
					}
				}
				model.addAttribute("doctorsDto",pomocna);
				
				pomocnaTemp.addAll(pomocna);
				}
				else {
					model.addAttribute("doctorsDto",doktoriPr);
					pomocnaTemp.addAll(doktoriPr);
				}
				return new ModelAndView("DoktoriNepredefPregled","Model", pomocna);
				
		
				
		}
		
		@GetMapping("/zakaziNepredefinisani2/{idDto}")
		public ModelAndView vratiPacijenteNepredefinisani2(HttpServletRequest request,@PathVariable("idDto") Long idDto,ModelMap model,@ModelAttribute("doctorDtoP") UserDto doktorDto) {
				ClinicDto clinicDto = clinicService.getClinicById(idDto);
				model.addAttribute("clinicDto",clinicDto);
				
				
				
				Set<UserDto >doktori2 = (Set<UserDto>) request.getSession().getAttribute("doktori2");
				Set<UserDto> doktori1 = (Set<UserDto>) request.getSession().getAttribute("doktori1");
				Set<UserDto> doktoriPr = (Set<UserDto>) request.getSession().getAttribute("doctorsDto");
				Set<UserDto> pomocna = new HashSet<UserDto>();
				if(doktoriPr==null) {
				if(doktori2!=null) {
				for(UserDto dok : doktori2) {
					if(dok.getClinicDto()==clinicDto.getIdDto()) {
						pomocna.add(dok);
					}
				}
				}
				else {
					
					if(doktori1!=null) {
						for(UserDto dok : doktori1) {
							if(dok.getClinicDto()==clinicDto.getIdDto()) {
								pomocna.add(dok);
							}
						
						}
					}
						else {
							for(UserDto dok : userService.allUserByRole("doktor")) {
								if(dok.getClinicDto()==clinicDto.getIdDto()) {
									pomocna.add(dok);
								}
							}
						}
				}
				model.addAttribute("doctorsDto",pomocna);
				
				}
				else {
					model.addAttribute("doctorsDto",doktoriPr);
					pomocnaTemp.addAll(doktoriPr);
				}
				return new ModelAndView("DoktorNepredf2","Model", clinicDto);
				
		
				
		}
		

		//pretraga Doktori1 
		@PostMapping("/doctors/search1/{idDto}")
		public String searchDoctors1(HttpServletRequest request,@PathVariable("idDto") Long idDto, @ModelAttribute("doctorDtoPretraga") UserDto doctorDto, ModelMap model ) {
			
			
			
			
			
			request.getSession().setAttribute("doctorsDto", userService.searchDoctorsNepredefinsani(doctorDto.nameDto, doctorDto.surnameDto, doctorDto.getMarkDto(),pomocnaTemp));
			
			return "redirect:/zakaziNepredefinisani1/"+idDto;
		}
		
		//pretraga Doktori2;
		
		@PostMapping("/doctors/search2/{idDto}")
		public String searchDoctors2(HttpServletRequest request,@PathVariable("idDto") Long idDto, @ModelAttribute("doctorDtoPretraga") UserDto doctorDto, ModelMap model ) {
			
			
			
			
			
			request.getSession().setAttribute("doctorsDto", userService.searchDoctorsNepredefinsani(doctorDto.nameDto, doctorDto.surnameDto, doctorDto.getMarkDto(),pomocnaTemp));
			
			return "redirect:/zakaziNepredefinisani1/"+idDto;
		}
		
		
}
