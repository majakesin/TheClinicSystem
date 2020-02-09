package ftn.project.validation;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.dto.UserDto;
import ftn.project.mapper.UserMapper;
import ftn.project.repository.UserRepository;
import ftn.project.services.UserService;
import lombok.Data;

@Data
@Component
public class CaCcaValidator implements Validator {

	private final UserService userService;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "usernameDto","CaCca.usernameDto.empty");
		ValidationUtils.rejectIfEmpty(errors, "passwordDto", "CaCca.passwordDto.empty");
		UserDto user=(UserDto)target;
		if(userRepository.findByUsername(user.getUsernameDto())==null) {
			return;
		}
		
		
		if(userRepository.findByUsername(user.getUsernameDto())!=null) {
			errors.rejectValue("usernameDto","CaCca.usernameDto.invalid");
		}
		
		
		
	}

}