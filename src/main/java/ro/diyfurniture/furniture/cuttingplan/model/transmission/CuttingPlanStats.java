package ro.diyfurniture.furniture.cuttingplan.model.transmission;

public class CuttingPlanStats {
	private int sheetCount;
	private long usedArea;
	private double wastePercent;

	public CuttingPlanStats(int sheetCount, long usedArea, double wastePercent) {
		this.sheetCount = sheetCount;
		this.usedArea = usedArea;
		this.wastePercent = wastePercent;
	}

	public int getSheetCount() {
		return sheetCount;
	}

	public long getUsedArea() {
		return usedArea;
	}

	public double getWastePercent() {
		return wastePercent;
	}
}
