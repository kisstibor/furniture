package ro.diyfurniture.furniture.cuttingplan.model.transmission;

import java.util.List;

public class CuttingPlanRequest {
	private String units;
	private Stock stock;
	private List<BodyPayload> bodies;
	private List<PartPayload> parts;

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public List<BodyPayload> getBodies() {
		return bodies;
	}

	public void setBodies(List<BodyPayload> bodies) {
		this.bodies = bodies;
	}

	public List<PartPayload> getParts() {
		return parts;
	}

	public void setParts(List<PartPayload> parts) {
		this.parts = parts;
	}
}
