package ftn.project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ftn.project.dto.CalendarDto;
import ftn.project.dto.UserDto;
import ftn.project.model.Appointment;
import ftn.project.model.User;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.services.AppointmentService;
import ftn.project.services.CalendarService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/calendar")
@Controller
@RequiredArgsConstructor
public class CalendarController {

	private final CalendarService calendarService;

	private final UserService userService;

	private final AppointmentService appointmentService;

	private final AppoitmentRepository appoitmentRepository;

	@GetMapping
	public Set<CalendarDto> getPage(ModelAndView model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Set<CalendarDto> calendars = new HashSet<CalendarDto>();

		CalendarDto c = new CalendarDto();
		c.setCalendarId(1l);
		c.setStart("2013-07-28");
		c.setEnd("2013-07-29");
		c.setTitle("Task in Progress");

		model.setViewName("calendar");

		return calendars;

	}

	@ResponseBody
	@GetMapping("/allEvents")
	public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = (String) request.getSession().getAttribute("logUsername");

		UserDto userDto = userService.getUserProfile(username);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(new Gson().toJson(calendarService.allCalendars(userDto.getIdDto())));

	}
}
