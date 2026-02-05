package ro.diyfurniture.furniture.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ro.diyfurniture.furniture.logging.model.UiLogEvent;

public interface UiLogEventRepository extends JpaRepository<UiLogEvent, Long>, JpaSpecificationExecutor<UiLogEvent> {
}
