package ro.diyfurniture.furniture.cuttingplan.model.transmission;

import java.util.List;

public class CuttingPlanSheet {
	private int index;
	private int width;
	private int height;
	private List<CuttingPlanPartPlacement> parts;

	public CuttingPlanSheet(int index, int width, int height, List<CuttingPlanPartPlacement> parts) {
		this.index = index;
		this.width = width;
		this.height = height;
		this.parts = parts;
	}

	public int getIndex() {
		return index;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public List<CuttingPlanPartPlacement> getParts() {
		return parts;
	}
}
