package ro.diyfurniture.furniture.cuttingplan.model.transmission;

import java.util.List;

public class PlanRequest {
	private List<PartPayload> parts;
	private Stock stock;

	public List<PartPayload> getParts() {
		return parts;
	}

	public void setParts(List<PartPayload> parts) {
		this.parts = parts;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
