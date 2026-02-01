package ro.diyfurniture.furniture.cuttingplan.model.transmission;

public class PartPayload {
	private String id;
	private String partType;
	private Long bodyId;
	private int width;
	private int height;
	private Boolean allowRotate;
	private Boolean mustAlign;

	public PartPayload() {}

	public PartPayload(String id, String partType, Long bodyId, int width, int height) {
		this.id = id;
		this.partType = partType;
		this.bodyId = bodyId;
		this.width = width;
		this.height = height;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public Long getBodyId() {
		return bodyId;
	}

	public void setBodyId(Long bodyId) {
		this.bodyId = bodyId;
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

	public Boolean getAllowRotate() {
		return allowRotate;
	}

	public void setAllowRotate(Boolean allowRotate) {
		this.allowRotate = allowRotate;
	}

	public Boolean getMustAlign() {
		return mustAlign;
	}

	public void setMustAlign(Boolean mustAlign) {
		this.mustAlign = mustAlign;
	}
}
