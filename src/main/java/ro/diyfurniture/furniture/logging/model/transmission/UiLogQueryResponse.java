package ro.diyfurniture.furniture.logging.model.transmission;

import java.util.ArrayList;
import java.util.List;

public class UiLogQueryResponse {

	private long total;
	private int count;
	private List<UiLogEntryResponse> items = new ArrayList<>();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<UiLogEntryResponse> getItems() {
		return items;
	}

	public void setItems(List<UiLogEntryResponse> items) {
		this.items = items;
	}
}
