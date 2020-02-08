package ftn.project.services_impl;

import java.util.HashSet;
import java.util.List;
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
import ftn.project.model.MedicalRecord;
import ftn.project.model.RegisterRequest;
import ftn.project.model.User;
import ftn.project.repository.MedicalRecordsRepository;
import ftn.project.repository.RequestRepository;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.RequestService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

	private final RequestRepository requestRepository;

	private final RequestMapper requestMapper;

	private final UserMapper userMapper;

	private final UserRepository userRepository;

	private final MedicalRecordsRepository medicalRecordsRepository;

	// za slanje zahteva za pregled

	
	private final AppoitmentRepository sRequestRepository;
	
	private final AppointmentMapper appointmentMapper;

	// to je za prijavu
	@Override
	public Set<RequestDto> allRequests() {
		return requestMapper.setToDtoSet(requestRepository.findAll());
	}

	@Override
	public void acceptRequest(Long id,String username) {
		// TODO Auto-generated method stub
		Appointment registerRequest = sRequestRepository.findById(id).get();
		registerRequest.setBusy(true);
		registerRequest.setAccept(true);
		
		User user=userRepository.findByUsername(username);
		registerRequest.setPacientId(user.getId());
		
		sRequestRepository.save(registerRequest);

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
		Appointment appointmentRequest = sRequestRepository.findById(id).get();
		appointmentRequest.setBusy(true);
		appointmentRequest.setAccept(false);
		sRequestRepository.save(appointmentRequest);

	}

	@Override
	public void rejectSchedulingRequest(Long id) {
		Appointment appointmentRequest = sRequestRepository.findById(id).get();
		appointmentRequest.setBusy(false);
		appointmentRequest.setAccept(false);
		sRequestRepository.save(appointmentRequest);

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
	public Appointment getAppointmentById(Long idDto) {
		return	sRequestRepository.findById(idDto).get();
	}

	@Override
	public Set<AppointmentDto> allFreeTerms() {
		return appointmentMapper.setToDtoSet(sRequestRepository.findAllByIsBusy(false));
	}
	
	public Set<AppointmentDto> allNotAccepted(){
		return appointmentMapper.setToDtoSet(sRequestRepository.findAllByisAccept(true));
	}

	@Override
	public void acceptUserRequest(Long idDto) {
		RegisterRequest reg=requestRepository.findById(idDto).get();
		User user=requestMapper.mappToUser(requestMapper.requestToDto(reg));
		user.setMedicalRecord(new MedicalRecord());
		userRepository.save(user);
		requestRepository.delete(reg);
		
	}


	@Override
	public RequestDto getRequest(Long id) {
		return requestMapper.requestToDto(requestRepository.findById(id).get());
	}

	
}
