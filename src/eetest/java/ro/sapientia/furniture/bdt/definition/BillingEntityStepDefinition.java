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

import com.ibm.icu.math.BigDecimal;

import io.cucumber.java.en.Then;

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
    public void IShouldGetTheCorrectDeposit(final BigDecimal expectedResult, final int expectedPosition) throws Throwable {
        mvc.perform(get("/billingEntity/all")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[" + expectedPosition + "].totalPrice", is(expectedResult.doubleValue())));
    }
	
	@Then("^I should get the customer name  \"([^\"]*)\" for the position \"([^\"]*)\"$")
	public void IShouldGetTheCorrectCustomerName(final String expectedResult, final int expectedPosition) throws Throwable {
		mvc.perform(get("billingEntity/all")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[" + expectedPosition + "].totalPrice", is(expectedResult.toString())));
	}
}
