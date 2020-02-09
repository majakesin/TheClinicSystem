package ftn.project.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import ftn.project.dto.RoomDto;
import ftn.project.dto.UserDto;
import ftn.project.model.User;
import ftn.project.services.AppointmentService;
import ftn.project.services.EmailService;
import ftn.project.services.OperationService;
import ftn.project.services.RoomService;
import ftn.project.services.UserService;
import ftn.project.validation.RoomValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Controller
public class RoomController {

	private OperationService operationService;
	private final EmailService emailService;
	private final RoomService roomService;
	private final UserService userService;

	private final RoomValidator roomValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(roomValidator);
	}

	private final AppointmentService appointmentService;

	@GetMapping("/rooms")
	public ModelAndView showClinics(HttpServletRequest request, @ModelAttribute("roomDto") RoomDto roomDto,
			ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getCA()) {

				model.addAttribute("roomsDto", roomService.allRooms());
				return new ModelAndView("roomCA", "Model", roomService.allRooms());
			} else {
				return new ModelAndView("badUser");
			}
		}
	}

	@PostMapping("/room/create")

	public String createRoom(@Validated @ModelAttribute("roomDto") RoomDto roomDto, BindingResult result) {
		roomDto.setFree(true);
		if (result.hasErrors()) {
			return "roomCA";
		}
		roomService.create(roomDto);
		return "redirect:/rooms";
	}

	@PostMapping("/room/edit")

	public String editRoom(@Validated @ModelAttribute("roomDto") RoomDto roomDto, BindingResult result) {

		if (result.hasErrors()) {
			return "roomCA";
		}
		roomService.create(roomDto);
		return "redirect:/rooms";
	}

	@GetMapping("room/delete/{idDto}")
	public String deleteRoom(HttpServletRequest request, @PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return "redirct;/badUser";
		} else {
			// autorizacija
			if (userService.getCA()) {

				roomService.deleteRoom(idDto);
				return "redirect:/rooms";
			} else {
				return "redirct;/badUser";
			}
		}
	}

	@GetMapping("/roomsSearch")
	public ModelAndView searchDoctor(HttpServletRequest request, @ModelAttribute("roomDto") RoomDto roomDto,
			ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getCA()) {

				ModelAndView mav = new ModelAndView("RoomSearch");
				Long idTerm = (Long) request.getSession().getAttribute("idTerms");
				Set<RoomDto> sobe = roomService.isTermsInRoomTerms(idTerm);
				mav.addObject("roomsDto", sobe);
				// Set<RoomDto>
				// sobe=(Set<RoomDto>)request.getSession().getAttribute("roomsDto");
				if (sobe.isEmpty()) {
					mav.setViewName("redirect:/clinic/admin/operations/changeAppointment");
					return mav;
				}

//		
//		if(sobe==null) {
//			mav.addObject("roomsDto", roomService.allRooms());
//		} else {
////			mav.addObject("roomsDto", sobe);
//		}
				return mav;
			} else {
				return new ModelAndView("badUser");
			}
		}
	}

	@PostMapping("/room/search")
	public ModelAndView searchDoctors(HttpServletRequest request, @ModelAttribute("roomDto") RoomDto roomDto,
			ModelMap model) {
		ModelAndView mav = new ModelAndView("RoomSearch");
		mav.addObject("roomsDto", roomService.searchRooms(roomDto.getNameDto(), roomDto.getHallNumberDto()));

		return mav;
	}

	/* Metoda koja izabranu salu stavlja kao atribut u sessiji korisnika */
	@GetMapping("/rooms/reservate/{id}")
	public String reservateRoom(@PathVariable("id") Long id, HttpServletRequest request) {
		Long idTerm = (Long) request.getSession().getAttribute("idTerms");
		request.getSession().setAttribute("selectedRoom", id);

		AppointmentDto appDto=appointmentService.getAppointement(idTerm);
		UserDto pacient = userService.getUserById(appDto.getPacientId());
		if(appDto.operationTypeDto.equals("Pregled")) {

			appDto.setRoomId(id);

			emailService.sendMail(pacient.getEmailDto(),
					"Postovani vas pregled je zakazan " + "soba " + appDto.getRoomId(), appDto.getOperationTypeDto());

			roomService.TakeRoom(appDto);
			return "redirect:/appointmentRequests";
		}
		return "redirect:/clinic/admin/operations/" + idTerm;
	}
}
