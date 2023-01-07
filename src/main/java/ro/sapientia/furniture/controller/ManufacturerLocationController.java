package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.service.ManufacturerLocationService;
import ro.sapientia.furniture.util.ErrorHandling;
import ro.sapientia.furniture.util.StatusMessage;

@RestController
@RequestMapping("/manufacturer-location")
public class ManufacturerLocationController {
    private final ManufacturerLocationService manufacturerLocationService;

    public ManufacturerLocationController(ManufacturerLocationService manufacturerLocationService) {
        this.manufacturerLocationService = manufacturerLocationService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllManufacturerLocations() {
        try {
            return new ResponseEntity<>(manufacturerLocationService.findAllManufacturerLocations(), HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getManufacturerLocationById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(manufacturerLocationService.findManufacturerLocationById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createManufacturerLocation(@RequestBody ManufacturerLocation manufacturerLocation) {
        try {
            return new ResponseEntity<>(manufacturerLocationService.create(manufacturerLocation), HttpStatus.CREATED);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateManufacturerLocation(@RequestBody ManufacturerLocation manufacturerLocation) {
        try {
            return new ResponseEntity<>(manufacturerLocationService.update(manufacturerLocation), HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteManufacturerLocation(@PathVariable("id") Long id) {
        try {
            manufacturerLocationService.delete(id);
            return new ResponseEntity<>(StatusMessage.OK, HttpStatus.OK);
        } catch (Exception e) {
            return ErrorHandling.handleControllerException(e);
        }
    }
}
