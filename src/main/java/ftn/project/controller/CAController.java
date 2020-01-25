package ftn.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.OperationDto;
import ftn.project.dto.UserDto;
import ftn.project.dto.VacationRequestDto;
import ftn.project.services.VacationRequestService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class CAController {

	private final VacationRequestService vqService;
	
	@GetMapping("/zahtevi/CA")
	public ModelAndView prikaziZahteveGodisnji(ModelMap model) {
		model.addAttribute("vqRegsDto",vqService.allRequests());
		return new ModelAndView("zahteviOdmorCA", "Model", vqService.allRequests());

	}
	
	
	@GetMapping("/VqReqRequests/accept/{idDto}")
	public String prihvatiZahtevZaGodisnji(@PathVariable("idDto") Long idDto ) {
		
		VacationRequestDto vqTemp = vqService.getVQDtoById(idDto);
		
		vqService.posaljiMejlPotvrdan(vqTemp.emailDto, vqTemp.nameDto);
		vqService.obrisiZahtev(idDto);
		return "redirect:/zahtevi/CA";
	}
	
	
	@GetMapping("/zahtevGodisnji/odbij/{idDto}")
	public ModelAndView odgovoriMejlOdbijenica(@PathVariable("idDto") Long idDto,ModelMap model,@ModelAttribute("VacReqDto") VacationRequestDto vacReqDto ) {
		
		VacationRequestDto vqTemp = vqService.getVQDtoById(idDto);
		vacReqDto.setIdDto(vqTemp.getIdDto());
		vacReqDto.setEmailDto(vqTemp.getEmailDto());
		
		return new ModelAndView("odbijenicaMejl");
		
	}
	@PostMapping("/odbijZahtev")
	public String odbijZahtev(@ModelAttribute("VacReqDto") VacationRequestDto vacReqDto )
	{
		vqService.posaljiMejlOdbijen(vacReqDto.emailDto, vacReqDto.tekstMailaDto, vacReqDto.subjekatMailaDto);
		vqService.obrisiZahtev(vacReqDto.idDto);
		return "redirect:/zahtevi/CA";
		
	}

}
