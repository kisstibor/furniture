package ro.diyfurniture.furniture.logging.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "ui_log_event")
public class UiLogEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_ui_log_event")
	@SequenceGenerator(name = "pk_ui_log_event", sequenceName = "pk_ui_log_event", allocationSize = 1)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "event_time", nullable = false)
	private Instant eventTime;

	@Column(name = "received_at", nullable = false)
	private Instant receivedAt;

	@Column(name = "level", nullable = false, length = 16)
	private String level;

	@Column(name = "message", nullable = false, length = 2048)
	private String message;

	@Column(name = "route", length = 1024)
	private String route;

	@Column(name = "session_id", length = 128)
	private String sessionId;

	@Column(name = "user_id", length = 128)
	private String userId;

	@Column(name = "data_json", columnDefinition = "TEXT")
	private String dataJson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getEventTime() {
		return eventTime;
	}

	public void setEventTime(Instant eventTime) {
		this.eventTime = eventTime;
	}

	public Instant getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Instant receivedAt) {
		this.receivedAt = receivedAt;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
}
