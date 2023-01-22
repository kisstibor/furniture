package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.FurniturePanel;
import ro.sapientia.furniture.service.FurniturePanelService;

import java.util.List;

@RestController
@RequestMapping("/furniturePanel")
public class FurniturePanelController {

    private final FurniturePanelService furniturePanelService;

    public FurniturePanelController(final FurniturePanelService furniturePanelService) {
        this.furniturePanelService = furniturePanelService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FurniturePanel>> getAllFurniturePanels() {
        final List<FurniturePanel> furniturePanels = furniturePanelService.findAllFurniturePanels();
        return new ResponseEntity<>(furniturePanels, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FurniturePanel> getFurniturePanelById(@PathVariable("id") Long id) {
        final FurniturePanel furniturePanel = furniturePanelService.findFurniturePanelById(id);
        return new ResponseEntity<>(furniturePanel, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FurniturePanel> addFurniturePanel(@RequestBody FurniturePanel furniturePanel) {
        final FurniturePanel persistFurniturePanel = furniturePanelService.create(furniturePanel);
        return new ResponseEntity<>(persistFurniturePanel, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<FurniturePanel> updateFurniturePanel(@RequestBody FurniturePanel furniturePanel) {
        final FurniturePanel persistFurniturePanel = furniturePanelService.update(furniturePanel);
        return new ResponseEntity<>(persistFurniturePanel, HttpStatus.OK);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteFurniturePanelById(@PathVariable("id") Long id) {
        furniturePanelService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
