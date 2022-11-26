package ro.sapientia.furniture.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Data
@Entity(name = "furniture_item_join")
public class FurnitureItemJoin implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_furniture_item_join")
    @SequenceGenerator(name="pk_furniture_item_join", sequenceName="pk_furniture_item_join")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @JoinColumn(name = "furniture_item")
    @OneToOne
    private FurnitureItem semiFurniture;

    @JoinColumn(name = "furniture_item")
    @OneToOne
    private FurnitureItem furnitureItem;
}
