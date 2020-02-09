package ftn.project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClinicControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired MockHttpSession session;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testIspisiListuKlinikaSaPretragom() throws Exception {
		
			session.setAttribute("logUsername", "pacijent");
			session.setAttribute("clinicsDto", null);
			session.setAttribute("doctorsDto", null);
			session.setAttribute("doktori2", null);
			
			mockMvc.perform(get("/clincsSearchDateType")
					.session(session))
			.andExpect(status().isOk()).andExpect(view().name("listOfClinics"))
			.andExpect(forwardedUrl("/WEB-INF/view/listOfClinics.jsp"))
			.andExpect(model().attributeExists("termsDto"));
			
	}
	
	@Test 
	public void testPretragaPoAdresiIOceni() throws Exception {
		
			session.setAttribute("logUsername", "pacijent");
			mockMvc.perform(get("/clinicsSearch")
				  	.session(session))
					.andExpect(status().isOk()).andExpect(view().name("listOfClinics2"))
					.andExpect(forwardedUrl("/WEB-INF/view/listOfClinics2.jsp"))
					.andExpect(model().attributeExists("clinicsDto"));
			
	}
	
	
}
