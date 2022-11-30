package ro.sapientia.furniture.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import ro.sapientia.furniture.History;

public class HistoryService {
	
	/**
	 * igy hatarozzuk meg az endpointot
	 * azzal hivjuk meg ezt a getHistory() fuggvenyt, hogy a browserben localhost:8080/history-ra megyunk
	 */
	public List<History> getHistory() {
		return List.of(
				new History("TestOrder", 110, LocalDate.now()),
				new History("TestOrder2", 220, LocalDate.now().minusDays(1)),
				new History("TestOrder3", 330, LocalDate.now().minusDays(2))
				);
	}
}
