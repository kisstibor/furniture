package ro.sapientia.furniture.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

// lombok
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
public class NotificationBody {
    @Id
    @SequenceGenerator(
            name = "notifications_sequence",
            sequenceName = "notifications_sequence",
            allocationSize = 1)

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_sequence"
    )


    private Long id;
    //private Long order_id;
    private String subject;
    private String message;
    private Boolean receive_sms;
    private Boolean receive_email;
    private Timestamp created_at;
    private Timestamp updated_at;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NotificationBody notificationBody = (NotificationBody) o;
        return id != null && Objects.equals(id, notificationBody.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
