package ro.sapientia.furniture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.History;
import ro.sapientia.furniture.service.HistoryService;

import java.util.List;

/**
 * igy hatarozzuk meg az endpointot
 */
@RestController
@RequestMapping(path = "api/history")
public class HistoryController {
	
	private final HistoryService historyService;
	
	@Autowired
	public HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	/**
	 * azzal hivjuk meg ezt a getHistory() fuggvenyt, hogy a browserben localhost:8080/api/history-ra megyunk
	 */
	@GetMapping
	public List<History> getHistory() {
		return historyService.getHistory();
	}
	
	@PostMapping
	public void registerNewHistory(@RequestBody History history) {
		historyService.addNewHistory(history);
	}
}
