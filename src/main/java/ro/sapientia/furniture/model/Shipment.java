package ro.sapientia.furniture.model;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "shipment")
public class Shipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_shipment")
	@SequenceGenerator(name="pk_shipment",sequenceName="pk_shipment") 
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "street")
	private String street;

	@Column(name = "nr")
	private String nr;
	
	@Column(name = "city")
	private String city;

	@Column(name = "post_code")
	private int postCode;

	@OneToMany(mappedBy="shipment",cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonIgnore
	private List<OrderEntity> order = new ArrayList<>();
	
	public Shipment() {}
	
	

	public Shipment(Long id, String street, String nr, String city, int postCode) {
		super();
		this.id = id;
		this.street = street;
		this.nr = nr;
		this.city = city;
		this.postCode = postCode;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	

	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public List<OrderEntity> getOrder() {
		return order;
	}

	public void setOrder(List<OrderEntity> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", street=" + street + ", nr=" + nr + ", city=" + city + ", postCode=" + postCode
				+ "]";
	}
	
	
}
