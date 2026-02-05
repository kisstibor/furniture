package ro.sapientia.furniture.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ro.diyfurniture.furniture.logging.controller.UiLogController;
import ro.diyfurniture.furniture.logging.model.transmission.UiLogBatchRequest;
import ro.diyfurniture.furniture.logging.model.transmission.UiLogEntryRequest;
import ro.diyfurniture.furniture.logging.model.transmission.UiLogEntryResponse;
import ro.diyfurniture.furniture.logging.model.transmission.UiLogQueryResponse;
import ro.diyfurniture.furniture.logging.service.UiLogService;
import ro.diyfurniture.furniture.FurnitureApplication;

@WebMvcTest(controllers = UiLogController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
@ContextConfiguration(classes = FurnitureApplication.class)
public class UiLogControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UiLogService uiLogService;

	@Test
	public void collectShouldAcceptBatch() throws Exception {
		UiLogBatchRequest request = new UiLogBatchRequest();
		UiLogEntryRequest entry = new UiLogEntryRequest();
		entry.setMessage("test");
		entry.setLevel("info");
		request.setLogs(List.of(entry));

		when(uiLogService.collect(any(UiLogBatchRequest.class))).thenReturn(1);

		mockMvc.perform(
			post("/api/ui-logs")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
		)
			.andExpect(status().isAccepted())
			.andExpect(jsonPath("$.accepted").value(1));
	}

	@Test
	public void searchShouldReturnItems() throws Exception {
		UiLogEntryResponse item = new UiLogEntryResponse();
		item.setId(1L);
		item.setLevel("error");
		item.setMessage("boom");
		item.setReceivedAt(Instant.parse("2026-01-01T10:00:00Z"));

		UiLogQueryResponse response = new UiLogQueryResponse();
		response.setTotal(1);
		response.setCount(1);
		response.setItems(List.of(item));

		when(uiLogService.search(eq("error"), eq(null), eq(null), eq(null), eq(null), anyInt()))
			.thenReturn(response);

		mockMvc.perform(get("/api/ui-logs").param("level", "error").param("limit", "100"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.total").value(1))
			.andExpect(jsonPath("$.items[0].level").value("error"))
			.andExpect(jsonPath("$.items[0].message").value("boom"));
	}
}
