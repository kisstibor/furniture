package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.FurnitureItemJoin;
import ro.sapientia.furniture.service.FurnitureItemJoinService;

import java.util.List;

@RestController
@RequestMapping("/furnitureItemJoin")
public class FurnitureItemJoinController {

    private final FurnitureItemJoinService furnitureItemJoinService;

    public FurnitureItemJoinController(final FurnitureItemJoinService furnitureItemJoinService) {
        this.furnitureItemJoinService = furnitureItemJoinService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FurnitureItemJoin>> getAllFurnitureItemJoins(){
        final List<FurnitureItemJoin> furnitureItemJoins = furnitureItemJoinService.findAllFurnitureItemJoins();
        return new ResponseEntity<>(furnitureItemJoins, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FurnitureItemJoin> getFurnitureItemJoinById(@PathVariable("id") Long id){
        final FurnitureItemJoin furnitureItemJoin = furnitureItemJoinService.findFurnitureItemJoinById(id);
        System.out.println(furnitureItemJoin);
        return new ResponseEntity<>(furnitureItemJoin,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FurnitureItemJoin> addFurnitureBody(@RequestBody FurnitureItemJoin furnitureBody){
        final FurnitureItemJoin persistenFurnitureItemJoin = furnitureItemJoinService.create(furnitureBody);
        return new ResponseEntity<>(persistenFurnitureItemJoin,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<FurnitureItemJoin> updateFurnitureItemJoin(@RequestBody FurnitureItemJoin furnitureBody){
        final FurnitureItemJoin persistenFurnitureItemJoin = furnitureItemJoinService.update(furnitureBody);
        return new ResponseEntity<>(persistenFurnitureItemJoin, HttpStatus.OK);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteFurnitureItemJoinsById(@PathVariable("id") Long id){
        furnitureItemJoinService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
