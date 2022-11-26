package ro.sapientia.furniture.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "material")
public class Material implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_material")
    @SequenceGenerator(name="pk_material",sequenceName="pk_material")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private double price;
}