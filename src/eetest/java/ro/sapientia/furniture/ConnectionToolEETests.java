package ro.sapientia.furniture;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import ro.sapientia.furniture.model.ConnectionTool;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")

class ConnectionToolEETests{

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TestEntityManager entityManager;

	private void addOneElement(){
		ConnectionTool connectionTool = new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");
		entityManager.persist(connectionTool);
		entityManager.flush();
	}

	@Test
	void testBeEmpty() throws Exception{
		mvc.perform(get("/connectiontool/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(0)));
	}

	@Test
	void testConnectionToolAll_oneElement() throws Exception{
		addOneElement();
		mvc.perform(get("/connectiontool/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].size", is(3)));
	}

	@Test
	void testConnectionToolAll() throws Exception{
		for(int i=0;i<10;++i)
		{
			addOneElement();
		}
		mvc.perform(get("/connectiontool/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(10)));
	}

	@Test
	void testFindConnectionToolsBySize() throws Exception{
		for(int i=0;i<3;++i)
		{
			addOneElement();
		}
		mvc.perform(get("/connectiontool/find/size/" + 3)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(3)));
	}

	@Test
	void testFinConnectionToolsBySizeBeEmpty() throws Exception{
		mvc.perform(get("/connectiontool/find/size/3")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(0)));
	}

	@Test
	void testFindConnectionToolsByType() throws Exception{
		for(int i=0;i<5;++i)
		{
			addOneElement();
		}
		mvc.perform(get("/connectiontool/find/type/sin")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(5)));
	}

	@Test
	void testFinConnectionToolsByTypeBeEmpty() throws Exception{
		mvc.perform(get("/connectiontool/find/type/sin")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(0)));
	}

	@Test
	void testFindConnectionToolById() throws Exception{
		ConnectionTool connectionTool = new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");
		entityManager.persistAndFlush(connectionTool);

		mvc.perform(get("/connectiontool/find/" + connectionTool.getId())
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.type", is(connectionTool.getType())));
	}

	@Test
	void testFindConnectionToolByIdFailed() throws Exception{
		mvc.perform(get("/connectiontool/find/100"))
			      .andExpect(status().isNotFound())
			      .andReturn();
	}

	@Test
	void testAddConnectionTool() throws Exception{
		final ObjectMapper objectMapper = new ObjectMapper();
		ConnectionTool connectionTool = new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");
		entityManager.persist(connectionTool);
		entityManager.flush();

		mvc.perform(post("/connectiontool/add")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(connectionTool))
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("size", is(3)))
			.andExpect(jsonPath("type", is("sin")));
	}

	@Test
	void testAddConnectionToolFailed() throws Exception{
		final ObjectMapper objectMapper = new ObjectMapper();
		ConnectionTool connectionTool = null;

		this.mvc.perform(post("/connectiontool/add")
				.content(objectMapper.writeValueAsString(connectionTool))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andReturn();
	}

	@Test
	void testUpdateConnectionTool() throws Exception{
		final ObjectMapper objectMapper = new ObjectMapper();
		ConnectionTool connectionTool = new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");
		entityManager.persist(connectionTool);
		entityManager.flush();

		mvc.perform(post("/connectiontool/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(connectionTool))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	void testUpdateConnectionToolFailed() throws Exception{
		final ObjectMapper objectMapper = new ObjectMapper();
		ConnectionTool connectionTool = new ConnectionTool();
		connectionTool.setId(100L);
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		mvc.perform(post("/connectiontool/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(connectionTool))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

	@Test
	void testDeleteConnectionToolById() throws Exception{
		ConnectionTool connectionTool = new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");
		entityManager.persist(connectionTool);
		entityManager.flush();

		mvc.perform(get("/connectiontool/delete/"+connectionTool.getId()))
			.andExpect(status().isOk());
	}

	@Test
	void testDeleteConnectionToolByIdFailed() throws Exception{
		mvc.perform(get("/connectiontool/delete/100"))
		.andExpect(status().isNotFound());
	}



}

