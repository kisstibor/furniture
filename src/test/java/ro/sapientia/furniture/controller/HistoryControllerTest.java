package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ro.sapientia.furniture.model.HistoryBody;
import ro.sapientia.furniture.service.HistoryService;

@WebMvcTest(controllers = HistoryController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class HistoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean(HistoryService.class)
	private HistoryService historyService;

	@Test
	public void checkHistoryName() throws Exception {
		final HistoryBody history = new HistoryBody();
		history.setName("test name");
		when(historyService.findAllHistoryBodies()).thenReturn(List.of(history));

		this.mockMvc.perform(get("/history/all")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is("test name")));
	}

	@Test
	public void checkHistoryOrderId() throws Exception {
		final HistoryBody history = new HistoryBody();
		history.setOrderId(10L);
		when(historyService.findAllHistoryBodies()).thenReturn(List.of(history));

		this.mockMvc.perform(get("/history/all")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].orderId", is(10)));
	}
	
	@Test
	public void checkHistoryUserId() throws Exception {
		final HistoryBody history = new HistoryBody();
		history.setUserId(20L);
		when(historyService.findAllHistoryBodies()).thenReturn(List.of(history));

		this.mockMvc.perform(get("/history/all")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].userId", is(20)));
	}
	
	@Test
    public void getAllHistoryTest() throws Exception {
        List<HistoryBody> historyList = Arrays.asList(
                new HistoryBody(1L, "History1"),
                new HistoryBody(2L, "History2"));
        
        when(historyService.findAllHistoryBodies()).thenReturn(historyList);

        mockMvc.perform(get("/history/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].name", is("History1")))
                .andExpect(jsonPath("$.[1].name", is("History2")));
    }
}