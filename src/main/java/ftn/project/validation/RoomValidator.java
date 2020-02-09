package ftn.project.validation;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.dto.RoomDto;


import lombok.Data;

@Data
@Component
public class RoomValidator implements Validator {

	
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return RoomDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nameDto","RoomName.empty");
		ValidationUtils.rejectIfEmpty(errors, "hallNumberDto", "RoomHallNumber.empty");
	
				
		
		
	}

}