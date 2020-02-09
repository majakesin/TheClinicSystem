package ftn.project.validation;



import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.dto.UserDto;

import lombok.Data;

@Data
@Component
public class ZakaziTerminValidator implements Validator {

	
	
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "datumPregledaDto","TerminDatum.empty");
	
		UserDto termin =(UserDto)target;
		
			if(termin.getDatumPregledaDto() != "") {
			
		
			
		
			String [] datumNiz = termin.getDatumPregledaDto().split("-");
			int godina = Integer.parseInt(datumNiz[0]);
			int mesec = Integer.parseInt(datumNiz[1]);
			int dan = Integer.parseInt(datumNiz[2]);
			
			LocalDate localDate = LocalDate.now();
			
			int Lokalgodina = localDate.getYear();
			int Lokalmesec = localDate.getMonth().getValue();
			int Lokaldan = localDate.getDayOfMonth();
			
			if(godina < Lokalgodina) {
				errors.rejectValue("datumPregledaDto","GodinaLosa");
			}
			
			if(godina==Lokalgodina & mesec<Lokalmesec) {
				errors.rejectValue("datumPregledaDto","MesecLos");
			}
			
			if(godina==Lokalgodina & mesec==Lokalmesec & dan < Lokaldan) {
				errors.rejectValue("datumPregledaDto","DanLos");
			}
			
			
		}else {
			
		}
			
	
		
		
	
		
		
		
		
		
		
		
		
	}

}