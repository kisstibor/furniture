package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.service.ManufacturerService;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        return new ResponseEntity<>(manufacturerService.findAllManufacturers(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(manufacturerService.findManufacturerById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(manufacturerService.create(manufacturer), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Manufacturer> updateManufacturer(@RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(manufacturerService.update(manufacturer), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteManufacturer(@PathVariable("id") Long id) {
        manufacturerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
