package ro.sapientia.furniture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "shipment")
public class Shipment {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_shipment")
	@SequenceGenerator(name="pk_shipment",sequenceName="pk_shipment") 
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "orderId")
	private Long orderId;

	@Column(name = "street")
	private String street;

	@Column(name = "nr")
	private String nr;
	
	@Column(name = "city")
	private String city;

	@Column(name = "postCode")
	private int postCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override 
	public String toString() {
		return "Shipment [id=" + id + ", order id=" + orderId + ", shipping adress=" + city + street + nr + "[postcode:" + postCode + " ]" + "]";
	}

}
