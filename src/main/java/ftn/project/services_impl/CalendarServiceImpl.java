package ftn.project.services_impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ftn.project.dto.CalendarDto;
import ftn.project.mapper.CalendarMapper;
import ftn.project.model.Appointment;
import ftn.project.model.Calendar;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.services.CalendarService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

	private final AppoitmentRepository schendulingRepository;
	
	private final CalendarMapper calendarMapper;
	
	@Override
	public List<CalendarDto> allCalendars(Long id) {
		// TODO Auto-generated method stub
		List<CalendarDto>calendarsDto=new ArrayList<CalendarDto>();
		List<Appointment> appointDto=schendulingRepository.findAllByDoctor(id);
		for(Appointment apoint:appointDto) {
			calendarsDto.add(calendarMapper.appointmentToCalendarDto(apoint));
		}
		return calendarsDto;
		
	}

	
}
