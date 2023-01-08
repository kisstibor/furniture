package ro.sapientia.furniture.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

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
	private int deposit;
	
	

	public BillingEntity(Long id, @NotNull Long creditCard, String customerName, int deposit) {
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

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	@Override
	public String toString() {
		return "BillingEntity [id=" + id + ", creditCard=" + creditCard + ", customerName=" + customerName
				+ ", deposit=" + deposit + "]";
	}
	
}
