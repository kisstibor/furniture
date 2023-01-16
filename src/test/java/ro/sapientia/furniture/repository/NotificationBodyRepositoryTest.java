package ro.sapientia.furniture.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ro.sapientia.furniture.model.NotificationBody;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class NotificationBodyRepositoryTest {

    @Autowired
    NotificationBodyRepository repository;

    // create data
    NotificationBody notification = new NotificationBody(
            1L,
            "order",
            "some message",
            true,
            true,
            new java.sql.Timestamp(System.currentTimeMillis()),
            new java.sql.Timestamp(System.currentTimeMillis()));

    // initialization
    @BeforeEach
    public void init() {
        notification = repository.saveAndFlush(notification);
    }

    // findAll notification
    @Test
    public void findAllShouldReturnAllNotification() {
        var result = repository.findAll();
        Assertions.assertEquals(1, result.size());
    }

    // findOne notification
    @Test
    public void testFindByIdShouldReturnNotificationWithTheGivenId() {
        Assertions.assertEquals(notification.getId(), repository.findById(notification.getId()).get().getId());
    }

    // createOne notification
    @Test
    public void testCreateShouldSaveNewNotification() {
        NotificationBody notification2 = new NotificationBody(2L,
                "order",
                "some message",
                true,
                true,
                new java.sql.Timestamp(System.currentTimeMillis()),
                new java.sql.Timestamp(System.currentTimeMillis()));

        repository.saveAndFlush(notification2);

        Assertions.assertEquals(2, repository.findAll().size());
    }

    // updateOne notification
    @Test
    public void testUpdateShouldUpdateNotification() {

        notification.setSubject("other");
        notification = repository.saveAndFlush(notification);

        final String newSubject = repository.findById(notification.getId()).get().getSubject();

        Assertions.assertEquals("other", newSubject);
    }

    // deleteOne notification
    @Test
    public void testDeleteShouldDeleteNotification() {
        repository.delete(notification);
        Assertions.assertEquals(0, repository.findAll().size());
    }

    // clean notification
    @AfterEach
    public void clean() {
        repository.deleteAll();
    }
}
