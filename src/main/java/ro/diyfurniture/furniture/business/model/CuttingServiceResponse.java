package ro.diyfurniture.furniture.business.model;

public class CuttingServiceResponse {
	private String id;
	private String businessId;
	private String name;
	private double pricePerSheet;
	private String notes;

	public CuttingServiceResponse(String id, String businessId, String name, double pricePerSheet, String notes) {
		this.id = id;
		this.businessId = businessId;
		this.name = name;
		this.pricePerSheet = pricePerSheet;
		this.notes = notes;
	}

	public String getId() {
		return id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public String getName() {
		return name;
	}

	public double getPricePerSheet() {
		return pricePerSheet;
	}

	public String getNotes() {
		return notes;
	}
}
