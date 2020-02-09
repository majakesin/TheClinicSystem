package ftn.project.validation;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.dto.ClinicDto;

import lombok.Data;

@Data
@Component
public class ClinicCreateValidator implements Validator {

	
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return ClinicDto.class.equals(clazz);
	}

	
	@Override
	public void validate(Object target, Errors errors) {
		
	
		ClinicDto cl=(ClinicDto)target;
	
		
		ValidationUtils.rejectIfEmpty(errors, "nameDto","CCV.nameDto.empty");
		ValidationUtils.rejectIfEmpty(errors, "adressDto", "CCV.adressDto.empty");
		ValidationUtils.rejectIfEmpty(errors, "phoneDto", "CCV.phoneDto.empty");
		
		
		
		
		
	}

}