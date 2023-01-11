package ro.sapientia.furniture.bdt.definition;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import ro.sapientia.furniture.model.HistoryBody;
@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@ContextConfiguration
public class HistoryStepDefinition {

	private List<HistoryBody> historyEntries;

    @Given("^that we have the following history entries:$")
    public void that_we_have_the_following_history_entries(List<HistoryBody> historyEntries) throws Throwable {
        this.historyEntries = historyEntries;
    }

    @When("^I invoke the history all endpoint$")
    public void i_invoke_the_history_all_endpoint() throws Throwable {
        // Call the history all endpoint to retrieve the history entries
    }

    @Then("^I should get the name \"([^\"]*)\" for the position \"([^\"]*)\"$")
    public void i_should_get_the_name_for_the_position(String name, int position) throws Throwable {
        assertEquals(name, historyEntries.get(position).getName());
    }

	@After
	public void closeBrowser() {
	}

}
