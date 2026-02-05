package ro.diyfurniture.furniture.logging.model.transmission;

import java.util.ArrayList;
import java.util.List;

public class UiLogBatchRequest {

	private List<UiLogEntryRequest> logs = new ArrayList<>();

	public List<UiLogEntryRequest> getLogs() {
		return logs;
	}

	public void setLogs(List<UiLogEntryRequest> logs) {
		this.logs = logs;
	}
}
