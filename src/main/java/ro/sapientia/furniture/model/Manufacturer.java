package ro.sapientia.furniture.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Entity(name = "manufacturer")
public class Manufacturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_manufacturer")
    @SequenceGenerator(name = "pk_manufacturer", sequenceName = "pk_manufacturer")
    @Column(name = "id", nullable = false, updatable = false)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "manufacturer")
    private Set<ManufacturerLocation> locations;
}
