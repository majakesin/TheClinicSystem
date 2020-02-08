package ftn.project.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.project.model.Appointment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentRepositoryTest {

	@Autowired
	private  AppoitmentRepository appointmentRepository;
	
	@Test
	public void testSaveAppointment() {
		appointmentRepository.save(new Appointment());
		
	}
	
	@Test
	public void testFindAllByIsBussy() {
		
		
	}
	
	
	
}
