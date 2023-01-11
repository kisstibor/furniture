package ro.sapientia.furniture.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

@Entity(name = "shipment")
public class Shipment {

	public Shipment(Long id, String street, String nr, String city, int postCode) {
		super();
		this.id = id;
		this.street = street;
		this.nr = nr;
		this.city = city;
		this.postCode = postCode;
	}
	
	public Shipment() {}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_shipment")
	@SequenceGenerator(name="pk_shipment",sequenceName="pk_shipment") 
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
<<<<<<< Upstream, based on origin/shipment-entity
	@NotNull
	@OneToMany
	@Cascade(value= {org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "order_id", nullable = false)
	private List<OrderEntity> orders;
=======
//	@NotNull
//	@OneToMany
//	@Cascade(value= {org.hibernate.annotations.CascadeType.ALL})
//	@JoinColumn(name = "order_id", nullable = false)
//	private List<OrderEntity> orders;
>>>>>>> 522f9cd shipment modifications

	@Column(name = "street")
	private String street;

	@Column(name = "nr")
	private String nr;
	
	@Column(name = "city")
	private String city;

	@Column(name = "post_code")
	private int postCode;

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
	
<<<<<<< Upstream, based on origin/shipment-entity
	public List<OrderEntity> getOrders() {
	return orders;
}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", street=" + street + ", nr=" + nr + ", city=" + city + ", postCode=" + postCode
				+ "]";
	}

}
=======
//	public List<OrderEntity> getOrders() {
//	return orders;
//	}
//
//	public void setOrders(List<OrderEntity> orders) {
//		this.orders = orders;
//	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", street=" + street + ", nr=" + nr + ", city=" + city + ", postCode=" + postCode
				+ "]";
	}

}
>>>>>>> 522f9cd shipment modifications
