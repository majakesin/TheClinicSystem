package ftn.project.validation;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.dto.UserDto;

import lombok.Data;

@Data
@Component
public class IzmeniSifruValidator implements Validator {

	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "passwordDto", "password.empty");
		ValidationUtils.rejectIfEmpty(errors, "PomocnaSifraDto", "password.empty");
		UserDto user=(UserDto)target;
	
		
		if(!user.getPomocnaSifraDto().equals(user.getPasswordDto())) {
			errors.rejectValue("passwordDto","password.invalid");
		}
		
		
		
	}

}