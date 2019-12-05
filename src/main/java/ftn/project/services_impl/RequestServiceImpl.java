package ftn.project.services_impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.RequestDto;
import ftn.project.dto.UserDto;
import ftn.project.mapper.RequestMapper;
import ftn.project.mapper.UserMapper;
import ftn.project.model.MedicalRecord;
import ftn.project.model.RegisterRequest;
import ftn.project.model.User;
import ftn.project.repository.MedicalRecordsRepository;
import ftn.project.repository.RequestRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.RequestService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private RequestMapper requestMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	private final MedicalRecordsRepository medicalRecordsRepository;
	
	@Override
	public Set<RequestDto> allRequests() {
		return requestMapper.setToDtoSet(requestRepository.findAll());
	}

	@Override
	public void acceptRequest(Long id) {
		// TODO Auto-generated method stub
		RegisterRequest registerRequest=requestRepository.findById(id).get();
		User user=requestMapper.mappToUser(requestMapper.requestToDto(registerRequest));
		user.setMedicalRecord(new MedicalRecord());
		userRepository.save(user);
		
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

}
