package ro.sapientia.furniture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
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
	
	@Override 
	public String toString() {
		return "Shipment [id=" + id + ", order id=" + orderId + ", shipping adress=" + city + street + nr + "[postcode:" + postCode + " ]" + "]";
	}

	public static long getSerialversionUid() {
		return serialVersionUID;
	}

}
