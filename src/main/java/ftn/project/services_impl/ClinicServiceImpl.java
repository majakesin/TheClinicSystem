package ftn.project.services_impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.mapper.ClinicMapper;
import ftn.project.mapper.UserMapper;
import ftn.project.model.Appointment;
import ftn.project.model.Clinic;
import ftn.project.model.User;


import ftn.project.repository.AppointmentRepository;
import ftn.project.repository.AppoitmentRepository;

import ftn.project.repository.ClinicRepository;

import ftn.project.repository.UserRepository;
import ftn.project.services.ClinicService;
import ftn.project.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Data
@RequiredArgsConstructor
@Service

public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;
	
	
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Autowired
	private ClinicMapper clinicMapper;
	
	private final UserRepository userRepository;
	private final AppoitmentRepository appointmentRepository;
	
	//liste za pretragu klinike
	private Set<ClinicDto> klinike1 =new HashSet<ClinicDto> ();
	private Set<UserDto> doktori1= new HashSet<UserDto>();
	private Set<ClinicDto> klinike2 = new HashSet<ClinicDto>();
	private Set<UserDto> doktori2= new HashSet<UserDto> ();
	private String adresa="";
	private double ocena = 0.0;
	private String tip = "None";
	private String datum = "";
	public void deleteClinic(Long id) {
		clinicRepository.deleteById(id);
	}

	//za testiranje
	public String getAdresa() {
		return adresa;
	}
	
	public String getTip() {
		return tip;
	}
	
	public String getDatum() {
		return datum;
	}
	
	public Double getOcena() {
		return ocena;
	}
	
	//
	public void createClinic(ClinicDto clinicDto) {
	//	User user=userRepository.findByUsername(clinicDto.getAdminDto());
	//	Clinic clinic=clinicMapper.DtoToClinic(clinicDto);
	//	user.setClinic(clinic);
	//	userRepository.save(user);
		
		clinicRepository.save(clinicMapper.DtoToClinic(clinicDto));
		
	}
	
	public Set<ClinicDto> allClinics() {
		return clinicMapper.ClinicToDtoSet(clinicRepository.findAll());
	}

	@Override
	public ClinicDto getClinicById(Long id) {
		// TODO Auto-generated method stub
		return clinicMapper.ClinicToDto(clinicRepository.findAllById(id));
	}

	@Override
	public ClinicDto getClinicProfile(String ussername) {
		// TODO Auto-generated method stub
		
		return clinicMapper.ClinicToDto(clinicRepository.findById(userRepository.findByUsername(ussername).getClinic()).get());
	}

	@Override
	public void editClinicProfile(ClinicDto clinicDto) {
		clinicRepository.save(clinicMapper.DtoToClinic(clinicDto));
		
	}
	
	
	
	
	
	
	
	
	
	
	//pretraga klinike
	@Override
	public Set<ClinicDto> searchClinicByDoctor(String dateDto, String typeDto) {
		
		//zbog testa
		tip = typeDto;
		datum = dateDto;
			
		
		
			
			if(dateDto == "" & typeDto.equals("None")) {
				List<Clinic> k=clinicRepository.findAll();
		
				klinike1.clear();
				doktori1.clear();
				
				klinike1.addAll(clinicMapper.ClinicToDtoSet(k));
				doktori1.addAll(userMapper.UserToDtoSet(userRepository.findAllByRole("doktor")));
				return klinike1;
				
			}else if(dateDto != "" & !typeDto.equals("None")) {
				
				Set<User> doktori = userRepository.findAllByRole("doktor");
				klinike1.clear();
				doktori1.clear();
				for(User doc : doktori) {
					UserDto temp =getDoktoreSlobodneTP(dateDto,typeDto, doc);
					if(!temp.getUsernameDto().equals("nepostojeci")) {
						Long idKlinike = temp.getClinicDto();
						
						Clinic tempClinic = clinicRepository.findById(idKlinike).get();
						ClinicDto tempClinicDto = clinicMapper.ClinicToDto(tempClinic);
						klinike1.add(tempClinicDto);
						doktori1.add(temp);
					}
				
				}
				
				
				return klinike1;
				
			}else if(dateDto != "" & typeDto.equals("None")) {
				Set<User> doktori = userRepository.findAllByRole("doktor");
				klinike1.clear();
				doktori1.clear();
				for(User doc : doktori) {
					UserDto temp =getDoktoreSlobodne(dateDto, doc);
					if(!temp.getUsernameDto().equals("nepostojeci")) {
						
						Long idKlinike = temp.getClinicDto();
						
						Clinic tempClinic = clinicRepository.findById(idKlinike).get();
						ClinicDto tempClinicDto = clinicMapper.ClinicToDto(tempClinic);
						klinike1.add(tempClinicDto);
						doktori1.add(temp);
					}
				
				}
				
				return klinike1;
			
			}else if(dateDto == "" & !typeDto.equals("None")) {
				Set<User> doktori = userRepository.findAllByRole("doktor");
				klinike1.clear();
				doktori1.clear();
				for(User doc : doktori) {
					UserDto temp = getDoktoreTP(typeDto, doc);
					if(!temp.getUsernameDto().equals("nepostojeci")) {
						Long idKlinike = temp.getClinicDto();
						
						Clinic tempClinic = clinicRepository.findById(idKlinike).get();
						ClinicDto tempClinicDto = clinicMapper.ClinicToDto(tempClinic);
						klinike1.add(tempClinicDto);
						doktori1.add(temp);
					}
				
				}
				
				return klinike1;
				
			}
			
		
		
		return klinike1;
	}

	@Override
	public Set<ClinicDto> searchClinic(String adressDto, double markDto) {
	
		//zbog testa
		adresa= adressDto;
		ocena = markDto;
		
		if(klinike1.isEmpty()) {
			
			Set<UserDto> doktori = userMapper.UserToDtoSet(userRepository.findAllByRole("doktor"));
			String datumPregleda=null;
			for(UserDto doc : doktori1) {
				datumPregleda = doc.getDatumPregledaDto();
				break;
			}
			
			if(adressDto=="" & markDto==0.0) {
				List<Clinic> klinikeTemp=clinicRepository.findAll();
				klinike2.clear();
				klinike2.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				doktori2.clear();
				
				for(UserDto doc : doktori) {
					doc.setDatumPregledaDto(datumPregleda);
					doktori2.add(doc);
				}
			
				return klinike2;
				
			}else if(adressDto!="" & markDto!=0.0) {
				Set<Clinic> klinikeTemp=clinicRepository.findByAdressAndMark(adressDto, markDto);
				klinike2.clear();
				klinike2.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				doktori2.clear();
				for(ClinicDto clinic : klinike2) {
					for(UserDto user : doktori) {
						if(user.getClinicDto()==clinic.getIdDto()) {
							user.setDatumPregledaDto(datumPregleda);
							doktori2.add(user);
						}
					}
				}
				return klinike2;
				
			}else if(adressDto!="" & markDto==0.0) {
				Set<Clinic> klinikeTemp=clinicRepository.findByAdress(adressDto);
				klinike2.clear();
				klinike2.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				doktori2.clear();
				for(ClinicDto clinic : klinike2) {
					for(UserDto user : doktori) {
						if(user.getClinicDto()==clinic.getIdDto()) {
							user.setDatumPregledaDto(datumPregleda);
							doktori2.add(user);
						}
					}
				}	
				
				return klinike2;
				
			}else if(adressDto=="" & markDto!=0.0) {
				Set<Clinic> klinikeTemp=clinicRepository.findByMark(markDto);
				klinike2.clear();
				klinike2.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				doktori2.clear();
				for(ClinicDto clinic : klinike2) {
					for(UserDto user : doktori1) {
						if(user.getClinicDto()==clinic.getIdDto()) {
							user.setDatumPregledaDto(datumPregleda);
							doktori2.add(user);
						}
					}
				}	
				
				return klinike2;
				
			}
			
			
			
			
		}else {
				
				klinike2.clear();
			 
				if(adressDto=="" & markDto==0.0) {
					
					klinike2.addAll(klinike1);
					doktori2.clear();
					
					doktori2.addAll(doktori1);
					return klinike2;
					
				}else if(adressDto!="" & markDto!=0.0) {
					for(ClinicDto c : klinike1) {
					
					if(c.adressDto.equalsIgnoreCase(adressDto) & c.markDto ==markDto) {
						klinike2.add(c);
					}
				}
					doktori2.clear();
					
					for(ClinicDto clinic : klinike2) {
						for(UserDto user : doktori1) {
							if(user.getClinicDto()==clinic.getIdDto()) {
								doktori2.add(user);
							}
						}
					}
					return klinike2;
					
				}else if(adressDto!="" & markDto==0.0) {
					for(ClinicDto c : klinike1) {
					if(c.adressDto.equalsIgnoreCase(adressDto)) {
						klinike2.add(c);
					}
				}
					doktori2.clear();
					
					for(ClinicDto clinic : klinike2) {
						for(UserDto user : doktori1) {
							if(user.getClinicDto()==clinic.getIdDto()) {
								doktori2.add(user);
							}
						}
					}
					return klinike2;
					
				}else if(adressDto=="" & markDto!=0.0) {
					for(ClinicDto c : klinike1) {
					if(c.markDto==markDto) {
						klinike2.add(c);
					}
				}
					doktori2.clear();
					
					for(ClinicDto clinic : klinike2) {
						for(UserDto user : doktori1) {
							if(user.getClinicDto()==clinic.getIdDto()) {
								doktori2.add(user);
							}
						}
					}
					return klinike2;
				}
			}
		
		
		return klinike2;
	}

	// PRETRAGA USER
	
	@Override
	public UserDto getDoktoreSlobodne(String datum, User user) {
		
		UserDto temp = new UserDto();
		temp.setUsernameDto("nepostojeci");
		
		String pocetakGod =user.getPocetakGodisnjeg();
		String krajGodisnjeg = user.getKrajGodisnjeg();
		
		if(pocetakGod == null || krajGodisnjeg == null) {
			return userMapper.UserToDto(user);
		}
		String [] pocGod = pocetakGod.split("-");
		String []  krajGod = krajGodisnjeg.split("-");
		String [] datumNiz = datum.split("-");
		
		int pocetak = Integer.parseInt(pocGod[2]);
		int kraj = Integer.parseInt(krajGod[2]);
		int dan = Integer.parseInt(datumNiz[2]);
		
		//ako godina nije ista onda je slobodan
		if(Integer.parseInt(pocGod[0])!=Integer.parseInt(datumNiz[0])) {
			return userMapper.UserToDto(user);
		}
		else if(Integer.parseInt(pocGod[1])!=Integer.parseInt(datumNiz[1])) {
			
			return userMapper.UserToDto(user);
		} else if(dan>pocetak && dan<kraj) {
			return temp;
		}
		else {
			return userMapper.UserToDto(user);
		}
		
		
		
	}
	
	@Override
	public UserDto getDoktoreSlobodneTP(String datum, String tip, User user) {
		
		UserDto temp = new UserDto();
		temp.setUsernameDto("nepostojeci");
		
		String pocetakGod =user.getPocetakGodisnjeg();
		String krajGodisnjeg = user.getKrajGodisnjeg();
		
		if(pocetakGod == null || krajGodisnjeg == null) {
			if(user.getTipPregleda().equals(tip)) {
				return userMapper.UserToDto(user);
			}
			else {
				return temp;
			}
		}
		
		String [] pocGod = pocetakGod.split("-");
		String []  krajGod = krajGodisnjeg.split("-");
		String [] datumNiz = datum.split("-");
		
		int pocetak = Integer.parseInt(pocGod[2]);
		int kraj = Integer.parseInt(krajGod[2]);
		int dan = Integer.parseInt(datumNiz[2]);
		
		//ako godina nije ista onda je slobodan
		if(Integer.parseInt(pocGod[0])!=Integer.parseInt(datumNiz[0])) {
			return userMapper.UserToDto(user);
		}
		else if(Integer.parseInt(pocGod[1])!=Integer.parseInt(datumNiz[1])) {
			
			return userMapper.UserToDto(user);
		} else if(dan>pocetak && kraj<dan) {
			return temp;
		}
		else {
			if(user.getTipPregleda().equals(tip)) {
				return userMapper.UserToDto(user);
			}
			else {
				return temp;
			}
		}
		
	}

	@Override
	public UserDto getDoktoreTP(String tip,User user) {
		UserDto temp = new UserDto();
		temp.setUsernameDto("nepostojeci");
		
		if(user.getTipPregleda().equals(tip)) {
			return userMapper.UserToDto(user);
		}
		else {
			return temp;
		}
	}
	
	public Set<UserDto> vratiDoktori1 () {
		return doktori1;
	}

	@Override
	public Set<UserDto> vratiDoktori2() {
		// TODO Auto-generated method stub
		return doktori2;
	}

}
