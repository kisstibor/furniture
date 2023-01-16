package ro.sapientia.furniture.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.NotificationBody;
import ro.sapientia.furniture.repository.NotificationBodyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class NotificationBodyService {
    private final NotificationBodyRepository notificationBodyRepository;

    public List<NotificationBody> findAllNotificationBodies() {
        log.info("finding all notification");
        return this.notificationBodyRepository.findAll();
    }

    public NotificationBody findNotificationBodyById(final Long id) {
        return this.notificationBodyRepository.findNotificationBodyById(id);
    }
    public NotificationBody create(NotificationBody notificationBody) {
        return this.notificationBodyRepository.saveAndFlush(notificationBody);
    }
    public NotificationBody update(NotificationBody notificationBody) {
        return this.notificationBodyRepository.saveAndFlush(notificationBody);
    }
    public void delete(Long id) {this.notificationBodyRepository.deleteById(id);}
}