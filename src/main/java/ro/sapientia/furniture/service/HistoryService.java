package ro.sapientia.furniture.service;

import java.util.List;
import java.util.Optional;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.sapientia.furniture.History;
import ro.sapientia.furniture.repository.HistoryRepository;

@Component
public class HistoryService {
	
	private final HistoryRepository historyRepository;
	
	@Autowired
	public HistoryService(HistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}
	
	public List<History> getHistory() {
		return historyRepository.findAll();
	}

	public void addNewHistory(History history) {
		Optional<History> historyOptional = historyRepository.findHistoryByName(history.getName());
		
		if (historyOptional.isPresent()) {
			throw new IllegalStateException("Name already taken!");
		}
		historyRepository.save(history);	
	}
}
