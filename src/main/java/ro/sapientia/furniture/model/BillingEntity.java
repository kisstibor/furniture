package ro.sapientia.furniture.model;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BillingEntity {
	
	@OneToOne(mappedBy="billingEntity",cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonIgnore
	private Order order;
}
