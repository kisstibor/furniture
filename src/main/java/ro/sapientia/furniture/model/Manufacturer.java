package ro.sapientia.furniture.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "manufacturer")
public class Manufacturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_manufacturer")
    @SequenceGenerator(name="pk_manufacturer", sequenceName="pk_manufacturer")
    @Column(name = "id", nullable = false, updatable = false)
    Long id;

    @Column(name = "name", nullable = false)
    String name;
}
