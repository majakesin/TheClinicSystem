package ftn.project.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ftn.project.dto.CodeBookDto;
import ftn.project.services.CodeBookService;
import ftn.project.services.UserService;
import ftn.project.services_impl.EmailServiceImpl;
import ftn.project.validation.CodeBookCreateValidator;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/administrators/codebook")
public class CodeBookController {

	private final CodeBookService codeBookService;

	private  EmailServiceImpl emailService;
	private final UserService userService;
	private final CodeBookCreateValidator codeBookValidator;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(codeBookValidator);
	}
	
	@GetMapping
	public ModelAndView getCodeBook(HttpServletRequest request,@ModelAttribute("codeBookDto") CodeBookDto codeBookDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return new ModelAndView("badUser");
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		
		model.addAttribute("codeBooksDto", codeBookService.allCodeBooks());
		return new ModelAndView("codeBook", "Model", codeBookService.allCodeBooks());
			}else {
				return new ModelAndView("badUser");
			}
			}
	}

	@PostMapping("/create")
	public String createCodeBook(@Validated @ModelAttribute("codeBookDto") CodeBookDto codeBookDto, BindingResult result) throws MailException, MessagingException {
		
		if(result.hasErrors()) {
			return "codeBook";
		}
		codeBookService.createCodeBook(codeBookDto);
		
		return "redirect:/administrators/codebook";
	}

	@GetMapping("/delete/{idDto}")
	public String deleteCodeBook(HttpServletRequest request,@PathVariable("idDto") Long idDto, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCCA()){
		codeBookService.deleteCodeBook(idDto);
		return "redirect:/administrators/codebook";
			}else {
				return "redirect:/badUser";
			}
			}
	}

	@GetMapping("/edit/{idDto}")
	public String getEditPage( HttpServletRequest request,@PathVariable("idDto") Long id, ModelMap model) {
		userService.Autorizacija(request);
		
		//autorizacija
		if(userService.getNull()) {
			return "redirect:/badUser";
		}
		else {
			//autorizacija
			if(userService.getCCA()){
				
		
		model.addAttribute("codeBookDto", codeBookService.editCodeBook(id));
		return "codeBookEdit";
			}else {
				return "redirect:/badUser";
			}
			}
	}

	@PostMapping("/edit/create")
	public String editCodeBook(@Validated @ModelAttribute("codeBookDto") CodeBookDto codeBookDto ,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			return "codeBookEdit";
		}
		
		codeBookService.createCodeBook(codeBookDto);
		return "redirect:/administrators/codebook";
	}

}
