package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ftn.project.dto.RecordsDto;
import ftn.project.services.MedicalRecordsService;
import ftn.project.services.UserService;
import ftn.project.validation.MedicalRecordsValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/doctors/records")
@Controller
public class MedicalRecordsController {

	private final UserService userService;
	private final MedicalRecordsValidator medicalValidator;

	private final MedicalRecordsService medicalRecordsService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(medicalValidator);
	}

	@GetMapping("/{idDto}")
	public String getPacients(@PathVariable("idDto") Long idDto, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Long pacientId = (Long) session.getAttribute("pacientId");
		if (pacientId == null) {
			session.setAttribute("pacientId", idDto);
		}

		model.addAttribute("recordDto", medicalRecordsService.getPacientRecords(idDto));

		return "medicalRecords";
	}

	@GetMapping("/edit")
	public String showPacientRecords( Long idDto,HttpServletRequest request,Model model) {
		model.addAttribute("medicalRecordDto",medicalRecordsService.getPacientRecords((Long)request.getSession().getAttribute("pacientId")));
		return "medicalRecordsEdit";	
	}
	
	@PostMapping("/save")
	public String changeMedicalRecords(@Validated @ModelAttribute("medicalRecordDto") RecordsDto recordsDto,BindingResult result, HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		
		Long pacientId = (Long) session.getAttribute("pacientId");
		recordsDto.setIdDto(medicalRecordsService.getPacientRecords(pacientId).getIdDto());
		
		if(result.hasErrors()){
			
			return "medicalRecordsEdit";
		}
		medicalRecordsService.savePacientRecords(recordsDto);
		return "redirect:/doctors/records/"+pacientId;
	}
	
	
}
