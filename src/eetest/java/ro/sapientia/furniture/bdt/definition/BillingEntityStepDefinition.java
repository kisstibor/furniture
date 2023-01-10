package ro.sapientia.furniture.bdt.definition;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.transaction.Transactional;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
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

import io.cucumber.java.en.Then;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ro.sapientia.furniture.model.BillingEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

@Transactional
@SpringBootTest
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ContextConfiguration
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:eetest.properties")
public class BillingEntityStepDefinition {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TestEntityManager entityManager;

	@Given("^that we have the following billing entities:$")
	public void thatWeHaveTheFollowingBillingEntities(final DataTable billing_entity) throws Throwable {
		for (final Map<String, String> data : billing_entity.asMaps(String.class, String.class)) {
			final BillingEntity billingEntity = new BillingEntity();
			billingEntity.setCreditCard(1L);
			billingEntity.setCustomerName(String.valueOf(data.get("customerName")));
			billingEntity.setDeposit(Double.parseDouble(data.get("deposit")));
			entityManager.persist(billingEntity);
		}
		entityManager.flush();
	}

	@Then("^I should get \"([^\"]*)\" items$")
	public void IShouldGetTheCorrectNumberOfItems(final int expectedSize) throws Throwable {
		mvc.perform(get("/billingEntity/all")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(expectedSize)));
	}
	
	@Then("^I should get the deposit \"([^\"]*)\" for the position \"([^\"]*)\"$")
    public void IShouldGetTheCorrectDeposit(final Double expectedResult, final int expectedPosition) throws Throwable {
        mvc.perform(get("/billingEntity/all")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[" + expectedPosition + "].deposit", is(expectedResult.doubleValue())));
    }
	
	@Then("^I should get the customer name \"([^\"]*)\" for the position \"([^\"]*)\"$")
	public void IShouldGetTheCorrectCustomerName(final String expectedResult, final int expectedPosition) throws Throwable {
		mvc.perform(get("/billingEntity/all")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[" + expectedPosition + "].customerName", is(expectedResult)));
	}

	@Then("^I should be able to create billing entity \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void IShouldCreateANewBillingEntity(final Long billingEntityId, final Long creditCard, final String customerName, final Double deposit) throws Throwable {
		final ObjectMapper objectMapper = new ObjectMapper();
		final BillingEntity billingEntity = new BillingEntity(billingEntityId, creditCard, customerName, deposit);

		mvc.perform(post("/billingEntity/create")
						.content(objectMapper.writeValueAsString(billingEntity))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.billingEntity.id", is(billingEntityId.intValue())))
				.andExpect(jsonPath("$.creditCard").exists())
				.andExpect(jsonPath("$.customerName").exists())
				.andExpect(jsonPath("$.deposit").exists());
	}

	@Then("^I should get the response code \"([^\"]*)\" for find by \"([^\"]*)\"$")
	public void IShouldGetTheCorrectResponseCodeForFindById(final int expectedResponseCode, final Long billingEntityId) throws Throwable {
		mvc.perform(get("/billingEntity/get/" + billingEntityId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(expectedResponseCode));
	}

	@Then("^I should get the customer name \"([^\"]*)\" for find by \"([^\"]*)\"$")
	public void IShouldGetTheCorrectCustomerNameForFindById(final String expectedResult, final Long billingEntityId) throws Throwable {
		mvc.perform(get("/billingEntity/get/" + billingEntityId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.customerName", is(expectedResult)));
	}

	@Then("^I should get the deposit \"([^\"]*)\" for find by \"([^\"]*)\"$")
	public void IShouldGetTheCorrectDepositForFindById(final double expectedResult, final Long billingEntityId) throws Throwable {
		mvc.perform(get("/billingEntity/get/" + billingEntityId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.deposit", is(expectedResult)));
	}

	@Then("I should be able to update billing entity \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void IShouldUpdateABillingEntity(final Long billingEntityId, final Long creditCard, final String customerName, final Double deposit) throws Throwable {
		final ObjectMapper objectMapper = new ObjectMapper();
		final BillingEntity billingEntity = new BillingEntity(billingEntityId, creditCard, customerName, deposit);

		mvc.perform(get("/billingEntity/update")
						.content(objectMapper.writeValueAsString(billingEntity))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(billingEntityId.intValue())))
				.andExpect(jsonPath("$.creditCard").exists())
				.andExpect(jsonPath("$.customerName").exists())
				.andExpect(jsonPath("$.deposit").exists());
	}

	@Then("^I should not be able to update billing entity \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void IShouldNotUpdateASale(final Long billingEntityId, final Long creditCard, final String customerName, final Double deposit) throws Throwable {
		final ObjectMapper objectMapper = new ObjectMapper();
		final BillingEntity billingEntity = new BillingEntity(billingEntityId, creditCard, customerName, deposit);

		mvc.perform(post("/billingEntity/update")
						.content(objectMapper.writeValueAsString(billingEntity))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isMethodNotAllowed());
	}

	@Then("^I should be able to delete billing entity by id \"([^\"]*)\"$")
	public void iShouldDeleteBillingEntityById(final Long id) throws Exception {
		mvc.perform(delete("/billingEntity/delete/" + id))
				.andExpect(status().isOk());
	}
}
