package ro.sapientia.furniture;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
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

import ro.sapientia.furniture.model.BillingEntity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

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
public class BillingEntityEETest {
	@Autowired
	private MockMvc mvc;

	 @Autowired
	 private TestEntityManager entityManager;

	 private BillingEntity addOneElement() {
	    	final BillingEntity billingEntity = new BillingEntity();
	    	billingEntity.setCreditCard(1L);
	    	billingEntity.setCustomerName("Test Name");
	        entityManager.persist(billingEntity);
	        entityManager.flush();
	        return billingEntity;
	    }

	    @Test
	    public void customerAll_oneElement() throws Exception {
	        addOneElement();
	        mvc.perform(get("/billingEntity/all")
	                .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content()
	                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	            .andExpect(jsonPath("$[0].customerName", is("Test Name")));
	    }

	@Test
    public void testDeleteCustomerShouldSucceed() throws Exception {
		final BillingEntity billingEntity = new BillingEntity(1L, 1L, "Jhon Doe", 0);
        entityManager.persist(entityManager.merge(billingEntity));
        mvc.perform(delete("/billingEntity/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

	@Test
	public void itShouldGetOneCustomerById() throws Exception {
		BillingEntity billingEntity = addOneElement();

		mvc.perform(get("/billingEntity/find/"+billingEntity.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}  

	@Test
	public void itShouldntGetOneCustomerById() throws Exception {
		assertThrows(NestedServletException.class, () -> mvc.perform(get("/billingEntity/find/-2").contentType(MediaType.APPLICATION_JSON)));
	}  

	@Test
    public void testCreateCustomerShouldSucceed() throws Exception {
		BillingEntity billingEntity = addOneElement();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(billingEntity);

		this.mvc.perform(post("/billingEntity/add").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated()); 
	}

	@Test
    public void testUpdateCustomerShouldSucceed() throws Exception {
		final BillingEntity billingEntity = new BillingEntity(1L, 1L, "Jhon Doe", 0);
		billingEntity.setCustomerName("Updated name");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(billingEntity);

		mvc.perform(put("/billingEntity/update").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()); 

	}
}