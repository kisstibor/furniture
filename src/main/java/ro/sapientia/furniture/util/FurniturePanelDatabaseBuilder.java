package ro.sapientia.furniture.util;

import org.springframework.stereotype.Component;
import ro.sapientia.furniture.model.FurniturePanel;

import java.util.List;

@Component
public class FurniturePanelDatabaseBuilder {

    public static FurniturePanel buildTestFurniturePanel() {
        return buildFurniturePanel(50, 80, 90);
    }

    public static List<FurniturePanel> buildTestFurniturePanels() {
        return List.of(
                buildFurniturePanel(50, 80, 90),
                buildFurniturePanel(150, 180, 190)
        );
    }

    public static FurniturePanel buildEmptyFurniturePanel() {
        return FurniturePanel.builder().build();
    }

    public static FurniturePanel buildFurniturePanel(final int width, final int height, final int depth) {
        return FurniturePanel.builder()
                .width(width)
                .heigth(height)
                .depth(depth)
                .build();
    }
}
