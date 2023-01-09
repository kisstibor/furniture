package ro.sapientia.furniture.bdt.definition;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ro.sapientia.furniture.model.ConnectionTool;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@ContextConfiguration
public class ConnectionToolStepDefinition {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TestEntityManager entityManager;

	@Given("^that we have the following connection tools:$")
	public void that_we_have_the_following_connection_tools(final DataTable connectionTools) throws Throwable {
		for (final Map<String, String> data : connectionTools.asMaps(String.class, String.class)) {
			ConnectionTool connectionTool = new ConnectionTool();
			connectionTool.setSize(Integer.parseInt(data.get("size")));
			connectionTool.setType(data.get("type"));
			entityManager.persist(connectionTool);
		}
		entityManager.flush();

	}
	@When("^I invoke the connectiontool all endpoint$")
	public void I_invoke_the_furniture_all_endpoint() throws Throwable {
	}

	@Then("^I should get the size \"([^\"]*)\" for the number of elements$")
	public void I_should_get_result_in_stories_list(String expectedResult) throws Throwable {
		mvc.perform(get("/connectiontool/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(3)));
	}

	@When("^I invoke the connectiontool find size endpoint$")
	public void I_invoke_the_connectiontool_find_size_endpoint() throws Throwable {
	}

	@Then("^I should get the size \"([^\"]*)\" for the number of the same size$")
	public void I_should_get_all_connectiontool_by_size(String expectedResult) throws Throwable {
		mvc.perform(get("/connectiontool/find/size/3")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(2)));
	}

	@When("^I invoke the connectiontool find type endpoint$")
	public void I_invoke_the_connectiontool_find_type_endpoint() throws Throwable {
	}

	@Then("^I should get the size \"([^\"]*)\" for the number of the same type$")
	public void I_should_get_all_connectiontool_by_type(String expectedResult) throws Throwable {
		mvc.perform(get("/connectiontool/find/type/szeg")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.length()", is(1)));
	}

	@When("^I invoke the connectiontool find id endpoint$")
	public void I_invoke_the_connectiontool_find_id_endpoint() throws Throwable {
	}

	@Then("^I should get the type \"([^\"]*)\" for the id \"([^\"]*)\"$")
	public void I_should_get_connectiontool_by_id(String expectedResult, final long id) throws Throwable {
		mvc.perform(get("/connectiontool/find/"+id)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("type", is(expectedResult)));
	}

	@When("^I invoke the connectiontool add endpoint$")
	public void I_invoke_the_connectiontool_add_endpoint() throws Throwable {
	}

	@Then("^I should succeed in creating \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void I_should_succeed_in_connectiontool_creating(Long id, String type, int size) throws Throwable {
		mvc.perform(post("/connectiontool/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\": " + id + ",\n"+
				" \"size\": \"" + size + "\",\n"+
				"  \"type\": \"" + type + "\",\n\"}")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("size", is(size)))
		.andExpect(jsonPath("type", is(type)));
	}

	@When("^I invoke the connectiontool update endpoint$")
	public void I_invoke_the_connectiontool_update_endpoint() throws Throwable {
	}

	@Then("^I should succeed in updating \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void I_should_succeed_in_connectiontool_updating(Long id, int size, String type) throws Throwable {
		mvc.perform(post("/connectiontool/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\": " + id + ",\n"+
						" \"size\": \"" + size + "\",\n"+
						"  \"type\": \"" + type + "\",\n\"}")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@When("^I invoke the connectiontool delete id endpoint$")
	public void I_invoke_the_connectiontool_delete_id_endpoint() throws Throwable {
	}

	@Then("^I should succeed in deleting the elements with id \"([^\"]*)\"$")
	public void I_should_succeed_in_connectiontool_delete_by_id(Long id) throws Throwable {
		mvc.perform(get("/connectiontool/delete/" + id))
			      .andExpect(status().isOk());
	}


	@After
	public void closeBrowser() {
	}
}