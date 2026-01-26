package ro.diyfurniture.furniture.cuttingplan.model.transmission;

import java.util.List;

public class CuttingPlanResponse {
	private String status;
	private List<CuttingPlanSheet> sheets;
	private CuttingPlanStats stats;

	public CuttingPlanResponse(String status, List<CuttingPlanSheet> sheets, CuttingPlanStats stats) {
		this.status = status;
		this.sheets = sheets;
		this.stats = stats;
	}

	public String getStatus() {
		return status;
	}

	public List<CuttingPlanSheet> getSheets() {
		return sheets;
	}

	public CuttingPlanStats getStats() {
		return stats;
	}
}
