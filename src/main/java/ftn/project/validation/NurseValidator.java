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
public class NurseValidator implements Validator {

	private final UserService userService;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nameDto","SestraIme.empty");
		ValidationUtils.rejectIfEmpty(errors, "surnameDto", "SestraPrezime.empty");
		ValidationUtils.rejectIfEmpty(errors, "usernameDto", "SestraKorisnicko.empty");
		ValidationUtils.rejectIfEmpty(errors, "passwordDto", "SestraSifra.empty");
		ValidationUtils.rejectIfEmpty(errors, "emailDto", "SestraEmail.empty");
		
		
		UserDto user=(UserDto)target;
/*		if(userRepository.findByUsername(user.getUsernameDto())==null) {
			return;
		} */
		
		
		if(userRepository.findByUsername(user.getUsernameDto())!=null) {
			errors.rejectValue("usernameDto","SestraKorisnicko.invalid");
		}else {
			
		}
		
		
/*		if(userRepository.findByEmail(user.getEmailDto())==null) {
			return;
		} */
		
		if(userRepository.findByEmail(user.getEmailDto())!=null) {
			errors.rejectValue("emailDto","JedinstvenEmail.invalid");
		}else {
			
		}
		
		Pattern pattern=Pattern.compile("\\b[\\w.-]+@[\\w.-]+(\\.[\\w.-]+)*\\.[A-Za-z]{2,4}\\b");
		
		
		
		if(!pattern.matcher(user.getEmailDto()).matches()) {
			errors.rejectValue("emailDto","Email.invalid");
		}else {
			
		}
		
		
		
	}

}