package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.model.ConnectionTool;
import ro.sapientia.furniture.service.ConnectionToolService;

@RestController
@RequestMapping("/connectiontool")
public class ConnectionToolController {
	private final ConnectionToolService connectionToolService;

	public ConnectionToolController(final ConnectionToolService connectionToolService){
		this.connectionToolService = connectionToolService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<ConnectionTool>> getAllConnectionTools(){
		final List<ConnectionTool> connectionTools = connectionToolService.findAllConnectionTools();
		return new ResponseEntity<>(connectionTools, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<ConnectionTool> getConnectionToolById(@PathVariable("id") Long id){
		final ConnectionTool persistenConnectionTool = connectionToolService.findConnectionToolById(id);
		return new ResponseEntity<>(persistenConnectionTool, HttpStatus.OK);
	}

	@GetMapping("/find/size/{size}")
	public ResponseEntity<List<ConnectionTool>> getConnectionToolBySize(@PathVariable("size") int size){
		final List<ConnectionTool> persistenConnectionTools = connectionToolService.findConnectionToolsBySize(size);
		return new ResponseEntity<>(persistenConnectionTools, HttpStatus.OK);
	}

	@GetMapping("/find/type/{type}")
	public ResponseEntity<List<ConnectionTool>> getConnectionToolByType(@PathVariable("type") String type){
		final List<ConnectionTool> persistenConnectionTools = connectionToolService.findConnectionToolsByType(type);
		return new ResponseEntity<>(persistenConnectionTools, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ConnectionTool> addConnectionTool(@RequestBody ConnectionTool connectionTool){
		final ConnectionTool persistenConnectionTool = connectionToolService.create(connectionTool);
		return new ResponseEntity<>(persistenConnectionTool, HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<ConnectionTool> updateConnectionTool(@RequestBody ConnectionTool connectionTool){
		final ConnectionTool persistenConnectionTool = connectionToolService.update(connectionTool);
		return new ResponseEntity<>(persistenConnectionTool, HttpStatus.OK);
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deleteConnectionToolById(@PathVariable("id") Long id){
		connectionToolService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
