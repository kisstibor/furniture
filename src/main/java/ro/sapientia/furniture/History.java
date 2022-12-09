package ro.sapientia.furniture;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private String orderName;
	private Integer pieceNr;
	private LocalDate timestamp;

	/**
	 * @param id
	 * @param orderName
	 * @param pieceNr
	 * @param timestamp
	 */
	public History(Long id, String orderName, Integer pieceNr, LocalDate timestamp) {
		super();
		this.id = id;
		this.orderName = orderName;
		this.pieceNr = pieceNr;
		this.timestamp = timestamp;
	}

	/**
	 * @param orderName
	 * @param pieceNr
	 * @param timestamp
	 */
	public History(String orderName, Integer pieceNr, LocalDate timestamp) {
		super();
		this.orderName = orderName;
		this.pieceNr = pieceNr;
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
	 * @return the orderName
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * @param orderName the orderName to set
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * @return the pieceNr
	 */
	public Integer getPieceNr() {
		return pieceNr;
	}

	/**
	 * @param pieceNr the pieceNr to set
	 */
	public void setPieceNr(Integer pieceNr) {
		this.pieceNr = pieceNr;
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
		return "HistoryController [orderName=" + orderName + ", pieceNr=" + pieceNr + ", timestamp=" + timestamp + "]";
	}

}
