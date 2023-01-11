package ro.sapientia.furniture.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "schedule")
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_schedule")
    @SequenceGenerator(name="pk_schedule", sequenceName = "pk_schedule")
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    @Column(name="product", nullable = false)
    String product;

    @Column(name="start_date", nullable = false)
    Date start_date;

    @Column(name="end_date", nullable = false)
    Date end_date;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "manufacturer_location_id", updatable = false, nullable = false)
    ManufacturerLocation manufacturerLocation;

}
