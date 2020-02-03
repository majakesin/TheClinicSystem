package ftn.project.services_impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.ClinicDto;
import ftn.project.mapper.ClinicMapper;
import ftn.project.model.Appointment;
import ftn.project.model.Clinic;
import ftn.project.model.User;

import ftn.project.repository.ClinicRepository;
import ftn.project.repository.SchedulingRequestRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.ClinicService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Service
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private ClinicMapper clinicMapper;
	
	private final UserRepository userRepository;
	private final SchedulingRequestRepository appointmentRepository;
	
	//liste za pretragu klinike
	private Set<ClinicDto> klinike1 =new HashSet<ClinicDto> ();
	private Set<ClinicDto> klinike2 = new HashSet<ClinicDto>();

	
	public void deleteClinic(Long id) {
		clinicRepository.deleteById(id);
	}

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
	public Set<ClinicDto> searchClinicByTerm(String dateDto, String typeDto) {
		
	
		User doktor= new User();
		Set<Clinic> klinikeTemp= new HashSet<Clinic>();
		Clinic klinika=new Clinic();
		
			
			if(dateDto == "" & typeDto.equals("None")) {
				List<Clinic> k=clinicRepository.findAll();
				klinike1.clear();
				klinike1.addAll(clinicMapper.ClinicToDtoSet(k));
				return klinike1;
				
			}else if(dateDto != "" & !typeDto.equals("None")) {
				Set<Appointment> termini= appointmentRepository.findByDateAndType(dateDto, typeDto);
				for(Appointment a : termini) {
					if(!a.isBusy()) {
						doktor=userRepository.findAllById(a.getDoctor());
						klinika= clinicRepository.findById(doktor.getClinic()).get();
						klinikeTemp.add(klinika);
					}
				}
				klinike1.clear();
				klinike1.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				return klinike1;
				
			}else if(dateDto != "" & typeDto.equals("None")) {
				Set<Appointment> termini= appointmentRepository.findByDate(dateDto);
				for(Appointment a : termini) {
					if(!a.isBusy()) {
						doktor=userRepository.findAllById(a.getDoctor());
						klinika= clinicRepository.findById(doktor.getClinic()).get();
						klinikeTemp.add(klinika);
					}
				}
				klinike1.clear();
				klinike1.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				return klinike1;
			
			}else if(dateDto == "" & !typeDto.equals("None")) {
				Set<Appointment> termini= appointmentRepository.findByType(typeDto);
				for(Appointment a : termini) {
					if(!a.isBusy()) {
						doktor=userRepository.findAllById(a.getDoctor());
						klinika= clinicRepository.findById(doktor.getClinic()).get();
						klinikeTemp.add(klinika);
					}
				}
				klinike1.clear();
				klinike1.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				return klinike1;
				
			}
			
		
		
		return klinike1;
	}

	@Override
	public Set<ClinicDto> searchClinic(String adressDto, double markDto) {
	
		if(klinike1.isEmpty()) {
			
			
			
			if(adressDto=="" & markDto==0.0) {
				List<Clinic> klinikeTemp=clinicRepository.findAll();
				klinike2.clear();
				klinike2.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				return klinike2;
				
			}else if(adressDto!="" & markDto!=0.0) {
				Set<Clinic> klinikeTemp=clinicRepository.findByAdressAndMark(adressDto, markDto);
				klinike2.clear();
				klinike2.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				return klinike2;
				
			}else if(adressDto!="" & markDto==0.0) {
				Set<Clinic> klinikeTemp=clinicRepository.findByAdress(adressDto);
				klinike2.clear();
				klinike2.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				return klinike2;
				
			}else if(adressDto=="" & markDto!=0.0) {
				Set<Clinic> klinikeTemp=clinicRepository.findByMark(markDto);
				klinike2.clear();
				klinike2.addAll(clinicMapper.ClinicToDtoSet(klinikeTemp));
				return klinike2;
				
			}
			
			
			
			
		}else {
				
			
			 
				if(adressDto=="" & markDto==0.0) {
					
					klinike2.addAll(klinike1);
					return klinike2;
					
				}else if(adressDto!="" & markDto!=0.0) {
					for(ClinicDto c : klinike1) {
					
					if(c.adressDto.equalsIgnoreCase(adressDto) & c.markDto ==markDto) {
						klinike2.add(c);
					}
				}
					return klinike2;
					
				}else if(adressDto!="" & markDto==0.0) {
					for(ClinicDto c : klinike1) {
					if(c.adressDto.equalsIgnoreCase(adressDto)) {
						klinike2.add(c);
					}
				}
					return klinike2;
					
				}else if(adressDto=="" & markDto!=0.0) {
					for(ClinicDto c : klinike1) {
					if(c.markDto==markDto) {
						klinike2.add(c);
					}
				}
					return klinike2;
				}
			}
		
		
		return klinike2;
	}

	

}
