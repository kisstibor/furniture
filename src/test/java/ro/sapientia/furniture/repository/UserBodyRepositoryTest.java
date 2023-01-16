package ro.sapientia.furniture.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ro.sapientia.furniture.model.UserBody;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class UserBodyRepositoryTest {

    @Autowired
    UserBodyRepository repository;

    // create data
    UserBody user = new UserBody(
            1L,
            "Jason Smith",
            "jason",
            "password",
            "jason@test.com",
            "0755-666-777",
            "Philadelphia");

    // initialization
    @BeforeEach
    public void init() {
        user = repository.saveAndFlush(user);
    }

    // findAll
    @Test
    public void findAllShouldReturnAllUser() {
        var result = repository.findAll();
        Assertions.assertEquals(1, result.size());
    }

    // findOne
    @Test
    public void testFindByIdShouldReturnUserWithTheGivenId() {
        Assertions.assertEquals(user.getId(), repository.findById(user.getId()).get().getId());
    }

    // createOne
    @Test
    public void testCreateShouldSaveNewUser() {
        UserBody user2 = new UserBody(2L,
                "Cara Greene",
                "cara",
                "password",
                "cara@test.com",
                "0766-777-999",
                "New York City");
        repository.saveAndFlush(user2);

        Assertions.assertEquals(1, repository.findAll().size());
    }

    // updateOne
    @Test
    public void testUpdateShouldUpdateUser() {

        user.setUser_name("Cara");
        user = repository.saveAndFlush(user);

        final String newUserName = repository.findById(user.getId()).get().getUser_name();

        Assertions.assertEquals("Cara", newUserName);
    }

    // deleteOne
    @Test
    public void testDeleteShouldDeleteUser() {
        repository.delete(user);
        Assertions.assertEquals(0, repository.findAll().size());
    }

    // clean user
    @AfterEach
    public void clean() {
        repository.deleteAll();
    }
}
