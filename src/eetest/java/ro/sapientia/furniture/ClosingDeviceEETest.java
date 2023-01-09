package ro.sapientia.furniture;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import ro.sapientia.furniture.model.ClosingDevice;
import ro.sapientia.furniture.service.ClosingDeviceService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
class ClosingDeviceEETest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClosingDeviceService service;

    @AfterEach
    public void clearManager() {
    	entityManager.clear();
    }

    private void addOneElement() {
    	ClosingDevice body = new ClosingDevice();
    	body.setHeight(10);
    	entityManager.persist(body);
    	entityManager.flush();
    	System.out.print("Created: ");
    	System.out.println(body);
    	//entityManageren keresztul tudunk az adatbazishoz is nyulni?
    }

	@Test
	void closingDeviceAll() throws Exception{
		addOneElement();
		this.mvc.perform(get("/closingDevice/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].height", is(10)));
	}

	@Test
	public void closingDeviceAllNull() throws Exception {
		this.mvc.perform(get("/closingDevice/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(0)));
	}

	@Test
	public void closingDeviceCreate() throws Exception {
		final ObjectMapper obm = new ObjectMapper();

		ClosingDevice closingDevice = new ClosingDevice();
    	closingDevice.setHeight(17);
    	entityManager.persist(closingDevice);
    	entityManager.flush();

		this.mvc.perform(post("/closingDevice/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(obm.writeValueAsString(closingDevice))
				.accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.height", is(17)));
	}

	@Test
	public void closingDeviceOne() throws Exception{
		addOneElement();
		this.mvc.perform(get("/closingDevice/find/2")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.height", is(10)));
	}

	@Test
	public void closingDeviceDelete() throws Exception {
		final var closingDevice = new ClosingDevice();
		closingDevice.setHeight(6);

    	entityManager.persist(closingDevice);
    	entityManager.flush();

    	final var cld = service.create(closingDevice);
    	final var cld2 = service.findClosingDeviceById(cld.getId());

    	this.mvc.perform(delete("/closingDevice/delete/"+cld2.getId()))
		.andExpect(status().isOk());
	}

	@Test
	public void closingDeviceUpdate() throws Exception {
		final var closingDevice = new ClosingDevice();
		closingDevice.setHeight(8);

    	entityManager.persist(closingDevice);
    	entityManager.flush();

    	final var cld = service.create(closingDevice);
    	final var cld2 = service.findClosingDeviceById(cld.getId());

        this.mvc.perform(post("/closingDevice/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": " + cld2.getId() + ", \"height\": \"9\"}")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
	}
}
