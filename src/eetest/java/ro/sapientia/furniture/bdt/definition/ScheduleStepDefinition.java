package ro.sapientia.furniture.bdt.definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.repository.ScheduleRepository;
import ro.sapientia.furniture.service.ScheduleService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@ContextConfiguration
public class ScheduleStepDefinition {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    Schedule schedule;
    List<Schedule> schedules = new ArrayList<>();
    ScheduleService scheduleService;

    public ScheduleStepDefinition(ScheduleRepository scheduleRepository) {
        this.scheduleService = new ScheduleService(scheduleRepository);
        createSchedule();
    }

    public void createSchedule() {
        final Date start_date = Date.from(Instant.parse("2022-05-05T11:50:55.912Z"));
        final Date end_date = Date.from(Instant.parse("2022-12-05T11:50:55.912Z"));

        final Manufacturer manufacturer = new Manufacturer(
                1L,
                "manufacturer1",
                null
        );

        final ManufacturerLocation manufacturerLocation = new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturer,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        schedule= new Schedule(
                100L,
                "product1",
                start_date,
                end_date,
                manufacturerLocation
        );
    }

    @Given("that I have a new schedule with id {long}")
    public void that_I_have_a_new_schedule_with_id(Long id) {
        schedule = new Schedule();
        schedule.setId(id);
    }

    @When("I create the schedule")
    public void I_create_the_schedule() {
        schedule = scheduleService.create(schedule);
    }

    @Then("the schedule should be created successfully")
    public void the_schedule_should_be_created_successfully() {
        Assertions.assertNotNull(schedule.getId());
    }

    @Given("that I have multiple schedules")
    public void that_i_have_multiple_schedules() {
        schedules = scheduleService.findAllSchedules();
    }
    @When("I retrieve all schedules")
    public void i_retrieve_all_schedules() {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("I should be able to see all schedules")
    public void i_should_be_able_to_see_all_schedules() {
        Assertions.assertTrue(schedules.size() > 0);
    }

    @Given("that I have a schedule with id {long}")
    public void that_i_have_a_schedule_with_id(Long id) throws NotFoundException {
        schedule = scheduleService.findScheduleById(id);
    }
    @When("I retrieve the schedule by id")
    public void i_retrieve_the_schedule_by_id() {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("I should be able to see the schedule with id {long}")
    public void i_should_be_able_to_see_the_schedule_with_id(Long id) {
        Assertions.assertEquals(schedule.getId(), id);
    }

    @When("I update the schedule product to {string}")
    public void i_update_the_schedule_product_to(String product) {
        schedule.setProduct(product);
        schedule = scheduleService.update(schedule);
    }
    @Then("the schedule should be updated successfully")
    public void the_schedule_should_be_updated_successfully() {
        Assertions.assertEquals(schedule.getProduct(), "updated product");
    }

    @When("I delete the schedule with id {long}")
    public void i_delete_the_schedule_with_id(Long id) throws NotFoundException {
        scheduleService.delete(id);
    }
    @Then("the schedule should be deleted successfully")
    public void the_schedule_should_be_deleted_successfully() {
        Assertions.assertFalse(scheduleService.findAllSchedules().contains(schedule));
    }

}
