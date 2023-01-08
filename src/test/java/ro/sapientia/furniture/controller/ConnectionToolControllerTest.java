package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ro.sapientia.furniture.model.ConnectionTool;
import ro.sapientia.furniture.service.ConnectionToolService;

@WebMvcTest(controllers = ConnectionToolController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ConnectionToolControllerTest{

	@Autowired
	private MockMvc mockMvc;

	@MockBean(ConnectionToolService.class)
	private ConnectionToolService connectionToolService;

	@Test
	public void testGetAllConnectionTools() throws Exception {
		//given
		final ConnectionTool connectionTool= new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.findAllConnectionTools()).thenReturn(List.of(connectionTool));

		//then
		this.mockMvc.perform(get("/connectiontool/all")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].size", is(3)));
	}

	@Test
	public void testGetAllConnectionToolsIsEmpty() throws Exception {
		//given

		//when
		when(connectionToolService.findAllConnectionTools()).thenReturn(Collections.emptyList());

		//then
		this.mockMvc.perform(get("/connectiontool/all")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(0)));
	}

	@Test
	public void testFindConnectionToolBySize() throws Exception
	{
		//given
		final ConnectionTool connectionTool= new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.findConnectionToolsBySize(connectionTool.getSize())).thenReturn(List.of(connectionTool));

		//then
		this.mockMvc.perform(get("/connectiontool/find/size/" + connectionTool.getSize())).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].size", is(3)));
	}

	@Test
	public void testFindConnectionToolsBySizeIsEmpty() throws Exception {
		//given

		//when
		when(connectionToolService.findConnectionToolsBySize(3)).thenReturn(Collections.emptyList());

		//then
		this.mockMvc.perform(get("/connectiontool/find/size/3")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(0)));
	}

	@Test
	public void testFindConnectionToolByType() throws Exception
	{
		//given
		final ConnectionTool connectionTool= new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.findConnectionToolsByType(connectionTool.getType())).thenReturn(List.of(connectionTool));

		//then
		this.mockMvc.perform(get("/connectiontool/find/type/" + connectionTool.getType())).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].type", is("sin")));
	}

	@Test
	public void testFindConnectionToolsByTypeIsEmpty() throws Exception {
		//given

		//when
		when(connectionToolService.findConnectionToolsByType("sin")).thenReturn(Collections.emptyList());

		//then
		this.mockMvc.perform(get("/connectiontool/find/type/sin")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(0)));
	}

	@Test
	public void testFindConnectionToolById() throws Exception{
		//given
		final ConnectionTool connectionTool= new ConnectionTool();
		connectionTool.setId(1L);
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.findConnectionToolById(connectionTool.getId())).thenReturn(connectionTool);

		//then
		this.mockMvc.perform(get("/connectiontool/find/" + connectionTool.getId()))
					.andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("type", is("sin")));
	}

	@Test
	public void testFindConnectionToolByIdFailed() throws Exception{
		//given
		final ConnectionTool connectionTool= new ConnectionTool();
		connectionTool.setId(100L);
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.findConnectionToolById(any())).thenReturn(null);

		//then
		this.mockMvc.perform(get("/connectiontool/find/100" ))
					.andExpect(status().isNotFound())
					.andReturn();
	}

	@Test
	public void testDeleteConnectionToolById()throws Exception{
		//given
		final ConnectionTool connectionTool= new ConnectionTool();
		connectionTool.setId(1L);
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.findConnectionToolById(connectionTool.getId())).thenReturn(connectionTool);

		//then
		this.mockMvc.perform(get("/connectiontool/delete/" + connectionTool.getId()))
					.andExpect(status().isOk());
	}

	@Test
	public void testDeleteConnectionToolByIdFailed()throws Exception{
		//given

		//when
		when(connectionToolService.findConnectionToolById(100L)).thenReturn(null);

		//then
		this.mockMvc.perform(get("/connectiontool/delete/100"))
					.andExpect(status().isOk());
	}

	@Test
	public void testAddConnectionTool() throws Exception{
		//given
		final ObjectMapper objectMapper = new ObjectMapper();
		final ConnectionTool connectionTool = new ConnectionTool();
		final long ctId = 1;
		connectionTool.setId(ctId);
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.create(any(ConnectionTool.class))).thenReturn(connectionTool);

		//then
		this.mockMvc.perform(post("/connectiontool/add")
				.content(objectMapper.writeValueAsString(connectionTool))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andReturn();
	}

	@Test
	public void testAddConnectionToolFailed() throws Exception{
		//given
		final ObjectMapper objectMapper = new ObjectMapper();
		final ConnectionTool connectionTool = new ConnectionTool();
		final long ctId = 1;
		connectionTool.setId(ctId);
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.create(any())).thenReturn(null);

		//then
		this.mockMvc.perform(post("/connectiontool/add")
				.content(objectMapper.writeValueAsString(connectionTool))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andReturn();
	}


	@Test
	public void testUpdateConnectionTool() throws Exception{
		//given
		final ObjectMapper objectMapper = new ObjectMapper();
		final ConnectionTool connectionTool= new ConnectionTool();
		connectionTool.setId(1L);
		connectionTool.setSize(4);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.update(any(ConnectionTool.class))).thenReturn(connectionTool);


		//then
		this.mockMvc.perform(post("/connectiontool/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(connectionTool))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	public void testUpdateConnectionToolFailed() throws Exception{
		//given
		final ObjectMapper objectMapper = new ObjectMapper();
		final ConnectionTool connectionTool= new ConnectionTool();
		connectionTool.setId(99L);
		connectionTool.setSize(4);
		connectionTool.setType("sin");

		//when
		when(connectionToolService.update(any())).thenReturn(null);


		//then
		this.mockMvc.perform(post("/connectiontool/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(connectionTool))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}



}

