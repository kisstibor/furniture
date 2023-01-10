package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.util.ErrorHandling;
import ro.sapientia.furniture.util.StatusMessage;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.service.ManufacturerService;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> getAllManufacturers() {
        try {
            return new ResponseEntity<>(manufacturerService.findAllManufacturers(), HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getManufacturerById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(manufacturerService.findManufacturerById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createManufacturer(@RequestBody Manufacturer manufacturer) {
        try {
            return new ResponseEntity<>(manufacturerService.create(manufacturer), HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteManufacturer(@PathVariable("id") Long id) {
        try {
            manufacturerService.delete(id);
            return new ResponseEntity<>(StatusMessage.OK, HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }
}
