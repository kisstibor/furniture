package ro.diyfurniture.furniture.business.model;

public class SheetTypeRequest {
	private String businessId;
	private String name;
	private int width;
	private int height;
	private int thickness;
	private double kerf;
	private double edgeBand;

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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public double getKerf() {
		return kerf;
	}

	public void setKerf(double kerf) {
		this.kerf = kerf;
	}

	public double getEdgeBand() {
		return edgeBand;
	}

	public void setEdgeBand(double edgeBand) {
		this.edgeBand = edgeBand;
	}
}
