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

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.services.AppointmentService;
import ftn.project.services.ClinicService;
import ftn.project.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Controller
public class ClinicController {

private final ClinicService clinicService;
	
	private final UserService userService;
	
	private final AppointmentService appointmentService;
	
	Set<ClinicDto> termini=null;
	
	
	@GetMapping("/clinics")
	public ModelAndView showClinics(HttpServletRequest request,@ModelAttribute("clinicDto")ClinicDto clinicDto,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		model.addAttribute("clinicsDto",clinicService.allClinics());
	//	model.addAttribute("allClinicsAdmins",userService.allUserByRole("Clinic Administrator"));
		return new ModelAndView("clinics","Model",clinicService.allClinics());
			}else {
				return new ModelAndView("badUser");
			}
			}
	}
	
	
	
	@PostMapping("/clinics/create")
	public String createClinic(HttpServletRequest request,@Valid @ModelAttribute("clinicDto")ClinicDto clinicDto) {
		clinicService.createClinic(clinicDto);
		return "redirect:/clinics";
	}
	
	@GetMapping("/clinicsProfileCA")
	public String showClinicProfile(HttpServletRequest request,@ModelAttribute("userDto") UserDto userDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		String username=(String)request.getSession().getAttribute("logUsername");
		model.addAttribute("clinicDto", clinicService.getClinicProfile(username));
		return "clinicProfileCA";
			}else {
				return "redirect:/badUser";
			}
				
			}

	}
	
	@GetMapping("clinics/delete/{idDto}") 
	public String deleteClinic(HttpServletRequest request,@PathVariable("idDto") Long idDto,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:badUser";
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		clinicService.deleteClinic(idDto);
		return "redirect:/clinics";
			}else {
				return "redirect:badUser";
			}
			}
	}
	
	@GetMapping("/clinicProfileCA/edit/{idDto}")
	public ModelAndView showNurseProfileEdit(HttpServletRequest request,@ModelAttribute("clinicDto") ClinicDto clinicDto,@PathVariable("idDto") Long idDto, ModelMap model ) {
		
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		
		model.addAttribute("clinicDto", clinicService.getClinicById(idDto));
		return new ModelAndView("clinicProfileCAEdit", "Model",clinicService.getClinicById(idDto));

			}else {
				return new ModelAndView("badUser");
			}
			}
	}
	
	@PostMapping("/clinic/edit/ca")
	public String edtClinicCA(@Valid @ModelAttribute("clinicDto")ClinicDto clinicDto) {
		clinicService.editClinicProfile(clinicDto);
		
	return "redirect:/clinicsProfileCA";
	
	}
	
	//pretraga klinika po vise kriterijuma
	
	//za pretragu klinika po datumu i tipu PREGLEDA
		@GetMapping("/clincsSearchDateType")
		public ModelAndView searchClinicByTerm(HttpServletRequest request, @ModelAttribute("termDto") AppointmentDto termDto, ModelMap model) {
			
			ModelAndView mav= new ModelAndView("listOfClinics");
			request.getSession().setAttribute("clinicsDto",null);
			request.getSession().setAttribute("doctorsDto",null);
			request.getSession().setAttribute("doktori2", null);
			
			termini =(Set<ClinicDto>)request.getSession().getAttribute("termsDto");
			
			
			if(termini==null) {
				mav.addObject("termsDto", clinicService.allClinics() );
			} else {
				mav.addObject("termsDto", termini);
				} 
			return mav;
		}
		
		@PostMapping("/terms/search")
		public String searchClinicByTerms(HttpServletRequest request, @ModelAttribute("termDto") AppointmentDto termDto, ModelMap model ) {
			request.getSession().setAttribute("termsDto", clinicService.searchClinicByDoctor(termDto.dateDto, termDto.typeDto));
			//ide dio gdje setujem direktno datum da to posle znam za zakazivanje :)
			Set<UserDto> doktori1 = clinicService.vratiDoktori1();
			for(UserDto doc: doktori1) {
				doc.setDatumPregledaDto(termDto.dateDto);
			}
			
			request.getSession().setAttribute("doktori1",doktori1);
			return "redirect:/clincsSearchDateType";
		}
		
		
		//za pretragu klinika po adresi i oceni KLINIKE
		@GetMapping("/clinicsSearch")
		public ModelAndView searchClinic(HttpServletRequest request, @ModelAttribute("clinicDto") ClinicDto clinicDto, ModelMap model) {
			
			ModelAndView mav= new ModelAndView("listOfClinics2");
			 
			Set<ClinicDto> klinike =(Set<ClinicDto>)request.getSession().getAttribute("clinicsDto");
					
			if(termini == null) {
				
				if(klinike==null) {
					mav.addObject("clinicsDto",clinicService.allClinics()  );
				} else {
					mav.addObject("clinicsDto", klinike);
				}
				
			}  else {
				if(klinike==null) {
					mav.addObject("clinicsDto",termini  );
				} else {
					mav.addObject("clinicsDto", klinike);
				}
			} 
			
			
					
					return mav;
				}
		
		@PostMapping("/clinics/search")
		public String searchClinics(HttpServletRequest request, @ModelAttribute("clinicDto") ClinicDto clinicDto, ModelMap model ) {
			request.getSession().setAttribute("clinicsDto", clinicService.searchClinic(clinicDto.adressDto, clinicDto.markDto));
			
			request.getSession().setAttribute("doktori2", clinicService.vratiDoktori2());
			return "redirect:/clinicsSearch";
		}
		
}
