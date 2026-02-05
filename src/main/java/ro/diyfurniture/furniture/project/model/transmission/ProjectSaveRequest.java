package ro.diyfurniture.furniture.project.model.transmission;

import com.fasterxml.jackson.databind.JsonNode;

public class ProjectSaveRequest {

	private String ownerId;
	private String projectId;
	private String name;
	private JsonNode snapshot;

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JsonNode getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(JsonNode snapshot) {
		this.snapshot = snapshot;
	}
}
