package ro.sapientia.furniture.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sales")
public class Sale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_sales")
	@SequenceGenerator(name="pk_sales", sequenceName="pk_sales")
	@Column(name = "sale_id", nullable = false, updatable = false)
	private Long id;
	
	@NotNull
	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "service_point_id", nullable = false)
	private ServicePoint servicePoint;
	
	@Column(name = "total_price")
	private BigDecimal totalPrice;
	
	@Column(name = "saled_date")
	private Timestamp saledDate;
}
