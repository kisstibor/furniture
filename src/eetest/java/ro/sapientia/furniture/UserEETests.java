package ro.sapientia.furniture;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ro.sapientia.furniture.model.UserBody;
import ro.sapientia.furniture.service.UserBodyService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserEETests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserBodyService userBodyService;

    @BeforeEach
    public void setUp() {
        entityManager.clear();
        entityManager.flush();
    }

    // find all
    @Test
    void testFindAllUsersShouldSucceed() throws Exception {
        mvc.perform(get("/api/user/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(2)));
    }

    // findById
    @Test
    public void testFindUserByIdShouldSucceed() throws Exception {

        mvc.perform(get("/api/user/find/{id}", 1L))
                .andExpect(status().isOk());
    }


    // create one user
    @Test
    public void testCreateUserShouldSucceed() throws Exception {

        var user = new UserBody(2L,
                "Cara Greene",
                "cara",
                "password",
                "cara@test.com",
                "0766-777-999",
                "New York City"
        );

        mvc.perform(post("/api/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.user_name", is(user.getUser_name())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.phone", is(user.getPhone())))
                .andExpect(jsonPath("$.address", is(user.getAddress())));
    }

    // update user
    @Test
    public void testUpdateUserBodyShouldSucceed() throws Exception {

        userBodyService = mock(UserBodyService.class);

        // Create a test user body
        UserBody user = new UserBody(1L,
                "Jason Smith",
                "jason",
                "password",
                "jason@test.com",
                "0755-666-777",
                "Philadelphia"
        );

        String testUserBodyJson = objectMapper.writeValueAsString(user);
        when(userBodyService.update(user)).thenReturn(user);

        mvc.perform(put("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUserBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().json(testUserBodyJson));
    }

    // delete user
    @Test
    public void testDeleteUserByIdShouldSucceed() throws Exception {
        mvc.perform(delete("/api/user/delete/{id}", 1L))
                .andExpect(status().isOk());
    }
}
