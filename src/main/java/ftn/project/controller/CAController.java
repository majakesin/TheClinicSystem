package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.VacationRequestDto;
import ftn.project.services.UserService;
import ftn.project.services.VacationRequestService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class CAController {

	private final VacationRequestService vqService;
	private final UserService userService;
	
	@GetMapping("/zahtevi/CA")
	public ModelAndView prikaziZahteveGodisnji(HttpServletRequest request,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		model.addAttribute("vqRegsDto",vqService.allRequests());
		return new ModelAndView("zahteviOdmorCA", "Model", vqService.allRequests());
			} else {
				return new ModelAndView("badUser");
			}
		}
	}
	
	
	@GetMapping("/VqReqRequests/accept/{idDto}")
	public String prihvatiZahtevZaGodisnji(HttpServletRequest request,@PathVariable("idDto") Long idDto ) {
		
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		
		VacationRequestDto vqTemp = vqService.getVQDtoById(idDto);
		String username = vqTemp.getUsernameDto();
		UserDto user = userService.getUserByUsername(username);
		user.setPocetakGodisnjegDto(vqTemp.pocetakGodisnjegDto);
		user.setKrajGodisnjegDto(vqTemp.krajGodisnjegDto);
		userService.createUser(user);
		vqService.posaljiMejlPotvrdan(vqTemp.emailDto, vqTemp.nameDto);
		vqService.obrisiZahtev(idDto);
		return "redirect:/zahtevi/CA";
			}else {
				return "redirect:/badUser";
			}
			}
	}
	
	
	@GetMapping("/zahtevGodisnji/odbij/{idDto}")
	public ModelAndView odgovoriMejlOdbijenica(HttpServletRequest request,@PathVariable("idDto") Long idDto,ModelMap model,@ModelAttribute("VacReqDto") VacationRequestDto vacReqDto ) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCA()){
		VacationRequestDto vqTemp = vqService.getVQDtoById(idDto);
		vacReqDto.setIdDto(vqTemp.getIdDto());
		vacReqDto.setEmailDto(vqTemp.getEmailDto());
		
		return new ModelAndView("odbijenicaMejl");
			}else {
				return new ModelAndView("badUser");
			}
		}
		
	}
	@PostMapping("/odbijZahtev")
	public String odbijZahtev(@ModelAttribute("VacReqDto") VacationRequestDto vacReqDto )
	{
		vqService.posaljiMejlOdbijen(vacReqDto.emailDto, vacReqDto.tekstMailaDto, vacReqDto.subjekatMailaDto);
		vqService.obrisiZahtev(vacReqDto.idDto);
		return "redirect:/zahtevi/CA";
		
	}

}
