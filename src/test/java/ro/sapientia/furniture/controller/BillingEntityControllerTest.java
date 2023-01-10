package ro.sapientia.furniture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import ro.sapientia.furniture.model.BillingEntity;
import ro.sapientia.furniture.service.BillingEntityService;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = BillingEntityController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class BillingEntityControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean(BillingEntityService.class)
	private BillingEntityService billingEntityService;
	
	@Test
	public void getAllShouldReturnedAllEntityWith() throws Exception {
		final BillingEntity billingEntity = new BillingEntity(1L, 1L, "Jhon Doe", 0);
		when(billingEntityService.findAllBillingEntities()).thenReturn(List.of(billingEntity));
		
		this.mockMvc.perform(get("/billingEntity/all")).andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].id", is(1)));
	}

	@Test
	public void createShouldReturnNewAddedObject() throws Exception {
		final BillingEntity billingEntity = new BillingEntity(1L, 1L, "Jhon Doe", 0);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(billingEntity);
		when(billingEntityService.create(billingEntity)).thenReturn(billingEntity);
		
		this.mockMvc.perform(post("/billingEntity/add",billingEntity).content(strigifyObject ).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	
	@Test
	public void findByIdShouldReturnEntitysWithGivenId() throws Exception {
		final BillingEntity billingEntity = new BillingEntity(1L, 1L, "Jhon Doe", 0);
		when(billingEntityService.findBillingEntityById(1l)).thenReturn(billingEntity);
		
		this.mockMvc.perform(get("/billingEntity/find/1")).andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("id", is(1)));;
	}
	
	@Test
	public void findByIdShouldThrowExceptionWhenEntityNotFound() throws Exception {
		when(billingEntityService.findBillingEntityById(2l)).thenThrow(new RuntimeException());
		
		assertThrows(NestedServletException.class,()->this.mockMvc.perform(get("/billingEntity/find/2")).andExpect(status().isOk()));
	}
	
	@Test
	public void updateShouldUpdatedEntity() throws Exception {
		final BillingEntity billingEntity = new BillingEntity(1L, 1L, "Jhon Doe", 0);
		billingEntity.setDeposit(200.0);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(billingEntity);
		when(billingEntityService.update(billingEntity)).thenReturn(billingEntity);
		
		this.mockMvc.perform(put("/billingEntity/update").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void deleteAllShouldReturnAllEntitysWith() throws Exception {
		when(billingEntityService.delete(1l)).thenReturn(true);
		
		this.mockMvc.perform(delete("/billingEntity/delete/1")).andExpect(status().isOk());
	}	
}
