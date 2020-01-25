package ftn.project.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import ftn.project.dto.VacationRequestDto;


@Service
public interface VacationRequestService {

	public void setRole(String s);
	public void createVacReq(VacationRequestDto vacReqDto);
	public Set<VacationRequestDto> allRequests();
	public VacationRequestDto getVQDtoById(Long id);
	
	public boolean posaljiMejlPotvrdan(String mail,String ime);
	public boolean posaljiMejlOdbijen(String mail,String tekst,String subjekat);
	public void obrisiZahtev(Long id);
}
