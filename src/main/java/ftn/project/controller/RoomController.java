package ftn.project.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Controller
public class RoomController  {


	
	private OperationService operationService;

	private final RoomService roomService;
	private final UserService userService;


	@GetMapping("/rooms")
	public ModelAndView showClinics(HttpServletRequest request,@ModelAttribute("roomDto")RoomDto roomDto,ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCA()){

		
		model.addAttribute("roomsDto",roomService.allRooms());
		return new ModelAndView("roomCA","Model",roomService.allRooms());
			}else {
				return new ModelAndView("badUser");
			}
			}
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
	public String deleteRoom(HttpServletRequest request,@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirct;/badUser";
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		roomService.deleteRoom(idDto);
		return "redirect:/rooms";
			}else {
				return "redirct;/badUser";
			}
	}
	}
	
	
	@GetMapping("/roomsSearch")
	public ModelAndView searchDoctor(HttpServletRequest request, @ModelAttribute("roomDto") RoomDto roomDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCA()){
		
		ModelAndView mav= new ModelAndView("RoomSearch");
		
		Set<RoomDto> sobe=roomService.emptyRooms();
		mav.addObject("roomsDto", roomService.allRooms());
		//Set<RoomDto> sobe=(Set<RoomDto>)request.getSession().getAttribute("roomsDto");
		Long idTerm=(Long)request.getSession().getAttribute("idTerms");
		if(sobe.isEmpty()) {
			request.getSession().setAttribute("allFull", true);
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
			}else {
				return new ModelAndView("badUser");
			}
			}
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
