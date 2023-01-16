package ro.sapientia.furniture.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.UserBody;
import ro.sapientia.furniture.repository.UserBodyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserBodyServiceTest {

    private UserBodyRepository repositoryMock;
    private UserBodyService service;

    private final List<UserBody> users = new ArrayList<>(List.of(
            new UserBody(1L,
                    "Jason Smith",
                    "jason",
                    "password",
                    "jason@test.com",
                    "0755-666-777",
                    "Philadelphia")
            , new UserBody(2L,
                    "Cara Greene",
                    "cara",
                    "password",
                    "cara@test.com",
                    "0766-777-999",
                    "New York City")));

    // repo mocking
    @BeforeEach
    public void setUp() {
        repositoryMock = mock(UserBodyRepository.class);
        service = new UserBodyService(repositoryMock);
    }


    // find all user - when empty list
    @Test
    public void testFindAllUserBodies_emptyList() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<UserBody> userBodies = service.findAllUserBodies();

        assertEquals(0, userBodies.size());
    }

    // find all user - when null
    @Test
    public void testFindAllUserBodies_null() {
        when(repositoryMock.findAll()).thenReturn(null);
        final List<UserBody> furnitureBodies = service.findAllUserBodies();

        Assertions.assertNull(furnitureBodies);
    }

    // find all user
    @Test
    public void testFindAllUser_listOfTwo() {
        when(repositoryMock.findAll()).thenReturn(users);
        final List<UserBody> users = service.findAllUserBodies();
        assertEquals(2, users.size());
    }

    // find one user
    @Test
    public void testFindUserById() {
        // when
        when(repositoryMock.findUserBodyById(anyLong())).thenReturn(users.get(0));
        UserBody user = service.findUserBodyById(users.get(0).getId());

        // then
        assertNotNull(user);
        assertEquals(users.get(0), user);
    }

    // create user
    @Test
    public void testCreateUser() {
        when(repositoryMock.saveAndFlush(users.get(0)))
                .thenReturn(users.get(0));
        service.create(users.get(0));

        verify(repositoryMock, times(1)).saveAndFlush(users.get(0));
    }

    // update user
    @Test
    public void testUpdateUser() {
        // given
        UserBody user = users.get(0);
        user.setUser_name("Jason");

        // when
        when(repositoryMock.saveAndFlush(any(UserBody.class))).thenReturn(users.get(0));
        UserBody user1 = service.update(user);

        // then
        assertEquals(user1.getUser_name(), user.getUser_name());
    }

    // delete user
    @Test
    public void testDeleteUser() {
        // when
        doNothing().when(repositoryMock).deleteById(anyLong());
        service.delete(1L);
        // then
        verify(repositoryMock, times(1)).deleteById(1L);
    }
}
