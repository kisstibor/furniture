package ro.sapientia.furniture.bdt.definition;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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
import ro.sapientia.furniture.model.FurniturePanel;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureMockMvc
@ContextConfiguration
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:eetest.properties")
public class FurniturePanelStepDefinition {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;


    @Given("^that we have the following furniture panels:$")
    public void that_we_have_the_following_furniture_panels(final DataTable furniturePanels) throws Throwable {
        for (final Map<String, String> data : furniturePanels.asMaps(String.class, String.class)) {
            FurniturePanel furniturePanel = new FurniturePanel();
            furniturePanel.setHeight(Integer.parseInt(data.get("height")));
            furniturePanel.setWidth(Integer.parseInt(data.get("width")));
            furniturePanel.setDepth(Integer.parseInt(data.get("depth")));
            entityManager.persist(furniturePanel);
        }
        entityManager.flush();
    }

    @Then("I should get {string} {string} {string} for find all furniture panels position {string}")
    public void iShouldGetForFindAllFurniturePanelsPosition(String width, String height, String depth, String position) throws Exception {
        mvc.perform(get("/furniturePanel"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[" + position + "].width", is(width)))
                .andExpect(jsonPath("$[" + position + "].height", is(height)))
                .andExpect(jsonPath("$[" + position + "].depth", is(depth)));
    }

    @Then("I should get {string} error for id {string}")
    public void iShouldGetErrorForId(String errorMsg, Long id) throws Exception {
        mvc.perform(get("/furniturePanel/" + id))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("error", is(errorMsg)));
    }


    @Then("I should get {string} {string} {string} for find furniture panel by id {string}")
    public void iShouldGetForFindFurniturePanelById(String width, String height, String depth, Long id) throws Exception {
        mvc.perform(get("/furniturePanel/find/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("width", is(width)))
                .andExpect(jsonPath("height", is(height)))
                .andExpect(jsonPath("depth", is(depth)));
    }

    @Then("I should get no error for adding {string} {string} {string} {string}")
    public void iShouldGetNoErrorForAdding(Long id, String width, String height, String depth) throws Exception {
        mvc.perform(post("/furniturePanel/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": " + id + ",\n" +
                        "    \"width\": \"" + width + "\",\n" +
                        "    \"height\": \"" + height + "\",\n" +
                        "    \"depth\": \"" + depth + "\",\n")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("width", is(width)))
                .andExpect(jsonPath("height", is(height)))
                .andExpect(jsonPath("depth", is(depth)));
    }

    @Then("I should get {string} error for updating {string} {string} {string} {string}")
    public void iShouldGetErrorForUpdating(String errorMsg, Long id, String width, String height, String depth) throws Exception {
        mvc.perform(post("/furniturePanel/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": " + id + ",\n" +
                        "    \"width\": " + width + ",\n" +
                        "    \"height\": \"" + height + "\",\n" +
                        "    \"depth\": \"" + depth + "\",\n")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("error", is(errorMsg)));
    }

    @Then("I should get no error for updating {string} {string} {string} {string}")
    public void iShouldGetNoErrorForUpdating(Long id, String width, String height, String depth) throws Exception {
        mvc.perform(post("/furniturePanel/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": " + id + ",\n" +
                        "    \"regionId\": " + width + ",\n" +
                        "    \"country\": \"" + height + "\",\n" +
                        "    \"county\": \"" + depth + "\",\n")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Then("I should get {string} error for deleting element at position {string}")
    public void iShouldGetErrorForDeletingElementAtPosition(String errorMsg, Long id) throws Exception {
        mvc.perform(get("/furniturePanel/delete/" + id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("error", is(errorMsg)));
    }

    @Then("I should get no error for deleting furniture panel by id {string}")
    public void iShouldGetNoErrorForDeletingFurniturePanelById(Long id) throws Exception {
        mvc.perform(get("/furniturePanel/delete/" + id))
                .andExpect(status().isOk());
    }
}