package ftn.project.services;

import java.util.Set;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.ClinicDto;
import ftn.project.dto.RequestDto;
import ftn.project.dto.UserDto;
import ftn.project.model.Appointment;
import ftn.project.model.RegisterRequest;

public interface RequestService {

	Set<RequestDto> allRequests();

	void acceptRequest( Long id,String pacientUsername);

	void rejectRequest( Long id);
	
	void saveRequest(UserDto userDto);
	
	RequestDto getRequest(Long id);
	
	//za slanje zahteva za pregled
	
	Set<AppointmentDto> allSchedulingRequest();
	
	void acceptSchedulingRequest(Long id);
	void rejectSchedulingRequest(Long id);
	
	void createTerm(AppointmentDto appointmentDto);
	void deleteTerm(Long idDto);
	
	Appointment getAppointmentById(Long idDto);
	
	Set<AppointmentDto> allFreeTerms();	
	Set<AppointmentDto> allNotAccepted();	
	
	void acceptUserRequest(Long idDto);
	
	Set<AppointmentDto>allNotAcceptedOperation();
}
