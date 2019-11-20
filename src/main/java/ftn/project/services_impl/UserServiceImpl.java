package ftn.project.services_impl;

import java.util.List;
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

	
	public Set<UserDto> allUsers() {
		return userMapper.UserToDtoSet(userRepository.findAll());
	}

	@Override
	public Set<UserDto> allMedicalStaff() {
		// TODO Auto-generated method stub
		return userMapper.UserToDtoSet(userRepository.findAllByRole("doktor"));
	}

	@Override
	public UserDto getUserById(String username) {
		// TODO Auto-generated method stub
		return userMapper.UserToDto(userRepository.findByUsername(username));
	}

	
	
	
	/*
		
	public Set<UserDto> allMedicalStaff() {
		
		
		
		List<User> sviKorisnici =  userRepository.findAll();
		
		System.out.println("bio sam ovdje");
		
		for (User user : sviKorisnici) {
			
			System.out.println("korisnik:"+user.getName());
			
			if(!user.getRole().equals("doktor")) {
				
				sviKorisnici.remove(user);
			}
		
		}
		if(sviKorisnici.isEmpty()) {
		
			return userMapper.UserToDtoSet(userRepository.findAll());
		}
		else {
			return userMapper.UserToDtoSet(sviKorisnici);
		}
	} */
}
