package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ftn.project.dto.RecordsDto;
import ftn.project.services.MedicalRecordsService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/doctors/records/{idDto}")
@Controller
public class MedicalRecordsController {
	
	private final UserService userService;
	
	private final MedicalRecordsService medicalRecordsService;
	
	@GetMapping
	public String getPacients(@PathVariable ("idDto") Long idDto,HttpServletRequest request,Model model) {
		HttpSession session=request.getSession();
		Long pacientId=(Long)session.getAttribute("pacientId");
		if(pacientId==null) {
			session.setAttribute("pacientId", idDto);
		}
		
		model.addAttribute("recordDto",medicalRecordsService.getPacientRecords(idDto));
		model.addAttribute("pacientDto",userService.getUserById(idDto));
		return "medicalRecords";
	}
	
//	@GetMapping("/{idDto}")
//	@ResponseBody
//	public ResponseEntity<RecordsDto> showPacientRecords(@PathVariable("idDto") Long idDto) {
//		RecordsDto recordsDto=medicalRecordsService.getPacientRecords(idDto);
//		return new ResponseEntity<RecordsDto>(recordsDto,HttpStatus.OK);
//	}
	
	@PostMapping("/save")
	public String changeMedicalRecords(@RequestBody RecordsDto recordsDto,HttpServletRequest request) {
		HttpSession session=request.getSession();
		Long pacientId=(Long)session.getAttribute("pacientId");
		recordsDto.setIdDto(medicalRecordsService.getPacientRecords(pacientId).getIdDto());
		medicalRecordsService.savePacientRecords(recordsDto);
		return "redirect:/doctors/records";
	}
}
