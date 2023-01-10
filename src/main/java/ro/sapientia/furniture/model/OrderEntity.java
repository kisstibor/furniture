package ro.sapientia.furniture.model;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="order_entity")
public class OrderEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_order")
	@SequenceGenerator(name="pk_order",sequenceName="pk_order") 
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(nullable=false)
	private Double price;
	
	@Column(nullable=false)
	private LocalDate orderedAt;
	
	@Column(nullable=false)
	private LocalDate orderDeadline;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
  	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
  	private Shipment shipment;
  	
  	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
  	private Customer customer;
  	
  	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
  	private BillingEntity billingEntity;
	
	public OrderEntity() {}

	public OrderEntity(Long id,  LocalDate orderedAt, LocalDate orderDeadline, double price,OrderStatus orderStatus) {
		super();
		this.id = id;
		this.price = price;
		this.orderedAt = orderedAt;
		this.orderDeadline = orderDeadline;
		this.orderStatus = orderStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDate getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(LocalDate orderedAt) {
		this.orderedAt = orderedAt;
	}

	public LocalDate getOrderDeadline() {
		return orderDeadline;
	}

	public void setOrderDeadline(LocalDate orderDeadline) {
		this.orderDeadline = orderDeadline;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", price=" + price + ", orderedAt=" + orderedAt + ", orderDeadline=" + orderDeadline
				+ ", orderStatus=" + orderStatus + "]";
	}
}
