package ftn.project.services_impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.UserDto;
import ftn.project.mapper.UserMapper;
import ftn.project.model.User;
import ftn.project.repository.UserRepository;
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	
	public void createUser(UserDto userDto) {
		userRepository.save(userMapper.DtoToUser(userDto));
	}

	@Override
	public void deleteUser(Long idDto) {
			
		userRepository.deleteById(idDto);
	}

	public UserDto getUserById(Long idDto) {
			
		
		return userMapper.UserToDto(userRepository.findAllById(idDto));
	
		
	}
	
	public Set<UserDto> allUsers() {
		return userMapper.UserToDtoSet(userRepository.findAll());
	}

	@Override
	public Set<UserDto> allMedicalStaff() {
		// TODO Auto-generated method stub
		return userMapper.UserToDtoSet(userRepository.findAllByRole("doktor"));
	}

	@Override
	public Set<UserDto> allNurse() {
		
		return userMapper.UserToDtoSet(userRepository.findAllByRole("med. sestra"));
	}

	

	@Override
	public void editUser(Long idDto) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
