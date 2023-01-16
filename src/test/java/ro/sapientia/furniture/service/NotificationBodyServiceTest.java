package ro.sapientia.furniture.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.NotificationBody;
import ro.sapientia.furniture.repository.NotificationBodyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class NotificationBodyServiceTest {

    private NotificationBodyRepository repositoryMock;
    private NotificationBodyService service;

    private final List<NotificationBody> notifications = new ArrayList<>(List.of(
            new NotificationBody(1L,
                    "order",
                    "some message",
                    true,
                    true,
                    new java.sql.Timestamp(System.currentTimeMillis()),
                    new java.sql.Timestamp(System.currentTimeMillis()))
            , new NotificationBody(2L,
                    "order",
                    "some message",
                    true,
                    true,
                    new java.sql.Timestamp(System.currentTimeMillis()),
                    new java.sql.Timestamp(System.currentTimeMillis()))
    ));

    // repo mocking
    @BeforeEach
    public void setUp() {
        repositoryMock = mock(NotificationBodyRepository.class);
        service = new NotificationBodyService(repositoryMock);
    }


    // find all notification - when empty list
    @Test
    public void testFindAllNotificationBodies_emptyList() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<NotificationBody> notificationBodies = service.findAllNotificationBodies();

        assertEquals(0, notificationBodies.size());
    }

    // find all notification - when null
    @Test
    public void testFindAllNotificationBodies_null() {
        when(repositoryMock.findAll()).thenReturn(null);
        final List<NotificationBody> furnitureBodies = service.findAllNotificationBodies();

        Assertions.assertNull(furnitureBodies);
    }

    // find all notification
    @Test
    public void testFindAllNotification_listOfTwo() {
        when(repositoryMock.findAll()).thenReturn(notifications);
        final List<NotificationBody> notifications = service.findAllNotificationBodies();
        assertEquals(2, notifications.size());
    }

    // find one notification
    @Test
    public void testFindNotificationById() {
        // when
        when(repositoryMock.findNotificationBodyById(anyLong())).thenReturn(notifications.get(0));
        NotificationBody notification = service.findNotificationBodyById(notifications.get(0).getId());

        // then
        assertNotNull(notification);
        assertEquals(notifications.get(0), notification);
    }

    // create notification
    @Test
    public void testCreateNotification() {
        when(repositoryMock.saveAndFlush(notifications.get(0)))
                .thenReturn(notifications.get(0));
        service.create(notifications.get(0));

        verify(repositoryMock, times(1)).saveAndFlush(notifications.get(0));
    }

    // update notification
    @Test
    public void testUpdateNotification() {
        // given
        NotificationBody notification = notifications.get(0);
        notification.setSubject("new subject");

        // when
        when(repositoryMock.saveAndFlush(any(NotificationBody.class))).thenReturn(notifications.get(0));
        NotificationBody notification1 = service.update(notification);

        // then
        assertEquals(notification1.getSubject(), notification.getSubject());
    }

    // delete notification
    @Test
    public void testDeleteNotification() {
        // when
        doNothing().when(repositoryMock).deleteById(anyLong());
        service.delete(1L);
        // then
        verify(repositoryMock, times(1)).deleteById(1L);
    }
}
