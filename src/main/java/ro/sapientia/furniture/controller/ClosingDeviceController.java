package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.model.ClosingDevice;
import ro.sapientia.furniture.service.ClosingDeviceService;

@RestController
@RequestMapping("/closingDevice")
public class ClosingDeviceController {
	private final ClosingDeviceService closingDeviceService;

	public ClosingDeviceController(final ClosingDeviceService closingDeviceService) {
		this.closingDeviceService = closingDeviceService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<ClosingDevice>> getAllClosingDevices(){
		final List<ClosingDevice> closingDevices = closingDeviceService.findAllClosingDevices();
		return new ResponseEntity<>(closingDevices,HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<ClosingDevice> getClosingDeviceById(@PathVariable("id") Long id){
		final ClosingDevice closingDevice = closingDeviceService.findClosingDeviceById(id);
		if (closingDevice == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(closingDevice,HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ClosingDevice> addClosingDevice(@RequestBody ClosingDevice closingDevice){
		final ClosingDevice persistentClosingDevice = closingDeviceService.create(closingDevice);
		return new ResponseEntity<>(persistentClosingDevice,HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<ClosingDevice> updateClosingDevice(@RequestBody ClosingDevice closingDevice){
		final ClosingDevice persistentClosingDevice = closingDeviceService.update(closingDevice);
		return new ResponseEntity<>(persistentClosingDevice,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteClosingDeviceById(@PathVariable("id") Long id) {
		final ClosingDevice closingDevice = closingDeviceService.findClosingDeviceById(id);

		if (closingDevice == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		closingDeviceService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
