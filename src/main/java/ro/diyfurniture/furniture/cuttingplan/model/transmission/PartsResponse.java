package ro.diyfurniture.furniture.cuttingplan.model.transmission;

import java.util.List;

public class PartsResponse {
	private List<PartPayload> parts;

	public PartsResponse(List<PartPayload> parts) {
		this.parts = parts;
	}

	public List<PartPayload> getParts() {
		return parts;
	}
}
