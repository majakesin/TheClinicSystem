package ftn.project.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import ftn.project.dto.CodeBookDto;
import ftn.project.model.CodeBook;

@Component
public class CodeBookMapper {

	public CodeBook DtoToCodeBook(CodeBookDto codeBookDto) {
		CodeBook codeBook = new CodeBook();
		codeBook.setId(codeBookDto.getIdDto());
		codeBook.setDiagnosisCode(codeBookDto.getDiagnosisCodeDto());
		codeBook.setDiagnosisDescription(codeBookDto.getDiagnosisDescriptionDto());
		codeBook.setDiagnosisName(codeBookDto.getDiagnosisNameDto());
		codeBook.setDrugCode(codeBookDto.getDrugCodeDto());
		codeBook.setDrugName(codeBookDto.getDrugNameDto());
		codeBook.setMedicCode(codeBookDto.getMedicCodeDto());
		codeBook.setDrugDescription(codeBookDto.getDrugDescriptionDto());

		return codeBook;
	}

	public CodeBookDto CodeBookToDto(CodeBook codeBook) {
		CodeBookDto codeBookDto = new CodeBookDto();
		codeBookDto.setDiagnosisCodeDto(codeBook.getDiagnosisCode());
		codeBookDto.setDiagnosisDescriptionDto(codeBook.getDiagnosisDescription());
		codeBookDto.setDiagnosisNameDto(codeBook.getDiagnosisName());
		codeBookDto.setDrugDescriptionDto(codeBook.getDrugDescription());
		codeBookDto.setDrugCodeDto(codeBook.getDrugCode());
		codeBookDto.setDrugNameDto(codeBook.getDrugName());
		codeBookDto.setIdDto(codeBook.getId());
		codeBookDto.setMedicCodeDto(codeBook.getMedicCode());

		return codeBookDto;

	}

	public Set<CodeBookDto> SetToDtoSet(Collection<CodeBook> codeBooks) {
		Set<CodeBookDto> codeBooksDto = new HashSet<CodeBookDto>();

		for (CodeBook codes : codeBooks) {
			codeBooksDto.add(this.CodeBookToDto(codes));
		}

		return codeBooksDto;
	}
	
	public Set<CodeBook> SetDtoToSet(Collection<CodeBookDto> codeBooksDto){
		Set<CodeBook> codeBooks=new HashSet<CodeBook>();
		
		for (CodeBookDto codesDto : codeBooksDto) {
			codeBooks.add(this.DtoToCodeBook(codesDto));
		}

		return codeBooks;
		
	}

}
