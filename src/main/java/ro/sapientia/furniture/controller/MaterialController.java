package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.error.MaterialNotFoundException;
import ro.sapientia.furniture.model.Material;
import ro.sapientia.furniture.service.MaterialService;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(final MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Material>> getAllMaterial(){
        final List<Material> material = materialService.findAllMaterials();
        return new ResponseEntity<>(material, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable("id") Long id) throws MaterialNotFoundException {
        final Material material = materialService.findMaterialById(id);
        return new ResponseEntity<>(material,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Material> addMaterial(@RequestBody Material material){
        final Material persistenMaterial = materialService.create(material);
        return new ResponseEntity<>(persistenMaterial,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Material> updateMaterial(@RequestBody Material material){
        final Material persistenMaterial = materialService.update(material);
        return new ResponseEntity<>(persistenMaterial,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteMaterialById(@PathVariable("id") Long id){
        materialService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
