package ftn.project.services_impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.VacationRequestDto;
import ftn.project.mapper.VacationRequestMapper;
import ftn.project.model.VacationRequest;
import ftn.project.repository.VacationRequestRepository;
import ftn.project.services.EmailService;
import ftn.project.services.VacationRequestService;
import lombok.Data;

@Service
@Data
public class VacationRequestImpl  implements VacationRequestService{
	
	@Autowired
	private 	VacationRequestRepository vqRepository;
	
	private final EmailService emailService;
	
	@Autowired
	private VacationRequestMapper vqMapper;
	
	@Override
	public void setRole(String s) {
		
		
	}

	@Override
	public void createVacReq(VacationRequestDto vacReqDto) {
		
		vqRepository.save(vqMapper.DtoToModel(vacReqDto));
	}

	@Override
	public Set<VacationRequestDto> allRequests() {
			
			
			return vqMapper.ModelToDtoSet(vqRepository.findAll());
			
		
	}


	public VacationRequestDto getVQDtoById(Long id) {
		VacationRequest temp =  vqRepository.findById(id).get();
		return vqMapper.ModelToDto(temp);
	}

	@Override
	public boolean posaljiMejlPotvrdan(String mail,String ime) {
		String tekst = "Postovani " + ime +",vas godisnji odmor je odobren. Pozdrav";
		emailService.sendMail(mail,tekst,"Odgovor za zahtev godisnjeg odmora");
		
		return true;
	}
	public boolean posaljiMejlOdbijen(String mail,String tekst,String subjekat) {
		
		emailService.sendMail(mail, tekst, subjekat);
		return true;
	}

	@Override
	public void obrisiZahtev(Long id) {
		vqRepository.deleteById(id);
		
	}

	


}
