package ro.sapientia.furniture.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ro.sapientia.furniture.model.enumeration.CustomerType;
import ro.sapientia.furniture.model.enumeration.PaymentMethod;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "billing_entity")
public class BillingEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_billing_entity")
	@SequenceGenerator(name="pk_billing_entity", sequenceName="pk_billing_entity")
	@Column(name="id", nullable = false, updatable = false)
	private Long id;

	@Column(name="credit_card")
	private Long creditCard;
	
	@Column(name="customer_type")
	private String customerType;
	
	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="deposit")
	private int deposit;

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

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	@Override
	public String toString() {
		return "BillingEntity [id=" + id + ", creditCard=" + creditCard + ", customerType=" + customerType
				+ ", paymentMethod=" + paymentMethod + ", deposit=" + deposit + "]";
	}
	
}
