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

import ro.sapientia.furniture.model.Shipment;

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
public class ShipmentEETests {
	
	@Autowired
    private MockMvc mvc;
 
	 @Autowired
	    private TestEntityManager entityManager;
	 
	 private Shipment addOneElement() {
	    	Shipment shipment = new Shipment();
	        shipment.setStreet("Alom u.");
	        shipment.setNr("45");
	        shipment.setCity("Tg.Mures");
	        shipment.setPostCode(235648);
	        entityManager.persist(shipment);
	        entityManager.flush();
	        return shipment;
	    }
	    
	    @Test
	    public void findAll_oneElement() throws Exception {
	        addOneElement();
	        mvc.perform(get("/shipment/all")
	                .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content()
	                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	            .andExpect(jsonPath("$[0].street", is("Alom u.")));
	    }
	
	@Test
    public void testDeleteShipmentShouldSucceed() throws Exception {
		Shipment shipment = addOneElement();
        entityManager.persist(entityManager.merge(shipment));
        mvc.perform(delete("/shipment/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
	public void itShouldGetOneshipmentById() throws Exception {
		Shipment shipment = addOneElement();
		mvc.perform(get("/shipment/find/" + shipment.getId()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}  
	
	@Test
	public void itShouldntGetOneshipmentById() throws Exception {
		assertThrows(NestedServletException.class, () -> mvc.perform(get("/shipment/find/-2").contentType(MediaType.APPLICATION_JSON)));
	}  
	
	@Test
    public void testCreateshipmentShouldSucceed() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(addOneElement());

		this.mvc.perform(post("/shipment/add").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated()); 
	}
	
	@Test
    public void testUpdateshipmentShouldSucceed() throws Exception {
		Shipment shipment = addOneElement();
		shipment.setStreet("Tel u.");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		final String strigifyObject = mapper.writeValueAsString(shipment);

		mvc.perform(put("/shipment/update").content(strigifyObject).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()); 
 
	}

}
