package ro.sapientia.furniture.model;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_schema")
    @SequenceGenerator(name="pk_schedule", sequenceName = "pk_schedule")
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    @Column(name="product", nullable = false)
    String product;

    @Column(name="start_date", nullable = false)
    Date start_date;

    @Column(name="end_date", nullable = false)
    Date end_date;

}
