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
import ro.sapientia.furniture.model.UserBody;
import ro.sapientia.furniture.repository.UserBodyRepository;
import ro.sapientia.furniture.service.UserBodyService;

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
public class UserStepDefinition {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    UserBody user = new UserBody(1L,
            "Jason Smith",
            "jason",
            "password",
            "jason@test.com",
            "0755-666-777",
            "Philadelphia");

    List<UserBody> users = new ArrayList<>();
    UserBodyService userBodyService;

    public UserStepDefinition(UserBodyRepository userRepository) {
        this.userBodyService = new UserBodyService(userRepository);
    }

    @Given("I have multiple users")
    public void given_multiple_users() {
        users = userBodyService.findAllUserBodies();
    }

    @When("I retrieve all users")
    public void when_retrieve_all_users() {}

    @Then("I should be able to see all users")
    public void then_verify_all_users() {
        assertTrue(users.size() > 0);
    }

    @Given("I have an user with id {long}")
    public void given_user_by_id(long id) {
        user = userBodyService.findUserBodyById(id);
    }

    @When("I retrieve the user by id")
    public void when_retrieve_user_by_id() {}

    @Then("I should be able to see the user with id {long}")
    public void then_verify_user_by_id(long id) {
        assertEquals(user.getId(), id);
    }

    @Given("I have a new user with id {long}")
    public void given_new_user(long id){
        user = new UserBody();
        user.setId(2L);
    }

    @When("I create the user")
    public void when_create_user() {
        user = userBodyService.create(user);
    }

    @Then("the user should be created successfully")
    public void then_verify_user_create() {
        assertNotNull(user.getId());
    }

    @When("I update the username to {string}")
    public void when_update_user(String user_name) {
        user.setUser_name(user_name);
        user = userBodyService.update(user);
    }

    // update user name
    @Then("the user should be updated successfully")
    public void then_verify_user_updated() {
        assertEquals(user.getUser_name(), "Jason");
    }

    @When("I delete the user with id {long}")
    public void when_delete_user(long id) {
        userBodyService.delete(id);
    }

    @Then("the user should be deleted successfully")
    public void then_verify_user_deleted() {
        assertFalse(userBodyService.findAllUserBodies().contains(user));
    }
}
