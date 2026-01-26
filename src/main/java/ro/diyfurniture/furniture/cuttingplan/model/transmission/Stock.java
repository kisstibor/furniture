package ro.diyfurniture.furniture.cuttingplan.model.transmission;

public class Stock {
	private Integer sheetWidth;
	private Integer sheetHeight;
	private Double kerf;
	private Double edgeBand;
	private Integer sheetThickness;
	private Boolean allowRotate;
	private Double margin;

	public Integer getSheetWidth() {
		return sheetWidth;
	}

	public void setSheetWidth(Integer sheetWidth) {
		this.sheetWidth = sheetWidth;
	}

	public Integer getSheetHeight() {
		return sheetHeight;
	}

	public void setSheetHeight(Integer sheetHeight) {
		this.sheetHeight = sheetHeight;
	}

	public Double getKerf() {
		return kerf;
	}

	public void setKerf(Double kerf) {
		this.kerf = kerf;
	}

	public Double getEdgeBand() {
		return edgeBand;
	}

	public void setEdgeBand(Double edgeBand) {
		this.edgeBand = edgeBand;
	}

	public Integer getSheetThickness() {
		return sheetThickness;
	}

	public void setSheetThickness(Integer sheetThickness) {
		this.sheetThickness = sheetThickness;
	}

	public Boolean getAllowRotate() {
		return allowRotate;
	}

	public void setAllowRotate(Boolean allowRotate) {
		this.allowRotate = allowRotate;
	}

	public Double getMargin() {
		return margin;
	}

	public void setMargin(Double margin) {
		this.margin = margin;
	}
}
