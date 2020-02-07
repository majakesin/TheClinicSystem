package ftn.project.services_impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import ftn.project.dto.OperationDto;
import ftn.project.model.Appointment;
import ftn.project.model.User;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.OperationRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.EmailService;
import ftn.project.services.OperationService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

	private final EmailService emailService;

	private final AppoitmentRepository appointmentRepository;
	
	private final OperationRepository operationRepository;
	
	private final UserService userService;
	
	private final UserRepository userRepository;
	
	@Override
	public void scheduleOperation() {
		// TODO Auto-generated method stub


		emailService.sendMail("prolomercegovac@yahoo.com","Operacija odobrena","Operacija odobrena");


	}

	@Override
	public void createOperation(OperationDto operationDto) {
		Appointment appointment=appointmentRepository.findById(operationDto.getTermId()).get();
		appointment.setAccept(true);
		List<User>doctors=userRepository.findAllById(operationDto.getOperationIds());
		appointment.setDoctors(doctors);
		appointmentRepository.save(appointment);
		for(User u:doctors) {
			if(u.getEmail()!=null) {
				emailService.sendMail(u.getEmail(), "Postovani imate zakazanu operaciju, pogledajte vas radni kalendar", "OPERACIJA");
			}
			
		}
		
		User pacient=userRepository.findById(appointment.getPacientId()).get();
		emailService.sendMail(pacient.getEmail(),"Postovani vasa operacija je zakazana","OPERACIJA");		
	}

	@Override
	public Long fullRooms(Long id) {
		Appointment selectedAppointment=appointmentRepository.findById(id).get();
		List<Appointment>allDates=appointmentRepository.findAll();
		Appointment comparator=new Appointment();
		Collections.sort(allDates);
		return allDates.get(0).getRoomId();
	
	}


}
