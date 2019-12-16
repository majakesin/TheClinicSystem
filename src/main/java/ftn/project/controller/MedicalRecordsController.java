package ftn.project.controller;

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
@RequestMapping("/doctors/records")
@Controller
public class MedicalRecordsController {
	
	private final UserService userService;
	
	private final MedicalRecordsService medicalRecordsService;
	
	@GetMapping
	public String getPacients(Model model) {
		model.addAttribute("pacientsDto",userService.allUserByRole("pacijent"));
		return "medicalRecords";
	}
	
	@GetMapping("/{idDto}")
	@ResponseBody
	public ResponseEntity<RecordsDto> showPacientRecords(@PathVariable("idDto") Long idDto) {
		RecordsDto recordsDto=medicalRecordsService.getPacientRecords(idDto);
		return new ResponseEntity<RecordsDto>(recordsDto,HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public String changeMedicalRecords(@RequestBody RecordsDto recordsDto) {
		medicalRecordsService.savePacientRecords(recordsDto);
		return "redirect:/doctors/records";
	}
}
