package ro.diyfurniture.furniture.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.diyfurniture.furniture.project.model.transmission.ProjectLoadResponse;
import ro.diyfurniture.furniture.project.model.transmission.ProjectSaveRequest;
import ro.diyfurniture.furniture.project.model.transmission.ProjectSummaryResponse;
import ro.diyfurniture.furniture.project.service.ProjectSnapshotService;

@RestController
@RequestMapping("/api/projects")
public class ProjectSnapshotController {

	private final ProjectSnapshotService projectSnapshotService;

	public ProjectSnapshotController(ProjectSnapshotService projectSnapshotService) {
		this.projectSnapshotService = projectSnapshotService;
	}

	@GetMapping
	public ResponseEntity<?> list(@RequestParam("ownerId") String ownerId) {
		try {
			List<ProjectSummaryResponse> response = projectSnapshotService.list(ownerId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(
				Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST
			);
		}
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> load(
		@PathVariable("projectId") String projectId,
		@RequestParam("ownerId") String ownerId
	) {
		try {
			ProjectLoadResponse response = projectSnapshotService.load(ownerId, projectId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			HttpStatus status = "Project not found.".equals(ex.getMessage()) ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
			return new ResponseEntity<>(
				Map.of("error", "validation_error", "message", ex.getMessage()),
				status
			);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ProjectSaveRequest request) {
		try {
			ProjectSummaryResponse response = projectSnapshotService.save(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(
				Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST
			);
		}
	}
}
