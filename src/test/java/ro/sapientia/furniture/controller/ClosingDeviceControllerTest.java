package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ro.sapientia.furniture.model.ClosingDevice;
import ro.sapientia.furniture.repository.ClosingDeviceRepository;
import ro.sapientia.furniture.service.ClosingDeviceService;

@WebMvcTest(controllers = ClosingDeviceController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ClosingDeviceControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean(ClosingDeviceService.class)
	private ClosingDeviceService closingDeviceService;

	private ClosingDevice clD;

	@Test
	public void testNotFindAll() throws Exception {
		final ClosingDevice clD = new ClosingDevice();

		when(closingDeviceService.findAllClosingDevices()).thenReturn(List.of(clD));

		this.mockMvc.perform(get("/closingDevice/all"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(content().json("[{}]"));
	}

	@Test
	public void testFindAll() throws Exception {
		final ClosingDevice body = new ClosingDevice();
		body.setHeight(10);

		when(closingDeviceService.findAllClosingDevices()).thenReturn(List.of(body));

		this.mockMvc.perform(get("/closingDevice/all")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].height", is(10)));
	}

	@Test
	public void testNotFindById() throws Exception {
		when(closingDeviceService.findClosingDeviceById(anyLong())).thenReturn(null);
		this.mockMvc.perform(get("/closingDevice/find/1"))
			.andExpect(status().isNotFound())
			.andReturn();
	}

	@Test
	public void testFindById() throws Exception {
		final ClosingDevice clD = new ClosingDevice();
		clD.setId(4L);

		when(closingDeviceService.findClosingDeviceById(anyLong())).thenReturn(clD);

		this.mockMvc.perform(get("/closingDevice/find/4"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id", is(4)));
	}

	@Test
	public void testNotDelete() throws Exception {
		this.mockMvc.perform(delete("/closingDevice/delete/11"))
		.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdate() throws Exception {
		//given
		final ObjectMapper objectMapper = new ObjectMapper();
		final ClosingDevice clD= new ClosingDevice();
		clD.setId(1L);
		clD.setHeight(4);

		//when
		when(closingDeviceService.update(any(ClosingDevice.class))).thenReturn(clD);


		//then
		this.mockMvc.perform(post("/closingDevice/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(clD))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	public void testCreate() throws Exception {
		final ObjectMapper obm = new ObjectMapper();
		final ClosingDevice closingDevice = new ClosingDevice();
		final long myId = 1;
		closingDevice.setId(myId);
		closingDevice.setHeight(20);
		closingDevice.setWidth(10);
		closingDevice.setDepth(5);

		//when
		when(closingDeviceService.create(any(ClosingDevice.class))).thenReturn(closingDevice);


		this.mockMvc.perform(post("/closingDevice/add")
				.content(obm.writeValueAsString(closingDevice))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();

		//then
		assertEquals(myId, (long) closingDevice.getId());
	}
}
