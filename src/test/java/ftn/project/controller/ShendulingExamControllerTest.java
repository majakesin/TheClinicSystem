package ftn.project.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class ShendulingExamControllerTest {
	private MockMvc mockMvc;
	
	@Autowired 
	MockHttpSession session;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetAppointmentRequest() throws Exception {
		session.setAttribute("logUsername", "clinic");
		mockMvc.perform(get("/appointmentRequests")
				.session(session))
		.andExpect(status().isOk()).andExpect(view().name("schedulingRequest"))
		.andExpect(forwardedUrl("/WEB-INF/view/schedulingRequest.jsp"))
		.andExpect(MockMvcResultMatchers.view().name("schedulingRequest"))
		.andExpect(model().attributeExists("appointmentDto"));
		
		assertThat(session.getAttribute("logUsername")).isNotNull();
				
	}
	
	@Test
	public void testGetRoomSearchRequest() throws Exception{
		session.setAttribute("logUsername", "clinic");
		mockMvc.perform(get("/roomsSearch")).andExpect(status().isOk())
		.andExpect(status().isOk()).andExpect(view().name("badUser"))
		.andExpect(forwardedUrl("/WEB-INF/view/badUser.jsp"))
		.andExpect(MockMvcResultMatchers.view().name("badUser"));
		
	}
	
	@Test
	public void testGetNotAvaibleAnyRoom()throws Exception{
		session.setAttribute("logUsername", "clinic");
		session.setAttribute("idTerms", 33L);
		mockMvc.perform(get("/clinic/admin/operations/changeAppointment")
				.session(session))
		.andExpect(status().isOk()).andExpect(view().name("changeAppoitment"))
		.andExpect(forwardedUrl("/WEB-INF/view/changeAppoitment.jsp"))
		.andExpect(model().attributeExists("appointmentDto"))
		.andExpect(model().attributeExists("allRooms"));
		assertThat(session.getAttribute("idTerms")).isNotNull();
		assertThat(session.getAttribute("logUsername")).isNotNull();
		
	}
	
}
