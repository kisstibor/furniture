package ro.sapientia.furniture.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.sapientia.furniture.model.enumeration.CustomerType;
import ro.sapientia.furniture.model.enumeration.PaymentMethod;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillingEntityRequestDTO {
	private Long id;
	private Long creditCard;
	private String customerType;
	private String paymentMethod;
	private int deposit;
	
	public Long getId() {
		return id;
	}
	public Long getCreditCard() {
		return creditCard;
	}
	public String getCustomerType() {
		return customerType;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public int getDeposit() {
		return deposit;
	}
}
