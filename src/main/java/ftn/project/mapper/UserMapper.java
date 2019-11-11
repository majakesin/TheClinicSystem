package ftn.project.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import ftn.project.dto.UserDto;
import ftn.project.model.User;
import lombok.Data;

@Component
@Data
public class UserMapper {

	public UserDto UserToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setIdDto(user.getId());

		userDto.setUsernameDto(user.getUsername());
		userDto.setPasswordDto(user.getPassword());
		userDto.setEmailDto(user.getEmail());

		userDto.setNameDto(user.getName());
		userDto.setSurnameDto(user.getSurname());
		userDto.setAddressDto(user.getAdress());
		userDto.setCityDto(user.getCity());
		userDto.setPhoneDto(user.getPhone());
		userDto.setRoleDto(user.getRole());

		return userDto;
	}

	public User DtoToUser(UserDto userDto) {
		User user = new User();

		user.setId(userDto.getIdDto());

		user.setUsername(userDto.getUsernameDto());
		user.setPassword(userDto.getPasswordDto());
		user.setEmail(userDto.getEmailDto());

		user.setName(userDto.getNameDto());
		user.setSurname(userDto.getSurnameDto());
		user.setAdress(userDto.getAddressDto());
		user.setCity(userDto.getCityDto());
		user.setPhone(userDto.getPhoneDto());
		user.setRole(userDto.getRoleDto());

		return user;
	}

	public Set<User> DtoToUserSet(Collection<UserDto> usersDto) {

		Set<User> users = new HashSet<User>();

		for (UserDto userDto : usersDto) {
			users.add(this.DtoToUser(userDto));
		}

		return users;
	}
	
	public Set<UserDto> UserToDtoSet(Collection<User>users){
		Set<UserDto>usersDto=new HashSet<UserDto>();
		
		for(User user:users) {
			usersDto.add(this.UserToDto(user));
		}
		
		return usersDto;
	}

}
