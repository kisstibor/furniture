package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.service.BillingEntityBodyService;

import ro.sapientia.furniture.model.BillingEntityBody;

@RestController
@RequestMapping("/billingEntity")
public class BillingEntityController {

	private final  BillingEntityBodyService billingEntityBodyService;
	
	public BillingEntityController(final BillingEntityBodyService billingEntityBodySerice) {
		this.billingEntityBodyService = billingEntityBodySerice;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BillingEntityBody>> getAllBillingEntityBodies() {
		final List<BillingEntityBody> billingEntityBodies = billingEntityBodyService.findAllBillingEntities();
		return new ResponseEntity<>(billingEntityBodies, HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<BillingEntityBody> getBillingEntityBodyById(@PathVariable("id") Long id) {
		final BillingEntityBody billingEntityBody = billingEntityBodyService.findBillingEntityBodyById(id);
		return new ResponseEntity<>(billingEntityBody, HttpStatus.OK);
	}
	
}