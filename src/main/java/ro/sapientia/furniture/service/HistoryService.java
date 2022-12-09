package ro.sapientia.furniture.service;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ro.sapientia.furniture.History;
import ro.sapientia.furniture.repository.HistoryRepository;

@Service
public class HistoryService {
	
	private final HistoryRepository historyRepository;
	
	@Autowired
	public HistoryService(HistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}
	
	public List<History> getHistory() {
		return historyRepository.findAll();
	}
}
