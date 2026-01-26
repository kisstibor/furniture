package ro.diyfurniture.furniture.cuttingplan.model.transmission;

public class CuttingPlanPartPlacement {
	private String id;
	private String partType;
	private int x;
	private int y;
	private int width;
	private int height;

	public CuttingPlanPartPlacement(String id, String partType, int x, int y, int width, int height) {
		this.id = id;
		this.partType = partType;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public String getId() {
		return id;
	}

	public String getPartType() {
		return partType;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
