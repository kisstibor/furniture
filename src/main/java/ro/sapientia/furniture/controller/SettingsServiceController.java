package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.model.SettingsServiceBody;
import ro.sapientia.furniture.service.FurnitureSettingsService;

@RestController
@RequestMapping("/furniture")
public class SettingsServiceController {
	private final FurnitureSettingsService furnitureSettingsService;
	
	public SettingsServiceController(final FurnitureSettingsService furnitureSettingsService) {
		this.furnitureSettingsService = furnitureSettingsService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<SettingsServiceBody>> getAllFurnitureBodies(){
		final List<SettingsServiceBody> settingsServiceBodies = furnitureSettingsService.findAllSettingsServiceBody();
		return new ResponseEntity<>(settingsServiceBodies,HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<SettingsServiceBody> getFurnitureBodyById(@PathVariable("id") Long id){
		final SettingsServiceBody settingsServiceBody = furnitureSettingsService.findServiceBodyById(id);
		return new ResponseEntity<>(settingsServiceBody,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SettingsServiceBody> addSettingsServiceBody(@RequestBody SettingsServiceBody settingsServiceBody){
		final SettingsServiceBody persistenSettingsServiceBody = furnitureSettingsService.create(settingsServiceBody);
		return new ResponseEntity<>(persistenSettingsServiceBody,HttpStatus.CREATED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<SettingsServiceBody> updateSettingsServiceBody(@RequestBody SettingsServiceBody settingsServiceBody){
		final SettingsServiceBody persistenSettingsServiceBody = furnitureSettingsService.update(settingsServiceBody);
		return new ResponseEntity<>(persistenSettingsServiceBody,HttpStatus.OK);
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteSettingsServiceBodyById(@PathVariable("id") Long id){
		furnitureSettingsService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
