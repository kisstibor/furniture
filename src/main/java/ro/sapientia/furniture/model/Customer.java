package ro.sapientia.furniture.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Customer {
	
	@OneToMany(mappedBy="customer",cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonIgnore
	private Set<OrderEntity> order;
}
