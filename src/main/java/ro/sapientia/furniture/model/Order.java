package ro.sapientia.furniture.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Entity(name="order")
@Data
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private Double price;
	
	private LocalDate orderedAt;
	
	private LocalDate orderDeadline;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
}
