package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.model.HistoryBody;
import ro.sapientia.furniture.repository.HistoryRepository;

@Service
public class HistoryService {
	
	private final HistoryRepository historyRepository;
	
	public HistoryService(final HistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}
	
	public List<HistoryBody> findAllHistoryBodies() {
		return this.historyRepository.findAll();
	}

	public HistoryBody findHistoryBodyById(final Long id) {
		return this.historyRepository.findHistoryById(id);
	}

	public HistoryBody create(HistoryBody historyBody) {
		return this.historyRepository.saveAndFlush(historyBody);
	}

	public HistoryBody update(HistoryBody historyBody) {
		return this.historyRepository.saveAndFlush(historyBody);
	}

	public void delete(Long id) {
		this.historyRepository.deleteById(id);
	}

}