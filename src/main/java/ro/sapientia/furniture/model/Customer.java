package ro.sapientia.furniture.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "customer")
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_customer")
	@SequenceGenerator(name="pk_customer",sequenceName="pk_customer") 
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name="name")
	private String name;

	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
  
	@OneToMany(mappedBy="customer",cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonIgnore
	private List<OrderEntity> order = new ArrayList<>();
	
	public Customer() {
		super();
	}

	public Customer(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public Customer(Long id, String name, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<OrderEntity> getOrder() {
		return order;
	}

	public void setOrder(List<OrderEntity> order) {
		this.order = order;
	}
	
	
}
