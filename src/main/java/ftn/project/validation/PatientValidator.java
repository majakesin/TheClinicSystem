package ftn.project.validation;



import java.util.regex.Pattern;

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
public class PatientValidator implements Validator {

	private final UserService userService;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "usernameDto","PacijentKoris.empty");
		ValidationUtils.rejectIfEmpty(errors, "passwordDto", "PacijentSifra.empty");
		ValidationUtils.rejectIfEmpty(errors, "pomocnaSifraDto","PacijentPomocna.empty");
		ValidationUtils.rejectIfEmpty(errors, "insuranceNumberDto","JMBG.empty");
		ValidationUtils.rejectIfEmpty(errors, "emailDto","PacijentEmail.empty");
		UserDto user=(UserDto)target;
		
	/*	if(userRepository.findByUsername(user.getUsernameDto())==null) {
			return;
		} */
		
		
		if(userRepository.findByUsername(user.getUsernameDto())!=null) {
			errors.rejectValue("usernameDto","Pacijent.invalid");
		}else {
			
		}
		
		
		
		if(user.getInsuranceNumberDto().length()!=13) {
			errors.rejectValue("insuranceNumberDto","JMBG.invalid");
		}else {
			
		}
		
		if(!user.getPomocnaSifraDto().equals(user.getPasswordDto())) {
			errors.rejectValue("passwordDto","PacijentPassword.invalid");
		}else {
			
		}
		
/*		if(userRepository.findByEmail(user.getEmailDto())==null) {
			return;
		} */
		
		if(userRepository.findByEmail(user.getEmailDto())!=null) {
			errors.rejectValue("emailDto","JedinstvenEmail.invalid");
		}else {
			
		}
		
		Pattern pattern=Pattern.compile("\\b[a-z0-9\\._\\-]+@[a-z0-9\\.\\-]+\\.[a-z]{2,7}\\b");
		
		
		
		if(!pattern.matcher(user.getEmailDto()).matches()) {
			errors.rejectValue("emailDto","Email.invalid");
		}else {
			
		}
		
		
	}

}