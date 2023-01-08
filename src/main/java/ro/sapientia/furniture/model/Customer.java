package ro.sapientia.furniture.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "customer_body")
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_customer")
	@SequenceGenerator(name="pk_customer_body",sequenceName="pk_customer") 
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
}
