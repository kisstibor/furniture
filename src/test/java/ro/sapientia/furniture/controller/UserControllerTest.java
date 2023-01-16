package ro.sapientia.furniture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.sapientia.furniture.model.UserBody;
import ro.sapientia.furniture.repository.UserBodyRepository;
import ro.sapientia.furniture.service.UserBodyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class UserControllerTest {

    private final List<UserBody> users = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        UserBodyRepository repositoryMock = mock(UserBodyRepository.class);
        UserBodyService service = new UserBodyService(repositoryMock);
        users.add(new UserBody(
                1L,
                "Jason Smith",
                "jason",
                "password",
                "jason@test.com",
                "0755-666-777",
                "Philadelphia"
        ));
        users.add(new UserBody(2L,
                "Cara Greene",
                "cara",
                "password",
                "cara@test.com",
                "0766-777-999",
                "New York City"
        ));
    }

    @Autowired
    private MockMvc mockMvc;
    @MockBean(UserBodyService.class)
    private UserBodyService userBodyService;
    @Autowired
    private ObjectMapper objectMapper;

    private final List<UserBody> userListWithOneUser = new ArrayList<>(List.of(
            new UserBody(1L,
                    "Jason Smith",
                    "jason",
                    "password",
                    "jason@test.com",
                    "0755-666-777",
                    "Philadelphia")
    ));

    @Test
    public void testFindAllUserShouldSucceedWithEmptyList() throws Exception {
        when(userBodyService.findAllUserBodies()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/user/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    public void testFindAllUserShouldSucceedWithOneUser() throws Exception {
        when(userBodyService.findAllUserBodies()).thenReturn(userListWithOneUser);

        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userListWithOneUser.size())));
    }

    @Test
    public void testFindAllUserShouldSucceedWithTwoUser() throws Exception {
        when(userBodyService.findAllUserBodies()).thenReturn(users);

        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(users.size())));
    }

    @Test
    public void testFindUserByIdShouldSucceed() throws Exception {
        when(userBodyService.findUserBodyById
                (anyLong())).thenReturn(users.get(0));

        mockMvc.perform(get("/api/user/find/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUserShouldSucceed() throws Exception {
        when(userBodyService.create(any(UserBody.class))).thenReturn(users.get(0));

        mockMvc.perform(post("/api/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(users.get(0))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(users.get(0).getName())))
                .andExpect(jsonPath("$.user_name", is(users.get(0).getUser_name())))
                .andExpect(jsonPath("$.password", is(users.get(0).getPassword())))
                .andExpect(jsonPath("$.email", is(users.get(0).getEmail())))
                .andExpect(jsonPath("$.phone", is(users.get(0).getPhone())))
                .andExpect(jsonPath("$.address", is(users.get(0).getAddress())));
    }

    @Test
    public void testUpdateUserShouldSucceed() throws Exception {
        when(userBodyService.update(any(UserBody.class))).thenReturn(users.get(0));

        mockMvc.perform(put("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(users.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(users.get(0).getName())))
                .andExpect(jsonPath("$.user_name", is(users.get(0).getUser_name())))
                .andExpect(jsonPath("$.password", is(users.get(0).getPassword())))
                .andExpect(jsonPath("$.email", is(users.get(0).getEmail())))
                .andExpect(jsonPath("$.phone", is(users.get(0).getPhone())))
                .andExpect(jsonPath("$.address", is(users.get(0).getAddress())));
    }

    @Test
    public void testDeleteUserShouldSucceed() throws Exception {
        doNothing().when(userBodyService).delete(anyLong());

        mockMvc.perform(delete("/api/user/delete/{id}", 1L))
                .andExpect(status().isOk());
    }
}