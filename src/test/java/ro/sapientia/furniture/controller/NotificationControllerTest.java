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
import ro.sapientia.furniture.model.NotificationBody;
import ro.sapientia.furniture.repository.NotificationBodyRepository;
import ro.sapientia.furniture.service.NotificationBodyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = NotificationController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class NotificationControllerTest {

    private final List<NotificationBody> notifications = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        NotificationBodyRepository repositoryMock = mock(NotificationBodyRepository.class);
        NotificationBodyService service = new NotificationBodyService(repositoryMock);
        notifications.add(new NotificationBody(
                1L,
                "order",
                "some message",
                true,
                true,
                new java.sql.Timestamp(System.currentTimeMillis()),
                new java.sql.Timestamp(System.currentTimeMillis())
        ));
        notifications.add(new NotificationBody(2L,
                "order",
                "some message",
                true,
                true,
                new java.sql.Timestamp(System.currentTimeMillis()),
                new java.sql.Timestamp(System.currentTimeMillis())
        ));
    }

    @Autowired
    private MockMvc mockMvc;
    @MockBean(NotificationBodyService.class)
    private NotificationBodyService notificationBodyService;
    @Autowired
    private ObjectMapper objectMapper;

    private final List<NotificationBody> notificationListWithOneNotification = new ArrayList<>(List.of(
            new NotificationBody(1L,
                    "order",
                    "some message",
                    true,
                    true,
                    new java.sql.Timestamp(System.currentTimeMillis()),
                    new java.sql.Timestamp(System.currentTimeMillis())
    )));

    @Test
    public void testFindAllNotificationShouldSucceedWithEmptyList() throws Exception {
        when(notificationBodyService.findAllNotificationBodies()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/notification/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    public void testFindAllNotificationShouldSucceedWithOneNotification() throws Exception {
        when(notificationBodyService.findAllNotificationBodies()).thenReturn(notificationListWithOneNotification);

        mockMvc.perform(get("/api/notification/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(notificationListWithOneNotification.size())));
    }

    @Test
    public void testFindAllNotificationShouldSucceedWithTwoNotification() throws Exception {
        when(notificationBodyService.findAllNotificationBodies()).thenReturn(notifications);

        mockMvc.perform(get("/api/notification/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(notifications.size())));
    }

    @Test
    public void testFindNotificationByIdShouldSucceed() throws Exception {
        when(notificationBodyService.findNotificationBodyById
                (anyLong())).thenReturn(notifications.get(0));

        mockMvc.perform(get("/api/notification/find/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateNotificationShouldSucceed() throws Exception {
        when(notificationBodyService.create(any(NotificationBody.class))).thenReturn(notifications.get(0));

        mockMvc.perform(post("/api/notification/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notifications.get(0))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subject", is(notifications.get(0).getSubject())))
                .andExpect(jsonPath("$.message", is(notifications.get(0).getMessage())))
                .andExpect(jsonPath("$.receive_sms", is(notifications.get(0).getReceive_sms())))
                .andExpect(jsonPath("$.receive_email", is(notifications.get(0).getReceive_email())));
    }

    @Test
    public void testUpdateNotificationShouldSucceed() throws Exception {
        when(notificationBodyService.update(any(NotificationBody.class))).thenReturn(notifications.get(0));

        mockMvc.perform(put("/api/notification/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notifications.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject", is(notifications.get(0).getSubject())))
                .andExpect(jsonPath("$.message", is(notifications.get(0).getMessage())))
                .andExpect(jsonPath("$.receive_sms", is(notifications.get(0).getReceive_sms())))
                .andExpect(jsonPath("$.receive_email", is(notifications.get(0).getReceive_email())));
    }

    @Test
    public void testDeleteUserShouldSucceed() throws Exception {
        doNothing().when(notificationBodyService).delete(anyLong());

        mockMvc.perform(delete("/api/notification/delete/{id}", 1L))
                .andExpect(status().isOk());
    }
}