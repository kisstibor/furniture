package ro.sapientia.furniture.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.sapientia.furniture.model.NotificationBody;
import ro.sapientia.furniture.repository.NotificationBodyRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class NotificationConfiguration {
    @Bean // seeding some notification into database
    CommandLineRunner commandLineRunner(NotificationBodyRepository notificationBodyRepository){

        long twoDaysInMilliseconds = TimeUnit.DAYS.toMillis(2);
        long threeDaysInMilliseconds = TimeUnit.DAYS.toMillis(3);

        return args -> {
            var notif1 = new NotificationBody(1L,
                    "order",
                    "some message",
                    true,
                    true,
                    new java.sql.Timestamp(System.currentTimeMillis()-twoDaysInMilliseconds),
                    new java.sql.Timestamp(System.currentTimeMillis())
            );
            var notif2 = new NotificationBody(2L,
                    "order",
                    "some message",
                    true,
                    false,
                    new java.sql.Timestamp(System.currentTimeMillis()-threeDaysInMilliseconds),
                    new java.sql.Timestamp(System.currentTimeMillis())
            );
            notificationBodyRepository.saveAll(List.of(notif1, notif2));
        };
    }
}
