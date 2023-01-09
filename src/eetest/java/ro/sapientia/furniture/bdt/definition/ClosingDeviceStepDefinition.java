package ro.sapientia.furniture.bdt.definition;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import ro.sapientia.furniture.model.ClosingDevice;
import ro.sapientia.furniture.service.ClosingDeviceService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@ContextConfiguration
public class ClosingDeviceStepDefinition {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
    private ClosingDeviceService service;

	@Given("^that we have the following closing device:$")
	public void that_we_have_the_following_closingDevice(final DataTable closingDevice) throws Throwable {
		for (final Map<String, String> data : closingDevice.asMaps(String.class, String.class)) {
			ClosingDevice body = new ClosingDevice();
			body.setHeight(Integer.parseInt(data.get("height")));
			body.setWidth(Integer.parseInt(data.get("width")));
			body.setDepth(Integer.parseInt(data.get("depth")));
			entityManager.persist(body);
		}
		entityManager.flush();

	}

	@When("^I invoke the closing device all endpoint$")
	public void I_invoke_the_closingDevice_all_endpoint() throws Throwable {
	}

	@Then("^I should get the height \"([^\"]*)\" for the position \\\"([^\\\"]*)\\\"$")
	public void I_should_get_result_in_closingDevice_list(int height, String expectedResult) throws Throwable {
		mvc.perform(get("/closingDevice/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].height", is(12)));
	}

	@When("^I invoke the closing device find by id endpoint$")
	public void I_invoke_the_closingDevice_find_by_id_endpoint() throws Throwable {
	}

	@Then("^I should get the height \"([^\"]*)\"$")
	public void I_should_get_result_in_closingDevice(int height) throws Throwable {
		mvc.perform(get("/closingDevice/find/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.height", is(12)));
	}

	@When("^I invoke the closing device delete endpoint$")
	public void I_invoke_the_closingDevice_delete_endpoint() throws Throwable {
	}

	@Then("^Deletion completed successfully$")
	public void I_should_get_result_of_deletion() throws Throwable {
		mvc.perform(delete("/closingDevice/delete/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}

	@When("^I create a closing device$")
	public void I_invoke_the_closingDevice_create_endpoint() throws Throwable {
	}

	@Then("^The height will be \\\"([^\\\"]*)\\\"$")
	public void I_should_get_result_of_creation(int height) throws Throwable {
		final ObjectMapper obm = new ObjectMapper();

		ClosingDevice body = new ClosingDevice();
		body.setHeight(15);
		body.setWidth(5);
		body.setDepth(3);

		this.mvc.perform(post("/closingDevice/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(obm.writeValueAsString(body))
				.accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.height", is(15)));
	}

	@When("I update that closing device$")
	public void I_invoke_the_closingDevice_update_endpoint() throws Throwable {
	}

	@Then("^This height will be \\\"([^\\\"]*)\\\"$")
	public void I_should_get_result_of_the_update(int height) throws Throwable {
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

	@After
	public void closeBrowser() {
	}

}
