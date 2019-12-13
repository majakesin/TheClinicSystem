package ftn.project.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.AppointmentRequestDto;


public interface AppointmentService {

	Set<AppointmentRequestDto> allAppointments();
	
	
}
