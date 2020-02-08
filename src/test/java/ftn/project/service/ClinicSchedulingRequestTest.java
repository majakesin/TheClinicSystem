package ftn.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.mapper.AppointmentMapper;
import ftn.project.model.Appointment;
import ftn.project.model.User;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.AppointmentService;
import ftn.project.services.ClinicService;
import ftn.project.services.RequestService;
import ftn.project.services.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClinicSchedulingRequestTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private AppoitmentRepository appoitmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AppointmentMapper appointmentMapper;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Test
	public void testClinicsReturnSizeOfSet() {
		Set<ClinicDto>allClinics=clinicService.allClinics();
		assertThat(allClinics).hasSize(1);
	}
	
	@Test
	public void testTermsReturnSizeOfTerms() {
		assertThat(requestService.allFreeTerms()).isNotNull();
	}
	
	@Test
	public void testGetUserReturnDoctor() {
		UserDto doctor=userService.getUserById(5L);
		assertEquals(doctor.getRoleDto(), "doktor");
		assertEquals(doctor.getIdDto(), 5L);
	}

	@Test
	public void testGetAppointmentByIdReturnAppointment() {
		AppointmentDto appointmentDto=appointmentService.getAppointement(33L);
		Appointment appointment=appoitmentRepository.findById(33L).get();
		
		assertEquals(appointment.getId(), 33L);
		assertEquals(appointmentDto.getIdDto(), 33L);
		
		assertEquals(appointment.getDoctor(), 5L);
		assertEquals(appointmentDto.getDoctorDto(),5L);
		
	}
	
	@Test
	public void testGetUserByUsernameReturnPacient() {
		User pacient= userRepository.findByUsername("paci");
		UserDto pacientDto=userService.getUserByUsername("paci");
		
		assertThat(pacient).isNotNull();
		assertThat(pacientDto).isNotNull();
	}
	
	@Test
	public void testProcessOfShendulingAppointment() {
		User pacient= userRepository.findByUsername("paci");
		AppointmentDto appointmentDto=appointmentService.getAppointement(33L);
		appointmentDto.setPacientId(pacient.getId());
		
		Appointment appointment=appointmentMapper.dtoToAppointment(appointmentDto);
		appoitmentRepository.save(appointment);
		
		Appointment testAppointment=appoitmentRepository.findById(33L).get();
		
		assertThat(testAppointment).isNotNull();
		assertEquals(appointment.getPacientId(),1L);
		assertEquals(appointment.getId(),33L);
		
	}
	
	
	
	
}
