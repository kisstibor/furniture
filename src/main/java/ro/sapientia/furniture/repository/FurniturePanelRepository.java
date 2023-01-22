package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.FurniturePanel;

public interface FurniturePanelRepository extends JpaRepository<FurniturePanel, Long> {

    FurniturePanel findFurniturePanelById(Long id);

}
