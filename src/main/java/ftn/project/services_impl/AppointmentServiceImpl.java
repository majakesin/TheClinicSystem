package ftn.project.services_impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.AppointmentRequestDto;
import ftn.project.mapper.AppointmentMapper;
import ftn.project.mapper.AppointmentRequestMapper;
import ftn.project.repository.AppointmentRepository;

import ftn.project.services.AppointmentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;
	
	private final AppointmentRequestMapper appointmentMapper;
	
	private final AppointmentRepository sRequestRepository;
	private final AppointmentMapper appointmentsMapper;
	
	@Override
	public Set<AppointmentRequestDto> allAppointments() {
		// TODO Auto-generated method stub
		return appointmentMapper.SetAppReqToDto(appointmentRepository.findAll());
	}

	@Override
	public Set<AppointmentDto> allAppointment() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
