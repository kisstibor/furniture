package ro.sapientia.furniture.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "furniture_panel")
public class FurniturePanel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_furniture_panel")
    @SequenceGenerator(name = "pk_furniture_panel", sequenceName = "pk_furniture_panel")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "width")
    private int width;

    @Column(name = "heigth")
    private int heigth;

    @Column(name = "depth")
    private int depth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return heigth;
    }

    public void setHeight(int heigth) {
        this.heigth = heigth;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getVolume() {
        return this.depth * this.heigth * this.width;
    }

    @Override
    public String toString() {
        return "FurniturePanel{" +
                "id=" + id +
                ", width=" + width +
                ", heigth=" + heigth +
                ", depth=" + depth +
                '}';
    }
}
