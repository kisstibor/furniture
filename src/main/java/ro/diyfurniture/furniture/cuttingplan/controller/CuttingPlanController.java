package ro.diyfurniture.furniture.cuttingplan.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.diyfurniture.furniture.cuttingplan.model.transmission.CuttingPlanRequest;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.CuttingPlanResponse;
import ro.diyfurniture.furniture.cuttingplan.service.CuttingPlanService;

@RestController
@RequestMapping("/cutting-plans")
public class CuttingPlanController {

	private final CuttingPlanService cuttingPlanService;

	public CuttingPlanController(final CuttingPlanService cuttingPlanService) {
		this.cuttingPlanService = cuttingPlanService;
	}

	@PostMapping
	public ResponseEntity<?> createCuttingPlan(@RequestBody CuttingPlanRequest request) {
		try {
			CuttingPlanResponse response = cuttingPlanService.generate(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST);
		}
	}
}
