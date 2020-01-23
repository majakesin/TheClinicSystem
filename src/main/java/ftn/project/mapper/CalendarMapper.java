package ftn.project.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import ftn.project.dto.CalendarDto;
import ftn.project.model.Appointment;
import ftn.project.model.Calendar;
import lombok.Data;

@Data
@Component
public class CalendarMapper {

	public Calendar calendarDtoToCalendar(CalendarDto calendarDto) {
		Calendar calendar = new Calendar();
		calendar.setDoctorId(calendarDto.getMedicalId());
		calendar.setStart(calendarDto.getStart());
		calendar.setEnd(calendarDto.getEnd());
		calendar.setId(calendarDto.getCalendarId());
		return calendar;
	}

	public CalendarDto calendarToCalendarDto(Calendar calendar) {
		CalendarDto calendarDto = new CalendarDto();
		calendarDto.setCalendarId(calendar.getId());
		calendarDto.setStart(calendar.getStart());
		calendarDto.setEnd(calendar.getEnd());
		calendarDto.setMedicalId(calendar.getDoctorId());
		return calendarDto;
	}

	public Set<Calendar> calendarDtoSetToCalendarSet(Collection<CalendarDto> calendarsDto) {
		Set<Calendar> calendars = new HashSet<Calendar>();

		for (CalendarDto calendarDto : calendarsDto) {
			calendars.add(this.calendarDtoToCalendar(calendarDto));
		}
		return calendars;
	}

	public Set<CalendarDto> calendarSetToCalendarDto(Collection<Calendar> calendars) {
		Set<CalendarDto> calendarsDto = new HashSet<CalendarDto>();

		for (Calendar calendar : calendars) {
			calendarsDto.add(this.calendarToCalendarDto(calendar));
		}
		return calendarsDto;
	}
	
	public CalendarDto appointmentToCalendarDto(Appointment appointment){
		
		CalendarDto calendarDto=new CalendarDto();
		String start=appointment.getDate();
		start.replaceAll("-", "/");
		calendarDto.setStart(start);
		calendarDto.setTitle(appointment.getType());
		
		
		return calendarDto;
	}
}