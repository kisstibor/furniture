package ro.diyfurniture.furniture.cuttingplan.model.transmission;

import java.util.List;

public class CuttingPlanResponse {
	private String status;
	private List<CuttingPlanSheet> sheets;
	private CuttingPlanStats stats;
	private List<PartPayload> parts;

	public CuttingPlanResponse(String status, List<CuttingPlanSheet> sheets, CuttingPlanStats stats,
		List<PartPayload> parts) {
		this.status = status;
		this.sheets = sheets;
		this.stats = stats;
		this.parts = parts;
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

	public List<PartPayload> getParts() {
		return parts;
	}
}
