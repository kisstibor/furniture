package ro.diyfurniture.furniture.project.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "project_snapshot")
public class ProjectSnapshot {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_project_snapshot")
	@SequenceGenerator(name = "pk_project_snapshot", sequenceName = "pk_project_snapshot", allocationSize = 1)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "project_id", nullable = false, unique = true, length = 128)
	private String projectId;

	@Column(name = "owner_id", nullable = false, length = 128)
	private String ownerId;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "snapshot_json", nullable = false, columnDefinition = "TEXT")
	private String snapshotJson;

	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSnapshotJson() {
		return snapshotJson;
	}

	public void setSnapshotJson(String snapshotJson) {
		this.snapshotJson = snapshotJson;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
}
