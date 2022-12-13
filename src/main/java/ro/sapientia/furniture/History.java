package ro.sapientia.furniture;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table
public class History {
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
	private Long id;
	private Long userId;
	private Long orderId;
	private String name;
	private LocalDate timestamp;
	
	public History() {
		super();
	}
	
	/**
	 * @param id
	 * @param user_id
	 * @param order_id
	 * @param name
	 * @param timestamp
	 */
	public History(Long id, Long userId, Long orderId, String name, LocalDate timestamp) {
		this.id = id;
		this.userId = userId;
		this.orderId = orderId;
		this.name = name;
		this.timestamp = timestamp;
	}
	
	
	/**
	 * @param userId
	 * @param orderId
	 * @param name
	 * @param timestamp
	 */
	public History(Long userId, Long orderId, String name, LocalDate timestamp) {
		this.userId = userId;
		this.orderId = orderId;
		this.name = name;
		this.timestamp = timestamp;
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

	/**
	 * @return the timestamp
	 */
	public LocalDate getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", name=" + name + ", timestamp="
				+ timestamp + "]";
	}
}
