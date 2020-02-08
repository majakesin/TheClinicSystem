package ftn.project.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import ftn.project.services.ClinicService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchendulingRequestControllerTest {

	private MockMvc mockMvc;

	@Mock
	private Model model;
	
	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@Test
	public void testGetClinicList() throws Exception {	
		mockMvc.perform(get("/listaKlinika"))
		.andExpect(status().isOk())
		.andExpect(view().name("badUser"))
		.andExpect(MockMvcResultMatchers.view().name("badUser"))
		.andExpect(model().attributeDoesNotExist("klinikeDto"));
	}
	
	@Test
	public void testGetAllFreeTerms() throws Exception{
		mockMvc.perform(get("/clinic/terms/3"))
		.andExpect(status().isOk())
		.andExpect(view().name("predefinisaniPregledi"))
		.andExpect(MockMvcResultMatchers.view().name("predefinisaniPregledi"))
		.andExpect(model().attributeExists("terminiDto"));
	}
	
	
	
}
