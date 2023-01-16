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
import ro.sapientia.furniture.model.NotificationBody;
import ro.sapientia.furniture.service.NotificationBodyService;

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
class NotificationEETests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NotificationBodyService notificationBodyService;

    @BeforeEach
    public void setUp() {
        entityManager.clear();
        entityManager.flush();
    }

    // find all
    @Test
    void testFindAllNotificationsShouldSucceed() throws Exception {
        mvc.perform(get("/api/notification/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(2)));
    }

    // findById
    @Test
    public void testFindNotificationByIdShouldSucceed() throws Exception {

        mvc.perform(get("/api/notification/find/{id}", 1L))
                .andExpect(status().isOk());
    }


    // create one notification
    @Test
    public void testCreateNotificationShouldSucceed() throws Exception {

        var notification = new NotificationBody(2L,
                "order",
                "some message",
                true,
                true,
                new java.sql.Timestamp(System.currentTimeMillis()),
                new java.sql.Timestamp(System.currentTimeMillis())
        );

        mvc.perform(post("/api/notification/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notification)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subject", is(notification.getSubject())))
                .andExpect(jsonPath("$.message", is(notification.getMessage())))
                .andExpect(jsonPath("$.receive_sms", is(notification.getReceive_sms())))
                .andExpect(jsonPath("$.receive_email", is(notification.getReceive_email())));
    }

    // update notification
    @Test
    public void testUpdateNotificationBodyShouldSucceed() throws Exception {

        notificationBodyService = mock(NotificationBodyService.class);

        // Create a test notification body
        NotificationBody notification = new NotificationBody(1L,
                "order",
                "some message",
                true,
                true,
                new java.sql.Timestamp(System.currentTimeMillis()),
                new java.sql.Timestamp(System.currentTimeMillis())
        );

        String testNotificationBodyJson = objectMapper.writeValueAsString(notification);
        when(notificationBodyService.update(notification)).thenReturn(notification);

        mvc.perform(put("/api/notification/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testNotificationBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().json(testNotificationBodyJson));
    }

    // delete notification
    @Test
    public void testDeleteNotificationByIdShouldSucceed() throws Exception {
        mvc.perform(delete("/api/notification/delete/{id}", 1L))
                .andExpect(status().isOk());
    }
}
