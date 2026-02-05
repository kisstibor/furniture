package ro.diyfurniture.furniture.project.model.transmission;

import com.fasterxml.jackson.databind.JsonNode;

public class ProjectLoadResponse extends ProjectSummaryResponse {

	private JsonNode snapshot;

	public JsonNode getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(JsonNode snapshot) {
		this.snapshot = snapshot;
	}
}
