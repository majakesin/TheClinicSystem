package ftn.project.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ftn.project.dto.OperationDto;
import ftn.project.dto.RoomDto;
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

	@GetMapping("/{id}")
	public String getPage(@PathVariable("id")Long id,Model model,HttpServletRequest request) throws MailException, MessagingException {
		request.getSession().setAttribute("idTerms", id);
		Long selectedRoom=(Long)request.getSession().getAttribute("selectedRoom");
		if(selectedRoom!=null) {
			
			model.addAttribute("selectedRoom",roomService.getRoom(selectedRoom));
		}
		
		model.addAttribute("doctorsList",userService.allUserByRole("doktor"));
		
		return "operations";
		
	}
	
	@PostMapping("/reservate")
	public String getIds(@RequestBody OperationDto operationDto,HttpServletRequest request) {
		Long selectedRoom=(Long) request.getSession().getAttribute("selectedRoom");
		Long idTerm=(Long)request.getSession().getAttribute("idTerms");
		operationDto.setRoomDto(selectedRoom);
		operationDto.setTermId(idTerm);
		operationService.createOperation(operationDto);
		return "operations";
	}
}
