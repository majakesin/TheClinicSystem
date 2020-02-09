package ftn.project.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ftn.project.dto.RoomDto;
import ftn.project.services.RoomService;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("/examinationRooms")
public class ExaminationRoomController {

	private final RoomService roomService;
	
	@GetMapping("/{id}")
	public String getPage(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		request.getSession().setAttribute("idTerms", id);
		Long selectedRoom = (Long) request.getSession().getAttribute("selectedRoomForExamination");
		if (selectedRoom != null) {
			model.addAttribute("selectedRoom", roomService.getRoom(selectedRoom));
		}
		return "redirect:/roomsSearch";
	}
	
	

}
