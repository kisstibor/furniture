package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ro.sapientia.furniture.model.SettingsServiceBody;
import ro.sapientia.furniture.service.FurnitureSettingsService;

@WebMvcTest(controllers = SettingsServiceController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class SettingsServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean(FurnitureSettingsService.class)
	private FurnitureSettingsService furnitureSettingsService;
	
	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		final SettingsServiceBody body = new SettingsServiceBody();
		body.setReceive_email_notification(10);
		when(furnitureSettingsService.findAllSettingsServiceBody()).thenReturn(List.of(body));

		this.mockMvc.perform(get("/settings/all")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].receive_email_notification", is(10)));
		
	}
}
