package ro.sapientia.furniture.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.repository.CustomerRepository;
import ro.sapientia.furniture.service.CustomerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = CustomerController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean(CustomerService.class)
	private CustomerService customerService;
	
	@Test  
	public void getAllShouldReturnAllEntitysWith() throws Exception{
		final Customer customer = new Customer(1l,"Test Name", "0123456789", "test@email.com");
		when(customerService.findAllCustomers()).thenReturn(List.of(customer));

		this.mockMvc.perform(get("/customer/all")).andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].id", is(1)));
	}
	 
	@Test
	public void createShouldReturnNewAddedObject() throws Exception {
		final Customer customer = new Customer(1l,"Test Name", "0123456789", "test@email.com");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(customer);
		when(customerService.create(customer)).thenReturn(customer);

		this.mockMvc.perform(post("/customer/add",customer).content(strigifyObject ).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
  
	@Test
	public void findByIdShouldReturnEntitysWithGivenId() throws Exception {
		final Customer customer = new Customer(1l,"Test Name", "0123456789", "test@email.com");
		when(customerService.findCustomerById(1l)).thenReturn(customer);

		this.mockMvc.perform(get("/customer/find/1")).andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("id", is(1)));;
	}

	@Test
	public void findByIdShouldThrowExceptionWhenEntityNotFound() throws Exception {
		when(customerService.findCustomerById(2l)).thenThrow(new RuntimeException());

		assertThrows(NestedServletException.class,()->this.mockMvc.perform(get("/customer/find/2")).andExpect(status().isOk()));
	}

	@Test
	public void updateShouldUpdatedEntity() throws Exception {
		final Customer customer = new Customer(1l,"Test Name", "0123456789", "test@email.com");
		customer.setName("Updated name");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(customer);
		when(customerService.update(customer)).thenReturn(customer);

		this.mockMvc.perform(put("/customer/update").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()); 
 
	}

	@Test
	public void deleteAllShouldReturnAllEntitysWith() throws Exception {
		when(customerService.delete(1l)).thenReturn(true);

		this.mockMvc.perform(delete("/customer/delete/1")).andExpect(status().isOk());
	}	
}
