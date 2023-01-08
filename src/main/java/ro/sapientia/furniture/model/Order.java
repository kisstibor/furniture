package ro.sapientia.furniture.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_order")
	@SequenceGenerator(name="pk_order",sequenceName="pk_order") 
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(nullable=false)
	private Double price;
	
	private LocalDate orderedAt;
	
	private LocalDate orderDeadline;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	
}
