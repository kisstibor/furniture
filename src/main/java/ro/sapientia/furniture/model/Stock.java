package ro.sapientia.furniture.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "stock")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_stock")
    @SequenceGenerator(name = "pk_stock", sequenceName = "pk_stock")
    @Column(name = "id", nullable = false, updatable = false)
    Long id;

    @Column(name = "product", nullable = false)
    String product;

    @Column(name = "count", nullable = false)
    Integer count;

    @ManyToOne
    @JoinColumn(name = "manufacturer_location_id", insertable = false, updatable = false)
    Long manufacturer_location_id;

}