package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ro.sapientia.furniture.model.Order;
import ro.sapientia.furniture.model.OrderStatus;
import ro.sapientia.furniture.service.FurnitureBodyService;
import ro.sapientia.furniture.service.OrderService;

@WebMvcTest(controllers = OrderController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean(OrderService.class)
	private OrderService orderService;
	
	@Test
	public void getAllShouldReturnAllEntitysWith() throws Exception {
		final Order order = new Order(1l,LocalDate.now(),LocalDate.now().plusDays(12),6912.43,OrderStatus.ORDERED);
		when(orderService.findAllOrder()).thenReturn(List.of(order));
		
		this.mockMvc.perform(get("/order/all")).andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].id", is(1)));
	}
	
	@Test
	public void createShouldReturnNewAddedObject() throws Exception {
		final Order order = new Order(1l,LocalDate.now(),LocalDate.now().plusDays(12),6912.43,OrderStatus.ORDERED);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(order);
		when(orderService.create(order)).thenReturn(order);
		
		this.mockMvc.perform(post("/order/add",order).content(strigifyObject ).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	
	@Test
	public void findByIdShouldReturnEntitysWithGivenId() throws Exception {
		final Order order = new Order(1l,LocalDate.now(),LocalDate.now().plusDays(12),6912.43,OrderStatus.ORDERED);
		when(orderService.findOrderById(1l)).thenReturn(order);
		
		this.mockMvc.perform(get("/order/find/1")).andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("id", is(1)));
	}
	
	@Test
	public void findByIdShouldThrowExceptionWhenEntityNotFound() throws Exception {
		final Order order = new Order(1l,LocalDate.now(),LocalDate.now().plusDays(12),6912.43,OrderStatus.ORDERED);
		when(orderService.findOrderById(2l)).thenThrow(new RuntimeException());
		
		assertThrows(NestedServletException.class,()->this.mockMvc.perform(get("/order/find/2")).andExpect(status().isOk()));
	}
	
	@Test
	public void updateShouldUpdatedEntity() throws Exception {
		final Order order = new Order(1l,LocalDate.now(),LocalDate.now().plusDays(12),6912.43,OrderStatus.ORDERED);
		order.setPrice(111.9);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(order);
		when(orderService.update(order)).thenReturn(order);
		
		this.mockMvc.perform(put("/order/update").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void deleteAllShouldReturnAllEntitysWith() throws Exception {
		when(orderService.delete(1l)).thenReturn(true);
		
		this.mockMvc.perform(delete("/order/delete/1")).andExpect(status().isOk());
	}	
}
