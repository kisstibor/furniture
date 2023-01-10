package ro.sapientia.furniture;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.junit.Assert.assertThrows;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.model.FurnitureBody;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.springframework.http.MediaType;

@Transactional
@SpringBootTest
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:eetest.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerEETest {
	@Autowired
    private MockMvc mvc;
 
	 @Autowired
	    private TestEntityManager entityManager;
	 
	 private Customer addOneElement() {
	    	final Customer customer = new Customer();
	        customer.setName("Test Name");
	        entityManager.persist(customer);
	        entityManager.flush();
	        return customer;
	    }
	    
	    @Test
	    public void customerAll_oneElement() throws Exception {
	        addOneElement();
	        mvc.perform(get("/customer/all")
	                .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content()
	                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	            .andExpect(jsonPath("$[0].name", is("Test Name")));
	    }
	
	@Test
    public void testDeleteCustomerShouldSucceed() throws Exception {
		final Customer customer = new Customer(1l,"Test Name", "0123456789", "test@email.com");
        entityManager.persist(entityManager.merge(customer));
        mvc.perform(delete("/customer/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
	public void itShouldGetOneCustomerById() throws Exception {
		Customer customer = addOneElement();

		mvc.perform(get("/customer/find/"+customer.getId()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}  
	
	@Test
	public void itShouldntGetOneCustomerById() throws Exception {
		assertThrows(NestedServletException.class, () -> mvc.perform(get("/customer/find/-2").contentType(MediaType.APPLICATION_JSON)));
	}  
	
	@Test
    public void testCreateCustomerShouldSucceed() throws Exception {
		Customer customer = addOneElement();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(customer);

		this.mvc.perform(post("/customer/add").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated()); 
	}
	
	@Test
    public void testUpdateCustomerShouldSucceed() throws Exception {
		final Customer customer = new Customer(1l,"Test Name", "0123456789", "test@email.com");
		customer.setName("Updated name");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(customer);

		mvc.perform(put("/customer/update").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()); 
 
	}
}

