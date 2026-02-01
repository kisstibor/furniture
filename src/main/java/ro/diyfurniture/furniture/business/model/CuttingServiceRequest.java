package ro.diyfurniture.furniture.business.model;

public class CuttingServiceRequest {
	private String businessId;
	private String name;
	private double pricePerSheet;
	private String notes;

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPricePerSheet() {
		return pricePerSheet;
	}

	public void setPricePerSheet(double pricePerSheet) {
		this.pricePerSheet = pricePerSheet;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
