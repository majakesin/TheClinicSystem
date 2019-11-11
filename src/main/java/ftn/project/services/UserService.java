package ftn.project.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import ftn.project.dto.UserDto;
import ftn.project.model.User;

@Service
public interface UserService {

	void createUser(UserDto userDto);

	void deleteUser(Long idDto);
	
	Set<UserDto> allUsers();
	

}
