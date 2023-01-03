package ro.sapientia.furniture.model;

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
    @JoinColumn(name="manufacturer_id", nullable=false)
    private Manufacturer manufacturer;

//    @OneToMany(mappedBy = "schedule")
    private Set<String> schedules;

//    @OneToMany(mappedBy = "stock")
    private Set<String> stocks;

//    @OneToMany(mappedBy = "employee")
    private Set<String> employees;

}
