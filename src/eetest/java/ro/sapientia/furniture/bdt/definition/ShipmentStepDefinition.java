package ro.sapientia.furniture.bdt.definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
import ro.sapientia.furniture.model.Shipment;
import ro.sapientia.furniture.repository.ShipmentRepository;
import ro.sapientia.furniture.service.ShipmentService;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ContextConfiguration
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:eetest.properties")
public class ShipmentStepDefinition {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    Shipment shipment = new Shipment(1L, "Ko", "2", "Tg.Mures", 548963);
    List<Shipment> shipments = new ArrayList<>();
    ShipmentService shipmentService;

    public ShipmentStepDefinition(ShipmentRepository shipmentRepository) {
        this.shipmentService = new ShipmentService(shipmentRepository);
    }

    @Given("I have multiple shipments")
    public void given_multiple_shipments() {
        shipments = shipmentService.findAllShipments();
    }

    @When("I retrieve all shipments")
    public void when_retrieve_all_shipments() {
        // No action is needed, as the shipments are retrieved in the given step
    }

    @Then("I should be able to see all the shipments")
    public void then_verify_all_shipments() {
        assertTrue(shipments.size() > 0);
    }

    @Given("I have a shipment with id {long}")
    public void given_shipment_by_id(long id) {
        shipment = shipmentService.findShipmentById(id);
    }

    @When("I retrieve the shipment by id")
    public void when_retrieve_shipment_by_id() {
        // No action is needed, as the shipment is retrieved in the given step
    }

    @Then("I should be able to see the shipment with id {long}")
    public void then_verify_shipment_by_id(long id) {
        assertEquals(shipment.getId(), id);
    }

    @Given("I have a new shipment with id {long}")
    public void given_new_shipment(long id){
        shipment = new Shipment();
        shipment.setId(2L);
    }

    @When("I create the shipment")
    public void when_create_shipment() {
        shipment = shipmentService.create(shipment);
    }

    @Then("the shipment should be created successfully")
    public void then_verify_shipment_create() {
        assertNotNull(shipment.getId());
    }

    @When("I update the shipment street to {string}")
    public void when_update_shipment(String street) {
        shipment.setStreet(street);
        shipment = shipmentService.update(shipment);
    }

    @Then("the shipment should be updated successfully")
    public void then_verify_shipment_updated() {
        assertEquals(shipment.getStreet(), "Szezam");
    }

    @When("I delete the shipment with id {long}")
    public void when_delete_shipment(long id) {
        shipmentService.delete(id);
    }

    @Then("the shipment should be deleted successfully")
    public void then_verify_shipment_deleted() {
        assertFalse(shipmentService.findAllShipments().contains(shipment));
    }
}