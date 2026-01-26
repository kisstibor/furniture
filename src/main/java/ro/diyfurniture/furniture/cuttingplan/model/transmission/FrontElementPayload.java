package ro.diyfurniture.furniture.cuttingplan.model.transmission;

public class FrontElementPayload {
	private Long bodyId;
	private Integer elementType;
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private Object details;

	public Long getBodyId() {
		return bodyId;
	}

	public void setBodyId(Long bodyId) {
		this.bodyId = bodyId;
	}

	public Integer getElementType() {
		return elementType;
	}

	public void setElementType(Integer elementType) {
		this.elementType = elementType;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}
}
