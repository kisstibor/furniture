package ro.sapientia.furniture.model.dto;

import javax.persistence.EnumType;

public class OrderDto {
	
	private Long id;
	
	private Double price;
	
	private LocalDate orderedAt;
	
	private LocalDate orderDeadline;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
}
