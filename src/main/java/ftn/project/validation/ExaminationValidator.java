package ftn.project.validation;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.dto.PrescriptionDto;




@Component
public class ExaminationValidator implements Validator {


	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return PrescriptionDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "report","Report.empty");
		
		
		
		
		
	}

}