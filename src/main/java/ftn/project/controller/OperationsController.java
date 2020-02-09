package ftn.project.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.OperationDto;
import ftn.project.dto.RoomDto;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.services.AppointmentService;
import ftn.project.services.OperationService;
import ftn.project.services.RequestService;
import ftn.project.services.RoomService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clinic/admin/operations")
public class OperationsController {

	private final UserService userService;

	private final RoomService roomService;

	private final RequestService requestService;

	private final OperationService operationService;

	private final AppointmentService appointmentService;

	@GetMapping("/{id}")
	public String getPage(@PathVariable("id") Long id, Model model, HttpServletRequest request)
			throws MailException, MessagingException {
		request.getSession().setAttribute("idTerms", id);
		Long selectedRoom = (Long) request.getSession().getAttribute("selectedRoom");
		if (selectedRoom != null) {

			model.addAttribute("selectedRoom", roomService.getRoom(selectedRoom));
		}
		model.addAttribute("typeOfOperation", appointmentService.getAppointement(id).operationTypeDto);

		model.addAttribute("doctorsList", userService.allUserByRole("doktor"));

		return "operations";

	}

	@Scheduled(cron = "${greeting.cron}")
	public void automatic() {
		operationService.automaticSystem();
	}

	@GetMapping("/changeAppointment")
	private String changeAppointment(Model model, HttpServletRequest request) {
		model.addAttribute("appointmentDto", new AppointmentDto());
		model.addAttribute("allRooms", roomService.allRooms());
		return "changeAppoitment";
	}

	@PostMapping("/change/appointment")
	private String saveAppoint(Model model, HttpServletRequest request,
			@ModelAttribute("appoitnmentDto") AppointmentDto appointDto) {
		Long idTerm = (Long) request.getSession().getAttribute("idTerms");
		request.getSession().setAttribute("selectedRoom", appointDto.getRoomId());
		appointDto.setIdDto(idTerm);
		roomService.TakeRoom(appointDto);
		operationService.changeOperation(idTerm, appointDto.getTimeDto(), appointDto.getDateDto(),
				appointDto.getRoomId());
		return "redirect:/clinic/admin/operations/" + idTerm;
	}

	@PostMapping("/reservate")
	public String getIds(@RequestBody OperationDto operationDto, HttpServletRequest request) {
		Long selectedRoom = (Long) request.getSession().getAttribute("selectedRoom");
		Long idTerm = (Long) request.getSession().getAttribute("idTerms");
		operationDto.setRoomDto(selectedRoom);
		operationDto.setTermId(idTerm);
		boolean isOperation = false;

		AppointmentDto appDto = appointmentService.getAppointement(idTerm);
		appDto.setRoomId(selectedRoom);
		roomService.TakeRoom(appDto);
		

		operationService.createOperation(operationDto, isOperation);
		return "redirect:/operationList";
	}
}
