package ftn.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.ReasonDto;
import ftn.project.dto.RequestDto;
import ftn.project.services.EmailService;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RequestsController {

	@Autowired
	private RequestService requestService;
	private final UserService userService;

	private final EmailService emailService;

	@GetMapping("/requests")
	public ModelAndView showUsers(HttpServletRequest request, ModelMap model) {
		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return new ModelAndView("badUser");
		} else {
			// autorizacija
			if (userService.getCCA()) {

				model.addAttribute("requestsDto", requestService.allRequests());
				return new ModelAndView("registrationRequests", "Model", requestService.allRequests());
			} else {
				return new ModelAndView("badUser");
			}
		}

	}

	@GetMapping("requests/accept/{idDto}")

	public String acceptRequest(@PathVariable("idDto") Long idDto, ModelMap model, HttpServletRequest request) {
		RequestDto tempDto = requestService.getRequest(idDto);
		boolean isAccept = true;
		request.getSession().setAttribute("isAcceptedRequest", isAccept);
		request.getSession().setAttribute("tempRequestDto", tempDto);

		userService.Autorizacija(request);

		// autorizacija
		if (userService.getNull()) {
			return "redirect:/badUser";
		}

		return "redirect:/requests/reason";

	}

	@GetMapping("requests/reject/{idDto}")
	public String rejectRequest(@PathVariable("idDto") Long idDto, ModelMap model, HttpServletRequest request) {
		// autorizacija
		if (userService.getNull()) {
			return "redirect:/badUser";
		}

		RequestDto tempDto = requestService.getRequest(idDto);
		boolean isAccept = false;
		request.getSession().setAttribute("isAcceptedRequest", isAccept);
		request.getSession().setAttribute("tempRequestDto", tempDto);
		return "redirect:/requests/reason";

	}

	@GetMapping("/requests/reason")
	public String reasonPage(@ModelAttribute("reasonDto") ReasonDto reasonDto, HttpServletRequest request) {
		RequestDto tempRequest = (RequestDto) request.getSession().getAttribute("tempRequestDto");
		reasonDto.setEmailDto(tempRequest.getEmailDto());
		return "registrationReason";

	}

	@PostMapping("/requests/reason/send")
	public String sendEmail(@ModelAttribute("reasonDto") ReasonDto reasonDto, HttpServletRequest request) {
		boolean isAccept = (boolean) request.getSession().getAttribute("isAcceptedRequest");
		RequestDto tempRequest = (RequestDto) request.getSession().getAttribute("tempRequestDto");
		if (isAccept) {
			String url = "http://localhost:8081/activate/user/" + tempRequest.getIdDto();
			String content = "Vas aktivacioni link:" + url;
			reasonDto.setTextDto(reasonDto.getTextDto() + content);
		}
		emailService.sendMail(tempRequest.getEmailDto(), reasonDto.getTextDto(), reasonDto.getSubjectDto());

		return "redirect:/requests";

	}

	@GetMapping("/activate/user/{idDto}")
	private String activateUser(@PathVariable("idDto") Long idDto) {
		requestService.acceptUserRequest(idDto);
		return "redirect:/logovanje";
	}
}
