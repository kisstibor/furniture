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

import ro.sapientia.furniture.model.Customer;
import ro.sapientia.furniture.model.FurnitureBody;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class CustomerEETest {
	@Autowired
    private MockMvc mvc;
 
	 @Autowired
	    private TestEntityManager entityManager;
	 
	private void addOneElement() {
		final Customer customer = new Customer(1L, "Name test", "0123456789", "test@email.com");
	    entityManager.persist(customer);
	    entityManager.flush();
	}
	
	@Test
	void customerAll_oneElement() throws Exception {
	    addOneElement();
	    mvc.perform(get("/customer/all")
	            .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(content()
	            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	        .andExpect(jsonPath("$[0].name", is("Name Test")));
	}
	
	@Test
	void customerUpdate_oneElement() throws Exception {
	    mvc.perform(put("/customer/update")
	            .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1,\n" +
                        "    \"name\": Updated,\n" +
                        "    \"phone\": \"0123456789\",\n" +
                        "    \"email\": \"test@email.com\",\"}")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}


}
