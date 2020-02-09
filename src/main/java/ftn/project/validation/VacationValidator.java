package ftn.project.validation;



import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import ftn.project.dto.VacationRequestDto;
import ftn.project.mapper.UserMapper;
import ftn.project.repository.UserRepository;
import ftn.project.services.UserService;
import lombok.Data;

@Data
@Component
public class VacationValidator implements Validator {

	private final UserService userService;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return VacationRequestDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "pocetakGodisnjegDto", "PocetakGodisnjeg");
		ValidationUtils.rejectIfEmpty(errors, "krajGodisnjegDto", "KrajGodisnjeg");
		VacationRequestDto godisnji =(VacationRequestDto)target;
		
		if(godisnji.getPocetakGodisnjegDto()=="") {
			return;
		}else {
			String [] pocetakNiz = godisnji.getPocetakGodisnjegDto().split("-");
			int godina = Integer.parseInt(pocetakNiz[0]);
			int mesec = Integer.parseInt(pocetakNiz[1]);
			int dan = Integer.parseInt(pocetakNiz[2]);
			
			LocalDate localDate = LocalDate.now();
			
			int Lokalgodina = localDate.getYear();
			int Lokalmesec = localDate.getMonth().getValue();
			int Lokaldan = localDate.getDayOfMonth();
			
			if(godina < Lokalgodina) {
				errors.rejectValue("pocetakGodisnjegDto","GodinaLosa");
			}
			
			if(godina==Lokalgodina & mesec<Lokalmesec) {
				errors.rejectValue("pocetakGodisnjegDto","MesecLos");
			}
			
			if(godina==Lokalgodina & mesec==Lokalmesec & dan < Lokaldan) {
				errors.rejectValue("pocetakGodisnjegDto","DanLos");
			}
			
		}
		if(godisnji.getKrajGodisnjegDto()=="") {
			return;
		}else {
			
			String [] krajNiz = godisnji.getKrajGodisnjegDto().split("-");
			int godina = Integer.parseInt(krajNiz[0]);
			int mesec = Integer.parseInt(krajNiz[1]);
			int dan = Integer.parseInt(krajNiz[2]);
			
			LocalDate localDate = LocalDate.now();
			
			int Lokalgodina = localDate.getYear();
			int Lokalmesec = localDate.getMonth().getValue();
			int Lokaldan = localDate.getDayOfMonth();
			
			if(godina < Lokalgodina) {
				errors.rejectValue("krajGodisnjegDto","GodinaLosa");
			}
			
			if(godina==Lokalgodina & mesec<Lokalmesec) {
				errors.rejectValue("krajGodisnjegDto","MesecLos");
			}
			
			if(godina==Lokalgodina & mesec==Lokalmesec & dan < Lokaldan) {
				errors.rejectValue("krajGodisnjegDto","DanLos");
			}
		}
		
		
	}

}