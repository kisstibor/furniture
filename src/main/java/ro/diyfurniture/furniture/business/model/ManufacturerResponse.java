package ro.diyfurniture.furniture.business.model;

public class ManufacturerResponse {
	private final String businessId;
	private final String name;

	public ManufacturerResponse(String businessId, String name) {
		this.businessId = businessId;
		this.name = name;
	}

	public String getBusinessId() {
		return businessId;
	}

	public String getName() {
		return name;
	}
}
