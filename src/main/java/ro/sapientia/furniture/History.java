package ro.sapientia.furniture;

import java.time.LocalDate;

public class History {
	
	private String orderName;
	private Integer pieceNr;
	private LocalDate timestamp;

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
