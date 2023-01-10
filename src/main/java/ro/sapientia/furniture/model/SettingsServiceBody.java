package ro.sapientia.furniture.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "furniture_SettingsService")
public class SettingsServiceBody implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_settingsservice")
	@SequenceGenerator(name="pk_settingsservice",sequenceName="pk_settingsservice") 
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_settingsservice")
	@SequenceGenerator(name="pk_settingsservice",sequenceName="pk_settingsservice") 
	@Column(name = "userid", nullable = false, updatable = false)
	private Long userid;
	
	@Column(name = "receive_email_notification")
	private int receive_email_notification;
	
	@Column(name = "receive_sms_notification")
	private int receive_sms_notification;
	
	@Column(name = "notification_frequency")
	private int notification_frequency;
	
	@Column(name = "created_at")
	private Timestamp created_at;
	
	@Column(name = "updated_at")
	private Timestamp updated_at;
	
	@Column(name = "furniture_type")
	private String furniture_type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public int getReceive_email_notification() {
		return receive_email_notification;
	}

	public void setReceive_email_notification(int receive_email_notification) {
		this.receive_email_notification = receive_email_notification;
	}

	public int getReceive_sms_notification() {
		return receive_sms_notification;
	}

	public void setReceive_sms_notification(int receive_sms_notification) {
		this.receive_sms_notification = receive_sms_notification;
	}

	public int getNotification_frequency() {
		return notification_frequency;
	}

	public void setNotification_frequency(int notification_frequency) {
		this.notification_frequency = notification_frequency;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public String getFurniture_type() {
		return furniture_type;
	}

	public void setFurniture_type(String furniture_type) {
		this.furniture_type = furniture_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "SettingsServiceBody [id=" + id + ", userid=" + userid + ", receive_email_notification=" + receive_email_notification + ", receive_sms_notification=" + receive_sms_notification + ", notification_frequency=" + notification_frequency + ", created_at=" + created_at + ", updated_at=" + updated_at + ", furniture_type=" + furniture_type + "]";
	}
}
