package ro.diyfurniture.furniture.cuttingplan.model.transmission;

import java.util.List;

public class PartsRequest {
	private List<BodyPayload> bodies;
	private Stock stock;

	public List<BodyPayload> getBodies() {
		return bodies;
	}

	public void setBodies(List<BodyPayload> bodies) {
		this.bodies = bodies;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
