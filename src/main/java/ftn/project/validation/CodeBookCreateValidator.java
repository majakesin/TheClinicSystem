package ftn.project.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ftn.project.dto.CodeBookDto;
import ftn.project.dto.UserDto;
import ftn.project.mapper.CodeBookMapper;
import ftn.project.mapper.UserMapper;
import ftn.project.repository.CodeBookRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.CodeBookService;
import ftn.project.services.UserService;
import lombok.Data;

@Data
@Component
public class CodeBookCreateValidator implements Validator {

	private final CodeBookService codeBookService;
	private final CodeBookRepository codeBookRepository;
	private final CodeBookMapper codeBookMapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return CodeBookDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "diagnosisCodeDto","SifraDijagnozePrazna");
		ValidationUtils.rejectIfEmpty(errors, "diagnosisNameDto", "NazivDijagnozePrazan");
		ValidationUtils.rejectIfEmpty(errors, "drugCodeDto", "DijagnozaLekaPrazna");
		ValidationUtils.rejectIfEmpty(errors, "drugNameDto", "ImeLekaPrazno");
		ValidationUtils.rejectIfEmpty(errors, "medicCodeDto", "MedicalCodeEmpty");
		CodeBookDto book=(CodeBookDto)target;
		
		if(codeBookRepository.findByDiagnosisCode(book.getDiagnosisCodeDto())==null) {
			return;
		}
		
		if(codeBookRepository.findByDrugCode(book.getDrugCodeDto())==null) {
			return;
		}
		
		if(codeBookRepository.findByMedicCode(book.getMedicCodeDto())==null) {
			return;
		}
		
		
		if(codeBookRepository.findByDiagnosisCode(book.getDiagnosisCodeDto())!=null) {
			errors.rejectValue("diagnosisCodeDto","SifraDijagnozeJedinstvena");
		}
		
		if(codeBookRepository.findByDrugCode(book.getDrugCodeDto())!=null) {
			errors.rejectValue("drugCodeDto","SifraLekaJedinstvena");
		}
		
		if(codeBookRepository.findByMedicCode(book.getMedicCodeDto())!=null) {
			errors.rejectValue("medicCodeDto","MedicCodeUniq");
		}
		
	}

}
