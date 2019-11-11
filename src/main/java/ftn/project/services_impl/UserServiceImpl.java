package ftn.project.services_impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.UserDto;
import ftn.project.mapper.UserMapper;
import ftn.project.repository.UserRepository;
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	
	public void createUser(UserDto userDto) {
		userRepository.save(userMapper.DtoToUser(userDto));
	}

	@Override
	public void deleteUser(Long idDto) {

	}

	
	public Set<UserDto> allUsers() {
		return userMapper.UserToDtoSet(userRepository.findAll());
	}

}
