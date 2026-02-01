package ro.diyfurniture.furniture.business.model;

public class SheetTypeResponse {
	private String id;
	private String businessId;
	private String name;
	private int width;
	private int height;
	private int thickness;
	private double kerf;
	private double edgeBand;

	public SheetTypeResponse(
		String id,
		String businessId,
		String name,
		int width,
		int height,
		int thickness,
		double kerf,
		double edgeBand
	) {
		this.id = id;
		this.businessId = businessId;
		this.name = name;
		this.width = width;
		this.height = height;
		this.thickness = thickness;
		this.kerf = kerf;
		this.edgeBand = edgeBand;
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getThickness() {
		return thickness;
	}

	public double getKerf() {
		return kerf;
	}

	public double getEdgeBand() {
		return edgeBand;
	}
}
