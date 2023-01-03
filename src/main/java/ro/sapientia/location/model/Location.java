package ro.sapientia.location.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "location")
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_location")
    @SequenceGenerator(name="pk_location",sequenceName="pk_location")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "capacityId")
    private String capacityId;
}
