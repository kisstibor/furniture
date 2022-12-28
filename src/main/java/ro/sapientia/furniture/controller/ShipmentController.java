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

import ro.sapientia.furniture.model.Shipment;
import ro.sapientia.furniture.service.ShipmentService;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
	
	private final ShipmentService shipmentService;
	
	public ShipmentController(final ShipmentService shipmentService) {
		this.shipmentService = shipmentService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Shipment>> getAllShipments(){
		return new ResponseEntity<>(shipmentService.findAllShipments(), HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Shipment> getShipmentById(@PathVariable("id") Long id){
		return new ResponseEntity<>(shipmentService.findShipmentById(id), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Shipment> addFurnitureBody(@RequestBody Shipment shipment){
		return new ResponseEntity<>(shipmentService.create(shipment), HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<Shipment> updateFurnitureBody(@RequestBody Shipment shipment){
		return new ResponseEntity<>(shipmentService.update(shipment), HttpStatus.OK);
	}

	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteFurnitureBodyById(@PathVariable("id") Long id){
		shipmentService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
