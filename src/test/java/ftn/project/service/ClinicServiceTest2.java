package ftn.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.mapper.ClinicMapper;
import ftn.project.mapper.UserMapper;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.ClinicRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.ClinicService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClinicServiceTest2 {

	
	
	
	@Autowired
	private ClinicService clinicService;
	
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Autowired
	private ClinicMapper clinicMapper;
	
	@Autowired
	private  UserRepository userRepository;
	
	@Autowired
	private AppoitmentRepository appointmentRepository;

	
	
	

	//pretraga po datumu i vremenu :)
	
	@Test
	public void testPretragaPregledNemaUnosa () {
		
			clinicService.searchClinicByDoctor("","None");
		
		assertEquals(clinicService.vratiDoktori1(), userMapper.UserToDtoSet(userRepository.findAllByRole("doktor")));
		
		
	}
	
	
	@Test
	public void testPretregaPregledPrazno () {
		clinicService.searchClinicByDoctor("", "Ginekologija");
		
		Set<UserDto> rezultat =  userMapper.UserToDtoSet(userRepository.findAllByRoleAndTipPregleda("doktor","Ginekologija"));

		assertEquals(clinicService.vratiDoktori1(),rezultat);
		
	}
	
	
	
	@Test
	public void testPretragaAdresaIOcena () {
		
			clinicService.searchClinic("", 0.0);
	
		if(clinicService.vratiDoktori1().isEmpty()) {
			
			assertEquals(clinicService.vratiDoktori2(),userMapper.UserToDtoSet(userRepository.findAllByRole("doktor")));
		}
		else {
		
			assertEquals(clinicService.vratiDoktori1(),clinicService.vratiDoktori2());
		}
	}

}
