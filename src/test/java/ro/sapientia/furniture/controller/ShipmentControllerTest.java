package ro.sapientia.furniture.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ro.sapientia.furniture.model.Shipment;
import ro.sapientia.furniture.service.ShipmentService;

@WebMvcTest(controllers = ShipmentController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ShipmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean(ShipmentService.class)
	private ShipmentService shipmentService;
	
	final Shipment shipment = new Shipment(1L, "Szezam u.", "4A", "Tg.Mures", 569785);

	
	@Test
	public void getAllShouldReturnAllEntitysWith() throws Exception {
		when(shipmentService.findAllShipments()).thenReturn(List.of(shipment));
		
		this.mockMvc.perform(get("/shipment/all")).andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].id", is(1)));
	}
	
	@Test
	public void createShouldReturnNewAddedObject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(shipment);
		when(shipmentService.create(shipment)).thenReturn(shipment);
		
		this.mockMvc.perform(post("/shipment/add",shipment).content(strigifyObject ).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	
	@Test
	public void findByIdShouldReturnEntitysWithGivenId() throws Exception {
		when(shipmentService.findShipmentById(1L)).thenReturn(shipment);
		
		this.mockMvc.perform(get("/shipment/find/1")).andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("id", is(1)));;
	}
	
	@Test
	public void findByIdShouldThrowExceptionWhenEntityNotFound() throws Exception {
		when(shipmentService.findShipmentById(2L)).thenThrow(new RuntimeException());
		
		assertThrows(NestedServletException.class,()->this.mockMvc.perform(get("/shipment/find/2")).andExpect(status().isOk()));
	}
	
	@Test
	public void updateShouldUpdatedEntity() throws Exception {
		shipment.setStreet("Mak u.");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(shipment);
		when(shipmentService.update(shipment)).thenReturn(shipment);
		
		this.mockMvc.perform(put("/shipment/update").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	@Test
	public void deleteAllShouldReturnAllEntitysWith() throws Exception {
		
		when(shipmentService.delete(1L)).thenReturn(true);
		
		this.mockMvc.perform(delete("/shipment/delete/1")).andExpect(status().isOk());
	}	

}
