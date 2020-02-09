package ftn.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.project.dto.UserDto;
import ftn.project.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchendulingExaminationServiceTest {


		@Autowired
		private UserService userService;
		
		@Test
		public void proveriGetIdUser( ) {
			UserDto user = userService.getUserById((long) 12);
			assertEquals("paci",user.getUsernameDto());
			
		}
		
		@Test
		public void proveriVracanjePoUlozi() {
			
			assertThat(userService.allUserByRole("doktor")).hasSize(1);
		}
		
		
			
		
	
}
