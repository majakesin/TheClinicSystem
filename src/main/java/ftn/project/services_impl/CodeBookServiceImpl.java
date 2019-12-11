package ftn.project.services_impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import ftn.project.dto.CodeBookDto;
import ftn.project.mapper.CodeBookMapper;
import ftn.project.model.CodeBook;
import ftn.project.repository.CodeBookRepository;
import ftn.project.services.CodeBookService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class CodeBookServiceImpl implements CodeBookService {

	private final CodeBookRepository codeBookRepository;
	
	private final CodeBookMapper codeBookMapper;
	
	@Override
	public void createCodeBook(CodeBookDto codeBookDto) {
		codeBookRepository.save(codeBookMapper.DtoToCodeBook(codeBookDto));
	}

	@Override
	public void deleteCodeBook(Long id) {
		codeBookRepository.deleteById(id);
	}

	@Override
	public CodeBookDto editCodeBook(Long id) {
		CodeBook codeBook=codeBookRepository.findById(id).get();
		return codeBookMapper.CodeBookToDto(codeBook);
	}

	@Override
	public Set<CodeBookDto> allCodeBooks() {
		return codeBookMapper.SetToDtoSet(codeBookRepository.findAll());
	}

}
