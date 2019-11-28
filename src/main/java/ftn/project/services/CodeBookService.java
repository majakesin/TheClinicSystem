package ftn.project.services;

import java.util.Set;

import ftn.project.dto.CodeBookDto;
import ftn.project.model.CodeBook;

public interface CodeBookService {

	Set<CodeBookDto> allCodeBooks();
	
	void createCodeBook(CodeBookDto codeBook);

	void deleteCodeBook(Long id);

	CodeBookDto editCodeBook(Long id);

}
