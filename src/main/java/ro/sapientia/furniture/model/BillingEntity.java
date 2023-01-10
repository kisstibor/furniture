package ro.sapientia.furniture.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "billing_entity")
@ToString
public class BillingEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_billing_entity")
	@SequenceGenerator(name="pk_billing_entity", sequenceName="pk_billing_entity")
	@Column(name="id", nullable = false, updatable = false)
	private Long id;

	@Column(name="credit_card")
	@NotNull
	private Long creditCard;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="deposit")
	private double deposit;	
   
    @OneToOne(mappedBy="billingEntity",cascade= {CascadeType.PERSIST,CascadeType.REMOVE})  
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

	public BillingEntity(Long id, @NotNull Long creditCard, String customerName, double deposit) {
		super();
		this.id = id;
		this.creditCard = creditCard;
		this.customerName = customerName;
		this.deposit = deposit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Long creditCard) {
		this.creditCard = creditCard;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public BillingEntity() {
		super();
	}
	

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "BillingEntity [id=" + id + ", creditCard=" + creditCard + ", customerName=" + customerName
				+ ", deposit=" + deposit + "]";
	}
}
