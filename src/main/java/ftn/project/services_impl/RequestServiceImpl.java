package ftn.project.services_impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.RequestDto;
import ftn.project.dto.UserDto;
import ftn.project.mapper.AppointmentMapper;
import ftn.project.mapper.RequestMapper;
import ftn.project.mapper.UserMapper;
import ftn.project.model.Appointment;
import ftn.project.model.RegisterRequest;
import ftn.project.repository.RequestRepository;
import ftn.project.repository.UserRepository;
import ftn.project.repository.SchedulingRequestRepository;
import ftn.project.services.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private RequestMapper requestMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	
	// za slanje zahteva za pregled
	
	@Autowired
	private SchedulingRequestRepository sRequestRepository;
	
	@Autowired
	private AppointmentMapper appointmentMapper;
	
	
	@Override
	public Set<RequestDto> allRequests() {
		return requestMapper.setToDtoSet(requestRepository.findAll());
	}

	@Override
	public void acceptRequest(Long id) {
		// TODO Auto-generated method stub
		RegisterRequest registerRequest=requestRepository.findById(id).get();
		
		userRepository.save(requestMapper.mappToUser(requestMapper.requestToDto(registerRequest)));
		requestRepository.deleteById(id);

	}

	@Override
	public void rejectRequest(Long id) {
		// TODO Auto-generated method stub
		requestRepository.deleteById(id);
	}

	@Override
	public void saveRequest(UserDto userDto) {
		
		requestRepository.save(requestMapper.mappToRequest(userDto));
		
	}
	
	// za kreiranje termina i slanje zahteva za pregled

	@Override
	public Set<AppointmentDto> allSchedulingRequest() {

		return appointmentMapper.setToDtoSet(sRequestRepository.findAll());
	}

	@Override
	public void acceptSchedulingRequest(Long id) {
		
	Appointment appointmentRequest=sRequestRepository.findById(id).get();
		
		sRequestRepository.save(appointmentRequest);
		
		
	}

	@Override
	public void rejectSchedulingRequest(Long id) {
		
		sRequestRepository.deleteById(id);
		
	}

	@Override
	public void createTerm(AppointmentDto appointmentDto) {

		sRequestRepository.save(appointmentMapper.dtoToAppointment(appointmentDto));
		
	}

	@Override
	public void deleteTerm(Long idDto) {
		
		sRequestRepository.deleteById(idDto);
		
		
	}

	@Override
	public AppointmentDto getAppointmentById(Long idDto) {
	
		return appointmentMapper.appointmentToDto(sRequestRepository.findAllById(idDto)) ;
	}

	

}
