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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchendulingExaminationControllerTest {

	
	private MockMvc mockMvc;

	@Autowired MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetClinicList() throws Exception {
		session.setAttribute("logUsername", "paci");
		mockMvc.perform(get("/listaKlinikaProfil")
				.session(session))
				.andExpect(forwardedUrl("/WEB-INF/view/listaKlinikaProfil.jsp"))
				.andExpect(status().isOk()).andExpect(view().name("listaKlinikaProfil"))
				.andExpect(MockMvcResultMatchers.view().name("listaKlinikaProfil"))
				.andExpect(model().attributeExists("klinikeDto"));
	}
	
	@Test
	public void testGetClinicListProfile() throws Exception {
		session.setAttribute("logUsername", "paci");
		mockMvc.perform(get("/profilKlinikePacijent/4")
				.session(session))
				.andExpect(forwardedUrl("/WEB-INF/view/clinicProfilPatient.jsp"))
				.andExpect(status().isOk()).andExpect(view().name("clinicProfilPatient"))
				.andExpect(MockMvcResultMatchers.view().name("clinicProfilPatient"))
				.andExpect(model().attributeExists("clinicDto"));
	}
	
	
	@Test
	public void testGetClinicProfilePatient() throws Exception {
		session.setAttribute("logUsername", "paci");
		mockMvc.perform(get("/listaDoktoraKlinike/4")
				.session(session))
				.andExpect(forwardedUrl("/WEB-INF/view/PretragaDoktoraProfil.jsp"))
				.andExpect(status().isOk()).andExpect(view().name("PretragaDoktoraProfil"))
				.andExpect(MockMvcResultMatchers.view().name("PretragaDoktoraProfil"))
				.andExpect(model().attributeExists("doctorDto"));
	}
	
	
	
}
