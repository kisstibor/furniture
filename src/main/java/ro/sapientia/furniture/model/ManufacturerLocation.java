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
@Entity(name = "manufacturer_location")
public class ManufacturerLocation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_manufacturer_location")
    @SequenceGenerator(name="pk_manufacturer_location",sequenceName="pk_manufacturer_location")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="manufacturer_id", updatable = false, nullable=false)
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "manufacturerLocation")
    private Set<Schedule> schedules;

    @OneToMany(mappedBy = "manufacturerLocation")
    private Set<Stock> stocks;


//    @OneToMany(mappedBy = "employee")
//    private Set<String> employees;

}
