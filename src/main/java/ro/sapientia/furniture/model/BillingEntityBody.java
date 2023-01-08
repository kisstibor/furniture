package ro.sapientia.furniture.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "billing_entity_body")
public class BillingEntityBody implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_billing_entity_body")
	@SequenceGenerator(name="pk_billing_entity_body", sequenceName="pk_billing_entity_body")
	@Column(name="id", nullable = false, updatable = false)
	private Long id;
}
