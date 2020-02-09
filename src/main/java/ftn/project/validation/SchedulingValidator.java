package ftn.project.validation;



import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.UserDto;
import ftn.project.mapper.AppointmentMapper;
import ftn.project.mapper.UserMapper;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.UserRepository;
import lombok.Data;

@Data
@Component
public class SchedulingValidator implements Validator {

	private AppoitmentRepository srRepository;
	private AppointmentMapper appointmentMapper;
	private UserMapper userMapper;
	private UserRepository userRepository;
	
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return AppointmentDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "dateDto","TerminDatum.empty");
		ValidationUtils.rejectIfEmpty(errors, "timeDto", "TerminVreme.empty");
		ValidationUtils.rejectIfEmpty(errors, "priceDto", "TerminCena.empty");
		ValidationUtils.rejectIfEmpty(errors, "discountDto", "TerminPopust.empty");
		
		AppointmentDto termin =(AppointmentDto)target;
		UserDto doktorTermin = new UserDto();
		
	/*	if(userRepository.findAllById(termin.getDoctorDto())==null) {
			return;
		}
		
		
		if(userRepository.findAllById(termin.getDoctorDto())!=null) {
			doktorTermin = userMapper.UserToDto(userRepository.findAllById(termin.getDoctorDto()));
		}
			
		
		
		if(!(srRepository.findByDate(termin.getDateDto())).isEmpty()) {
		

			
			Set<AppointmentDto> listaTermina = appointmentMapper.setToDtoSet(srRepository.findByDate(termin.getDateDto()));
			for(AppointmentDto term : listaTermina) {
				UserDto doktorTerm = userMapper.UserToDto(userRepository.findAllById(term.getDoctorDto()));
				if((term.getDateDto().equals(termin.getDateDto()) & term.getTimeDto().equals(termin.getTimeDto()) & term.getRoomDto().equals(termin.getRoomDto())) | (term.getDateDto().equals(termin.getDateDto()) & term.getTimeDto().equals(termin.getTimeDto()) & doktorTerm.equals(doktorTermin))) {
					errors.rejectValue("dateDto","Termin.invalid");
				}
				}
			}else {
				
			} */
			
		
			 
		
		if(termin.getDateDto()=="") {
			return;
		}else {
			String [] datumNiz = termin.getDateDto().split("-");
			int godina = Integer.parseInt(datumNiz[0]);
			int mesec = Integer.parseInt(datumNiz[1]);
			int dan = Integer.parseInt(datumNiz[2]);
			
			LocalDate localDate = LocalDate.now();
			
			int Lokalgodina = localDate.getYear();
			int Lokalmesec = localDate.getMonth().getValue();
			int Lokaldan = localDate.getDayOfMonth();
			
			if(godina < Lokalgodina) {
				errors.rejectValue("dateDto","GodinaLosa");
			}
			
			if(godina==Lokalgodina & mesec<Lokalmesec) {
				errors.rejectValue("dateDto","MesecLos");
			}
			
			if(godina==Lokalgodina & mesec==Lokalmesec & dan < Lokaldan) {
				errors.rejectValue("dateDto","DanLos");
			}
			
		}
		
		
	
		
		
		
		
		
		
		
		
	}

}