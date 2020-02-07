package ftn.project.validation;


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import ftn.project.dto.RecordsDto;
import ftn.project.model.MedicalRecord;

@Component
public class MedicalRecordsValidator implements Validator {
	
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RecordsDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "heightDto","medicalRecords.heightDto.empty");
		ValidationUtils.rejectIfEmpty(errors, "weightDto", "medicalRecords.weightDto.empty");
		ValidationUtils.rejectIfEmpty(errors, "bloodType", "medicalRecords.bloodType.empty");
		ValidationUtils.rejectIfEmpty(errors, "allergy", "medicalRecords.allergy.empty");
		
		
		RecordsDto record=(RecordsDto)target;
		Pattern pattertn=Pattern.compile("/^[+]?((\\d+(\\.\\d*)?)|(\\.\\d+))$/");
		
		
		
//		if(!pattertn.matcher(Double.toString(record.getHeightDto())).matches()) {
//			errors.rejectValue("heightDto","medicalRecords.height.invalid");
//		}
		
	}


	
	


	
	     
}
