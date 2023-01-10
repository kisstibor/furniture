package ro.sapientia.furniture.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_employee")
    @SequenceGenerator(name="pk_employee", sequenceName="pk_employee")
    @Column(name = "id", nullable = false, updatable = false)
    Long id;

    @Column(name = "first_name", nullable = false)
    String first_name;

    @Column(name = "last_name", nullable = false)
    String last_name;

    @Column(name = "age", nullable = false)
    Integer age;

    //@Enumerated(EnumType.STRING)
    @Column(name = "employment_type", nullable = false)
    String employment_type;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "manufacturer_location_id", updatable = false, nullable = false)
    ManufacturerLocation manufacturerLocation;
}
