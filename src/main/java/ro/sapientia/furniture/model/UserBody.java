package ro.sapientia.furniture.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
public class UserBody {
    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1)

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    //  declared database attributes
    private Long id;
    private String name;
    private String user_name;
    private String password;
    private String email;
    private String phone;
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserBody userBody = (UserBody) o;
        return id != null && Objects.equals(id, userBody.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}