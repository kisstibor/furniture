package ro.sapientia.furniture.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "joinery")
public class Joinery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_joinery")
    @SequenceGenerator(name="pk_joinery",sequenceName="pk_joinery")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "type")
    private int type;

    @Column(name = "price")
    private double price;
}