package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.exception.FurniturePanelNotFoundException;
import ro.sapientia.furniture.model.FurniturePanel;
import ro.sapientia.furniture.repository.FurniturePanelRepository;

import java.util.List;


@Service
public class FurniturePanelService implements IFurniturePanelService {

    private final FurniturePanelRepository furniturePanelRepository;

    public FurniturePanelService(final FurniturePanelRepository furniturePanelRepository) {
        this.furniturePanelRepository = furniturePanelRepository;
    }

    public List<FurniturePanel> findAllFurniturePanels() {
        return this.furniturePanelRepository.findAll();
    }

    public FurniturePanel findFurniturePanelById(final Long id) {
        return furniturePanelRepository.findById(id).orElseThrow(
                () -> new FurniturePanelNotFoundException("Furniture panel not found in db"));
    }

    public FurniturePanel create(FurniturePanel furniturePanel) {
        return this.furniturePanelRepository.saveAndFlush(furniturePanel);
    }

    public FurniturePanel update(FurniturePanel furniturePanel) {
        findFurniturePanelById(furniturePanel.getId());
        return this.furniturePanelRepository.saveAndFlush(furniturePanel);
    }

    public void delete(Long id) {
        findFurniturePanelById(id);
        this.furniturePanelRepository.deleteById(id);
    }
}
