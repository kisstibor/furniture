package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.service.BillingEntityService;

import ro.sapientia.furniture.model.BillingEntity;

@RestController
@RequestMapping("/billingEntity")
public class BillingEntityController {

	private final  BillingEntityService billingEntityService;
	
	public BillingEntityController(final BillingEntityService billingEntitySerice) {
		this.billingEntityService = billingEntitySerice;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BillingEntity>> getAllBillingEntityBodies() {
		final List<BillingEntity> billingEntityBodies = billingEntityService.findAllBillingEntities();
		return new ResponseEntity<>(billingEntityBodies, HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<BillingEntity> getBillingEntityById(@PathVariable("id") Long id) {
		final BillingEntity billingEntity = billingEntityService.findBillingEntityById(id);
		return new ResponseEntity<>(billingEntity, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<BillingEntity> createBillingEntity(@RequestBody BillingEntity billingEntity) {
		final BillingEntity persistentBillingEntity = billingEntityService.create(billingEntity);
		return new ResponseEntity<>(persistentBillingEntity, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<BillingEntity> updateBillingEntity(@RequestBody BillingEntity billingEntity) {
		final BillingEntity persistentBillingEntity = billingEntityService.update(billingEntity);
		return new ResponseEntity<>(persistentBillingEntity, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBillingEntity(@PathVariable("id") final Long id) {
		billingEntityService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}