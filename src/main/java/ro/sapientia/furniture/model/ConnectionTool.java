package ro.sapientia.furniture.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "connection_tool")
public class ConnectionTool implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_connection_tool")
	@SequenceGenerator(name="pk_connection_tool", sequenceName="pk_connection_tool")
	@Column(name ="type_id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "size")
	private int size;

	@Column(name = "type")
	private String type;

	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	public int getSize(){
		return size;
	}
	public void setSize(int size){
		this.size = size;
	}

	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override public String toString(){
		return "ConnectionTool [id=" + id + ", size = " + size + ", type=" + type + "]";
	}
}
