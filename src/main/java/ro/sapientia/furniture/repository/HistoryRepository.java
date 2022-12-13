package ro.sapientia.furniture.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.sapientia.furniture.History;

@Repository 
public interface HistoryRepository extends JpaRepository<History, Long> {

	@Query("SELECT h FROM History h WHERE h.name = ?1")
	Optional<History> findHistoryByName(String name);
}
