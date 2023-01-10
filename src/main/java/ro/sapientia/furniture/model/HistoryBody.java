package ro.sapientia.furniture.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.Column;

@Entity(name = "history")
@Table
public class HistoryBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(
			name = "history_sequence",
			sequenceName = "history_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "history.sequence"
	)
	
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "userId")
	private Long userId;

	@Column(name = "orderId")
	private Long orderId;

	@Column(name = "name")
	private String name;
	
	public HistoryBody() {
		super();
	}
	
	/**
	 * @param id
	 * @param user_id
	 * @param order_id
	 * @param name
	 * @param timestamp
	 */
	public HistoryBody(Long id, Long userId, Long orderId, String name, LocalDate timestamp) {
		this.id = id;
		this.userId = userId;
		this.orderId = orderId;
		this.name = name;
	}
	
	
	/**
	 * @param userId
	 * @param orderId
	 * @param name
	 * @param timestamp
	 */
	public HistoryBody(Long userId, Long orderId, String name) {
		this.userId = userId;
		this.orderId = orderId;
		this.name = name;
	}
	
	public HistoryBody(Long orderId, String name) {
		this.orderId = orderId;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", name=" + name + "]";
	}
}
