package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sapientia.furniture.model.NotificationBody;

@Repository

public interface NotificationBodyRepository extends JpaRepository<NotificationBody,Long> {

    NotificationBody findNotificationBodyById(Long id);
}
