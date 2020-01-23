package ftn.project.services;

import java.util.List;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.CalendarDto;

public interface CalendarService {

	public List<CalendarDto> allCalendars(Long id);
	
	
}
