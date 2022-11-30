package ro.sapientia.furniture.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.History;
import ro.sapientia.furniture.service.HistoryService;

import java.util.List;

@RestController
@RequestMapping(path = "api/history")
public class HistoryController {
	
	private final HistoryService historyService;
	
	public HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	@GetMapping
	public List<History> getHistory() {
		return historyService.getHistory();
	}
}
