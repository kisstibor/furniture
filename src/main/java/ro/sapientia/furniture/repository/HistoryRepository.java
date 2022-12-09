package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.sapientia.furniture.History;

@Repository 
public interface HistoryRepository extends JpaRepository<History, Long> {

}
