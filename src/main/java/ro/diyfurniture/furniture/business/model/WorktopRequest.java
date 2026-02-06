package ro.diyfurniture.furniture.business.model;

public class WorktopRequest {
	private String businessId;
	private String name;
	private int thickness;
	private int frontOverhang;
	private int leftOverhang;
	private int rightOverhang;
	private int backOverhang;

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

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public int getFrontOverhang() {
		return frontOverhang;
	}

	public void setFrontOverhang(int frontOverhang) {
		this.frontOverhang = frontOverhang;
	}

	public int getLeftOverhang() {
		return leftOverhang;
	}

	public void setLeftOverhang(int leftOverhang) {
		this.leftOverhang = leftOverhang;
	}

	public int getRightOverhang() {
		return rightOverhang;
	}

	public void setRightOverhang(int rightOverhang) {
		this.rightOverhang = rightOverhang;
	}

	public int getBackOverhang() {
		return backOverhang;
	}

	public void setBackOverhang(int backOverhang) {
		this.backOverhang = backOverhang;
	}
}
