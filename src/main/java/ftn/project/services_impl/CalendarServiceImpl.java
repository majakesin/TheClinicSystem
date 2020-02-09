package ftn.project.services_impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ftn.project.dto.CalendarDto;
import ftn.project.mapper.CalendarMapper;
import ftn.project.model.Appointment;
import ftn.project.model.Calendar;
import ftn.project.model.User;
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
		List<Appointment> allAppointments=schendulingRepository.findAll();
		List<Appointment> doctorAppointments=schendulingRepository.findAllByDoctor(id);
		
		
		
		List<Appointment> reservateApp=new ArrayList<Appointment>();
		reservateApp.addAll(doctorAppointments);
		
		List<User>operationDoctors=new ArrayList<User>();
		for(Appointment a:allAppointments) {
			for(User u:a.getDoctors()) {
				if(u.getId()==id) {
					reservateApp.add(a);
				}
			}
		}


		for(Appointment apoint:reservateApp) {
			calendarsDto.add(calendarMapper.appointmentToCalendarDto(apoint));
		}
		
		return calendarsDto;
		
	}

	
}
