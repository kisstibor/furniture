package ro.sapientia.furniture.model;
import java.util.List;

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

import lombok.AllArgsConstructor;
<<<<<<< HEAD
=======
import lombok.Builder;
>>>>>>> ebb9041 (Resolved merge error)
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
<<<<<<< HEAD
=======
@Builder
>>>>>>> ebb9041 (Resolved merge error)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "shipment")
public class Shipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_shipment")
	@SequenceGenerator(name="pk_shipment",sequenceName="pk_shipment") 
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
<<<<<<< HEAD
	@Cascade(value= {org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "order_id", nullable = false)
	@OneToMany(mappedBy="shipment",cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonIgnore
	private List<OrderEntity> order;
=======
//	@NotNull
//	@OneToMany
//	@Cascade(value= {org.hibernate.annotations.CascadeType.ALL})
//	@JoinColumn(name = "order_id", nullable = false)
//	private List<Order> orderId;
>>>>>>> ebb9041 (Resolved merge error)

	@Column(name = "street")
	private String street;

	@Column(name = "nr")
	private String nr;
	
	@Column(name = "city")
	private String city;

	@Column(name = "post_code")
	private int postCode;
}
