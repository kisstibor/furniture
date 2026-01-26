package ro.diyfurniture.furniture.cuttingplan.model.transmission;

import java.util.List;

public class BodyPayload {
	private Long id;
	private String name;
	private Integer width;
	private Integer height;
	private Integer deepth;
	private Integer thickness;
	private List<FrontElementPayload> frontElements;
	private Object frontDetails;
	private Boolean includeBack;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getDeepth() {
		return deepth;
	}

	public void setDeepth(Integer deepth) {
		this.deepth = deepth;
	}

	public Integer getThickness() {
		return thickness;
	}

	public void setThickness(Integer thickness) {
		this.thickness = thickness;
	}

	public List<FrontElementPayload> getFrontElements() {
		return frontElements;
	}

	public void setFrontElements(List<FrontElementPayload> frontElements) {
		this.frontElements = frontElements;
	}

	public Object getFrontDetails() {
		return frontDetails;
	}

	public void setFrontDetails(Object frontDetails) {
		this.frontDetails = frontDetails;
	}

	public Boolean getIncludeBack() {
		return includeBack;
	}

	public void setIncludeBack(Boolean includeBack) {
		this.includeBack = includeBack;
	}
}
