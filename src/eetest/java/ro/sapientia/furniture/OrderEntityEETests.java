package ro.sapientia.furniture;

import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThrows;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ro.sapientia.furniture.model.OrderEntity;
import ro.sapientia.furniture.model.OrderStatus;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
public class OrderEntityEETests {
	
	 @Autowired
	 private MockMvc mvc;
	
	@Autowired
    private TestEntityManager entityManager;
	
	private OrderEntity addOneElement() {
		OrderEntity order = new OrderEntity();
		order.setOrderedAt(LocalDate.now());
		order.setOrderDeadline(LocalDate.now().plusDays(12));
		order.setPrice(124.21);
		
		return entityManager.persistAndFlush(order);
	}

	    @Test
	    public void customerAll_oneElement() throws Exception {
	        addOneElement();
	        mvc.perform(get("/order/all")
	                .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content()
	                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	            .andExpect(jsonPath("$[0].id", is(1)));
	    }

    public void testDeleteCustomerShouldSucceed() throws Exception {
		final OrderEntity order = addOneElement();
        entityManager.persist(entityManager.merge(order));
        mvc.perform(delete("/order/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

	public void itShouldGetOneCustomerById() throws Exception {
		final OrderEntity order = addOneElement();

		mvc.perform(get("/order/find/"+order.getId()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}  

	@Test
	public void itShouldntGetOneCustomerById() throws Exception {
		assertThrows(NestedServletException.class, () -> mvc.perform(get("/order/find/-2").contentType(MediaType.APPLICATION_JSON)));
	}  

	@Test
    public void testCreateCustomerShouldSucceed() throws Exception {
		OrderEntity order = addOneElement();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(order);

		this.mvc.perform(post("/order/add").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated()); 
	}

	@Test
    public void testUpdateCustomerShouldSucceed() throws Exception {
		final OrderEntity order = addOneElement();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(order);

		mvc.perform(put("/order/update").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()); 

	}

}
