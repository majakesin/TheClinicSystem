package ftn.project.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.RoomDto;
import ftn.project.services.OperationService;
import ftn.project.services.RoomService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class RoomController  {

	private RoomService roomService;
	
	private OperationService operationService;

	@GetMapping("/rooms")
	public ModelAndView showClinics(@ModelAttribute("roomDto")RoomDto roomDto,ModelMap model) {
		model.addAttribute("roomsDto",roomService.allRooms());
		return new ModelAndView("roomCA","Model",roomService.allRooms());
	}
	
	@PostMapping("/room/create")
	public String createRoom(@Valid @ModelAttribute("roomDto") RoomDto roomDto) {
		roomDto.setFree(true);
		roomService.create(roomDto);
		return "redirect:/rooms";
	}
	
	@PostMapping("/room/edit")
	public String editRoom(@Valid @ModelAttribute("roomDto") RoomDto roomDto) {
		
		roomService.create(roomDto);
		return "redirect:/rooms";
	}


	@GetMapping("room/delete/{idDto}")
	public String deleteRoom(@PathVariable("idDto") Long idDto, ModelMap model) {
		roomService.deleteRoom(idDto);
		return "redirect:/rooms";
	}
	
	
	@GetMapping("/roomsSearch")
	public ModelAndView searchDoctor(HttpServletRequest request, @ModelAttribute("roomDto") RoomDto roomDto, ModelMap model) {
		ModelAndView mav= new ModelAndView("RoomSearch");
		
		Set<RoomDto> sobe=roomService.emptyRooms();
		mav.addObject("roomsDto", roomService.allRooms());
		//Set<RoomDto> sobe=(Set<RoomDto>)request.getSession().getAttribute("roomsDto");
		Long idTerm=(Long)request.getSession().getAttribute("idTerms");
		if(sobe.isEmpty()) {
			operationService.fullRooms(idTerm);
			mav.setViewName("redirect:/clinic/admin/operations/"+idTerm);
			return mav;
		}
//		
//		if(sobe==null) {
//			mav.addObject("roomsDto", roomService.allRooms());
//		} else {
////			mav.addObject("roomsDto", sobe);
//		}
		return mav;
	}
	
	@PostMapping("/room/search")
	public ModelAndView searchDoctors(HttpServletRequest request, @ModelAttribute("roomDto") RoomDto roomDto, ModelMap model ) {
		ModelAndView mav=new ModelAndView("RoomSearch");
		mav.addObject("roomsDto",roomService.searchRooms(roomDto.getNameDto(), roomDto.getHallNumberDto()));
		
		return mav;
	}
	/*Metoda koja izabranu salu stavlja kao atribut u sessiji korisnika*/
	@GetMapping("/rooms/reservate/{id}")
	public String reservateRoom(@PathVariable ("id") Long id,HttpServletRequest request) {
		Long idTerm=(Long)request.getSession().getAttribute("idTerms");
		request.getSession().setAttribute("selectedRoom", id);
		return "redirect:/clinic/admin/operations/"+idTerm;
	}
}
