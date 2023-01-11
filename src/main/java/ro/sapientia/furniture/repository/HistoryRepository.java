package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sapientia.furniture.model.HistoryBody;

public interface HistoryRepository extends JpaRepository<HistoryBody, Long> {

	HistoryBody findHistoryById(Long id);

}
