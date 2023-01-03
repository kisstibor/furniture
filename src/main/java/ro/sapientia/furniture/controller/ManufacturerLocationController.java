package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.service.ManufacturerLocationService;

import java.util.List;

@RestController
@RequestMapping("/manufacturerlocation")
public class ManufacturerLocationController {
    private final ManufacturerLocationService manufacturerLocationService;

    public ManufacturerLocationController(ManufacturerLocationService manufacturerLocationService) {
        this.manufacturerLocationService = manufacturerLocationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ManufacturerLocation>> getAllManufacturerLocations() {
        return new ResponseEntity<>(manufacturerLocationService.findAllManufacturerLocations(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ManufacturerLocation> getManufacturerLocationById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(manufacturerLocationService.findManufacturerLocationById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ManufacturerLocation> createManufacturerLocation(@RequestBody ManufacturerLocation manufacturerLocation) {
        return new ResponseEntity<>(manufacturerLocationService.create(manufacturerLocation), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ManufacturerLocation> updateManufacturerLocation(@RequestBody ManufacturerLocation manufacturerLocation) {
        return new ResponseEntity<>(manufacturerLocationService.update(manufacturerLocation), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteManufacturerLocation(@PathVariable("id") Long id) {
        manufacturerLocationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
