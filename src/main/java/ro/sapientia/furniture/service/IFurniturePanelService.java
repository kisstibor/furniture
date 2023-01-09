package ro.sapientia.furniture.service;

import ro.sapientia.furniture.model.FurniturePanel;

import java.util.List;

public interface IFurniturePanelService {

    List<FurniturePanel> findAllFurniturePanels();

    FurniturePanel findFurniturePanelById(final Long id);

    FurniturePanel create(FurniturePanel furniturePanel);

    FurniturePanel update(FurniturePanel furniturePanel);

    void delete(Long id);
}
