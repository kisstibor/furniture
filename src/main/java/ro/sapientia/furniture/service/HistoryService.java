package ro.sapientia.furniture.service;

import java.time.LocalDate;
import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import ro.sapientia.furniture.History;

@Component
public class HistoryService {
	
	public List<History> getHistory() {
		return List.of(
				new History("TestOrder", 110, LocalDate.now()),
				new History("TestOrder2", 220, LocalDate.now().minusDays(1)),
				new History("TestOrder3", 330, LocalDate.now().minusDays(2))
				);
	}
}
