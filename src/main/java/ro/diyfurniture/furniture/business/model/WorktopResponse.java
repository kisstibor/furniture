package ro.diyfurniture.furniture.business.model;

public class WorktopResponse {
	private String id;
	private String businessId;
	private String name;
	private int thickness;
	private int frontOverhang;
	private int leftOverhang;
	private int rightOverhang;
	private int backOverhang;

	public WorktopResponse(
		String id,
		String businessId,
		String name,
		int thickness,
		int frontOverhang,
		int leftOverhang,
		int rightOverhang,
		int backOverhang
	) {
		this.id = id;
		this.businessId = businessId;
		this.name = name;
		this.thickness = thickness;
		this.frontOverhang = frontOverhang;
		this.leftOverhang = leftOverhang;
		this.rightOverhang = rightOverhang;
		this.backOverhang = backOverhang;
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

	public int getThickness() {
		return thickness;
	}

	public int getFrontOverhang() {
		return frontOverhang;
	}

	public int getLeftOverhang() {
		return leftOverhang;
	}

	public int getRightOverhang() {
		return rightOverhang;
	}

	public int getBackOverhang() {
		return backOverhang;
	}
}
