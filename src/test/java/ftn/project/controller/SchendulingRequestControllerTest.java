package ftn.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import ftn.project.dto.AppointmentDto;
import ftn.project.services.ClinicService;
import ftn.project.services.RequestService;
import ftn.project.services.RoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchendulingRequestControllerTest {

	private MockMvc mockMvc;

	@Autowired MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetCreateTerm() throws Exception {
		session.setAttribute("logUsername", "clinic");
		mockMvc.perform(get("/createTerm")
				.session(session))
				.andExpect(status().isOk()).andExpect(view().name("createTerm"))
				.andExpect(forwardedUrl("/WEB-INF/view/createTerm.jsp"))
				.andExpect(MockMvcResultMatchers.view().name("createTerm"))
				.andExpect(model().attributeExists("termsDto"))
				.andExpect(model().attributeExists("allRooms"));
	}

	@Test
	public void testGetClinicList() throws Exception {
		session.setAttribute("logUsername", "paci");
		mockMvc.perform(get("/listaKlinika")
				.session(session))
				.andExpect(forwardedUrl("/WEB-INF/view/freeTerms.jsp"))
				.andExpect(status().isOk()).andExpect(view().name("freeTerms"))
				.andExpect(MockMvcResultMatchers.view().name("freeTerms"))
				.andExpect(model().attributeExists("klinikeDto"));
	}

	@Test
	public void testGetAllFreeTerms() throws Exception {
		session.setAttribute("logUsername", "paci");
		mockMvc.perform(get("/clinic/terms/3")
				.session(session))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/view/predefinisaniPregledi.jsp"))
				.andExpect(view().name("predefinisaniPregledi"))
				.andExpect(MockMvcResultMatchers.view().name("predefinisaniPregledi"))
				.andExpect(model().attributeExists("terminiDto"));
	}

	@Test
	public void testCalendar()throws Exception{
		String datum="2020-02-15";
		String vreme="05:00";
		String sala="1";
		
		session.setAttribute("logUsername", "drag");
		
		mockMvc.perform(get("/calendar/allEvents")
				.session(session)
				.param("datum",datum)
				.param("vreme", vreme)
				.param("sala", sala))
		.andExpect(status().isOk());
	}

}
