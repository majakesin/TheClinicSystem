package ftn.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ftn.project.dto.AnamnesisDto;
import ftn.project.dto.AppointmentDto;
import ftn.project.dto.PrescriptionDto;
import ftn.project.dto.UserDto;
import ftn.project.services.AnamnesisService;
import ftn.project.services.AppointmentService;
import ftn.project.services.CalendarService;
import ftn.project.services.CodeBookService;
import ftn.project.services.PrescriptionService;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctors/records/pacient/examination")
public class ExaminationController {

	private final CodeBookService codeBookService;
	
	private final PrescriptionService prescriptionService;
	
	private final AnamnesisService anamnesisService;
	
	private final UserService userService;
	
	private final RequestService requestService;
	
	private final CalendarService calendarService;
	
	@GetMapping
	public String getPage(HttpServletRequest request,Model model,@ModelAttribute("anamnesisDto") AnamnesisDto anamnesisDto) {
		Long pacientId=(Long) request.getSession().getAttribute("pacientId");
		model.addAttribute("pacientDto",userService.getUserById(pacientId));
		model.addAttribute("allCodebook",codeBookService.allCodeBooks());
		model.addAttribute("prescriptionsDto",prescriptionService.getAllPrescriptionsByPacientId(pacientId));
		return "examination";
	}
	
	@GetMapping("/prescription")
	public String getPage(HttpServletRequest request,Model model,@ModelAttribute("prescriptionDto")PrescriptionDto prescriptionDto) {
		Long pacientId=(Long) request.getSession().getAttribute("pacientId");
		model.addAttribute("allCodebook",codeBookService.allCodeBooks());
		model.addAttribute("prescriptionsDto",prescriptionService.getAllPrescriptionsByPacientId(pacientId));
		return "prescriptions";
	}
	
	@PostMapping("/create")
	public String finishExamination(HttpServletRequest request,@Valid @ModelAttribute("anamnesisDto") AnamnesisDto anamnesisDto) {
		anamnesisDto.setPacientId((Long)request.getSession().getAttribute("pacientId"));
		anamnesisService.save(anamnesisDto);
		return "redirect:/doctors/records/pacient/examination/calendar";
	}
	
	@PostMapping("/prescription/create")
	public String addPrescription(HttpServletRequest request,@Valid @ModelAttribute("prescriptionDto")PrescriptionDto prescriptionDto) {
		prescriptionDto.setPacientId((Long)request.getSession().getAttribute("pacientId"));
		prescriptionService.createPrescription(prescriptionDto);
		return "redirect:/doctors/records/pacient/examination";
	}
	
	@GetMapping("/delete/prescription/{idDto}")
	public String removePrescription(@PathVariable("idDto")Long idDto) {
		prescriptionService.deletePrescription(idDto);
		return  "redirect:/doctors/records/pacient/examination";
	}
	
	@GetMapping("/calendar")
	public String getDoctorCalendar(HttpServletRequest request,Model model,@ModelAttribute("appointmentDto")AppointmentDto appointmentDto) {
		appointmentDto.setDoctorDto((Long)request.getSession().getAttribute("pacientId"));
		requestService.createTerm(appointmentDto);		
		return "doctorCalendar";
	}
	
	
	@ResponseBody
	@GetMapping("/calendar/allEvents")
	public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username=(String)request.getSession().getAttribute("logUsername");
		UserDto userDto=userService.getUserProfile(username);
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(new Gson().toJson(calendarService.allCalendars(userDto.getIdDto())));

	}
}
