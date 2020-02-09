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
public class UserControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired MockHttpSession session;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void TestKreirajZahtev() throws Exception {
		

		session.setAttribute("logUsername", "pacijent");
		
		
		mockMvc.perform(get("/kreirajNP1/4")
				.session(session))
		.andExpect(status().isOk()).andExpect(view().name("zakaziNPD1"))
		.andExpect(forwardedUrl("/WEB-INF/view/zakaziNPD1.jsp"))
		.andExpect(model().attributeExists("docVreme"))
		.andExpect(model().attributeExists("doktor"));
	}
	
	@Test
	public void TestKreirajZahtev2() throws Exception {
		

		session.setAttribute("logUsername", "pacijent");
		
		
		mockMvc.perform(get("/kreirajNP2/4")
				.session(session))
		.andExpect(status().isOk()).andExpect(view().name("zakazanNPD2"))
		.andExpect(forwardedUrl("/WEB-INF/view/zakazanNPD2.jsp"))
		.andExpect(model().attributeExists("docVreme"))
		.andExpect(model().attributeExists("doktor"));
	}
	
	@Test
	public void testListaDoktoraKlinike1() throws Exception {
		
		session.setAttribute("logUsername", "pacijent");
		mockMvc.perform(get("/zakaziNepredefinisani2/1")
				.session(session))
		.andExpect(status().isOk()).andExpect(view().name("DoktorNepredf2"))
		.andExpect(forwardedUrl("/WEB-INF/view/DoktorNepredf2.jsp"))
		.andExpect(model().attributeExists("doctorDtoP"))
		.andExpect(model().attributeExists("clinicDto"))
		.andExpect(model().attributeExists("doctorsDto"));
	}
	
	
	
	
}
