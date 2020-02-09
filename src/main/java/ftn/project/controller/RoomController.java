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

import ftn.project.dto.RoomDto;
import ftn.project.services.RoomService;
import ftn.project.services.UserService;
import ftn.project.validation.RoomValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Controller
public class RoomController  {

	private final RoomService roomService;
	private final UserService userService;
	private final RoomValidator roomValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(roomValidator);
	}


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
	public String createRoom(@Validated @ModelAttribute("roomDto") RoomDto roomDto,BindingResult result) {
	
		if(result.hasErrors()) {
			return "roomCA";
		}
		
		roomService.create(roomDto);
		return "redirect:/rooms";
	}
	
	@PostMapping("/room/edit")
	public String editRoom(@Validated @ModelAttribute("roomDto") RoomDto roomDto, BindingResult result) {
		
		if(result.hasErrors()) {
			return "roomCA";
		}
		
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
		
		Set<RoomDto> sobe=(Set<RoomDto>)request.getSession().getAttribute("roomsDto");
		
		if(sobe==null) {
			mav.addObject("roomsDto", roomService.allRooms());
		} else {
			mav.addObject("roomsDto", sobe);
		}
		return mav;
			}else {
				return new ModelAndView("badUser");
			}
			}
	}
	
	@PostMapping("/room/search")
	public String searchDoctors(HttpServletRequest request, @ModelAttribute("roomDto") RoomDto roomDto, ModelMap model ) {
		request.getSession().setAttribute("roomsDto", roomService.searchRooms(roomDto.getNameDto(), roomDto.getHallNumberDto()));
		return "redirect:/roomsSearch";
	}
}
