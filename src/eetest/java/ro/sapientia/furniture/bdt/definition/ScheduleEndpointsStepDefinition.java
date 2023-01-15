package ro.sapientia.furniture.bdt.definition;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.hamcrest.CoreMatchers;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@ContextConfiguration
public class ScheduleEndpointsStepDefinition {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    private void addOneElement() {
        final Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        final Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        final Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        entityManager.persist(entityManager.merge(manufacturer));

        final ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        entityManager.persist(entityManager.merge(manufacturerLocation));

        final Schedule schedule= new Schedule(
                1L,
                "product1",
                start_date,
                end_date,
                manufacturerLocation
        );

        entityManager.persist(entityManager.merge(schedule));
        entityManager.flush();
    }

    @Given("that we have the following schedule:")
    public void that_we_have_the_following_schedule(Long id) {
        addOneElement();

    }
    @Then("I should get the product {string} for the id {long}")
    public void i_should_get_the_product_for_the_position(String product, Long id) throws Exception {
        mvc.perform(get("/schedule/find/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", CoreMatchers.is(id)))
                .andExpect(jsonPath("$.product", CoreMatchers.is("product")));
    }

    @Then("^I should succeed in creating \\\"([^\\\"]*)\\\" \\\"([^\\\"]*)\\\" \\\"([^\\\"]*)\\\" \\\"([^\\\"]*)\\\"$")
    public void i_should_succeed_in_creating(Long id, String product, Date start_date, Date end_date) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();

        final Manufacturer manufacturer = new Manufacturer(
                2L,
                "manufacturer1",
                null
        );

        final ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                2L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        final Schedule schedule= new Schedule(
                id,
                product,
                start_date,
                end_date,
                manufacturerLocation
        );

        mvc.perform(MockMvcRequestBuilders.post("/schedule/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product", CoreMatchers.is(product)))
                .andExpect(jsonPath("$.start_date", CoreMatchers.is(start_date)))
                .andExpect(jsonPath("$.end_date", CoreMatchers.is(end_date)));
    }

    @Then("^I should succeed in updating schedule \\\"([^\\\"]*)\\\" \\\"([^\\\"]*)\\\" \\\"([^\\\"]*)\\\" \\\"([^\\\"]*)\\\"$")
    public void i_should_succeed_in_updating_schedule(String string, String string2, String string3, String string4) throws Exception {
        addOneElement();

        final ObjectMapper objectMapper = new ObjectMapper();

        final Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        final Date end_date = Date.from(Instant.parse("2023-01-05T11:50:55.912Z"));

        final Manufacturer manufacturer = new Manufacturer(
                2L,
                "manufacturer1",
                null
        );

        final ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                2L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        final Schedule schedule= new Schedule(
                1L,
                "updatedProduct",
                start_date,
                end_date,
                manufacturerLocation
        );

        mvc.perform(MockMvcRequestBuilders.put("/schedule/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product", CoreMatchers.is(schedule.getProduct())));
    }

    @Then("^I should get \\\"([^\\\"]*)\\\" error for deleting schedule by id \\\"([^\\\"]*)\\\"$")
    public void i_should_get_error_for_deleting_schedule_by_id(String string, String string2) throws Exception {
        mvc.perform(delete("/schedule/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Then("^I should succeed in deleting schedule by id \\\"([^\\\"]*)\\\"$")
    public void i_should_succeed_in_deleting_schedule_by_id(Long id) throws Exception {
        addOneElement();
        mvc.perform(delete("/schedule/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @After
    public void closeBrowser() {
    }
}
