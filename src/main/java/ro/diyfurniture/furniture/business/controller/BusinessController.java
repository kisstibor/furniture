package ro.diyfurniture.furniture.business.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ro.diyfurniture.furniture.business.model.BusinessAuthRequest;
import ro.diyfurniture.furniture.business.model.BusinessAuthResponse;
import ro.diyfurniture.furniture.business.model.CuttingServiceRequest;
import ro.diyfurniture.furniture.business.model.CuttingServiceResponse;
import ro.diyfurniture.furniture.business.model.ManufacturerResponse;
import ro.diyfurniture.furniture.business.model.SheetTypeRequest;
import ro.diyfurniture.furniture.business.model.SheetTexture;
import ro.diyfurniture.furniture.business.model.SheetTextureResponse;
import ro.diyfurniture.furniture.business.model.SheetTypeResponse;
import ro.diyfurniture.furniture.business.service.BusinessService;

@RestController
@RequestMapping("/business")
public class BusinessController {

	private final BusinessService businessService;

	public BusinessController(BusinessService businessService) {
		this.businessService = businessService;
	}

	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@RequestBody BusinessAuthRequest request) {
		try {
			BusinessAuthResponse response = businessService.register(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody BusinessAuthRequest request) {
		try {
			BusinessAuthResponse response = businessService.login(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/sheet-types")
	public ResponseEntity<List<SheetTypeResponse>> listSheetTypes(
		@RequestParam(value = "businessId", required = false) String businessId
	) {
		return new ResponseEntity<>(businessService.listSheetTypes(businessId), HttpStatus.OK);
	}

	@GetMapping("/manufacturers")
	public ResponseEntity<List<ManufacturerResponse>> listManufacturers() {
		return new ResponseEntity<>(businessService.listManufacturers(), HttpStatus.OK);
	}

	@PostMapping("/sheet-types")
	public ResponseEntity<?> createSheetType(@RequestBody SheetTypeRequest request) {
		try {
			SheetTypeResponse response = businessService.createSheetType(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/sheet-types/{id}")
	public ResponseEntity<?> deleteSheetType(@PathVariable("id") String id) {
		businessService.deleteSheetType(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/cutting-services")
	public ResponseEntity<List<CuttingServiceResponse>> listCuttingServices(
		@RequestParam(value = "businessId", required = false) String businessId
	) {
		return new ResponseEntity<>(businessService.listCuttingServices(businessId), HttpStatus.OK);
	}

	@PostMapping("/cutting-services")
	public ResponseEntity<?> createCuttingService(@RequestBody CuttingServiceRequest request) {
		try {
			CuttingServiceResponse response = businessService.createCuttingService(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/cutting-services/{id}")
	public ResponseEntity<?> deleteCuttingService(@PathVariable("id") String id) {
		businessService.deleteCuttingService(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/sheet-types/{id}/texture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadSheetTexture(
		@PathVariable("id") String sheetTypeId,
		@RequestParam("businessId") String businessId,
		@RequestParam("file") MultipartFile file
	) {
		try {
			SheetTextureResponse response = businessService.uploadSheetTexture(sheetTypeId, businessId, file);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/sheet-types/{id}/texture")
	public ResponseEntity<?> getSheetTexture(
		@PathVariable("id") String sheetTypeId,
		@RequestParam("businessId") String businessId
	) {
		try {
			SheetTexture texture = businessService.getSheetTexture(sheetTypeId, businessId);
			return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(texture.getContentType()))
				.body(texture.getData());
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST);
		}
	}
}
