package ro.diyfurniture.furniture.project.service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ro.diyfurniture.furniture.project.model.ProjectSnapshot;
import ro.diyfurniture.furniture.project.model.transmission.ProjectLoadResponse;
import ro.diyfurniture.furniture.project.model.transmission.ProjectSaveRequest;
import ro.diyfurniture.furniture.project.model.transmission.ProjectSummaryResponse;
import ro.diyfurniture.furniture.project.repository.ProjectSnapshotRepository;

@Service
public class ProjectSnapshotService {

	private final ProjectSnapshotRepository projectSnapshotRepository;
	private final ObjectMapper objectMapper;

	public ProjectSnapshotService(ProjectSnapshotRepository projectSnapshotRepository, ObjectMapper objectMapper) {
		this.projectSnapshotRepository = projectSnapshotRepository;
		this.objectMapper = objectMapper;
	}

	public List<ProjectSummaryResponse> list(String ownerId) {
		validateOwnerId(ownerId);
		List<ProjectSnapshot> projects = projectSnapshotRepository.findByOwnerIdOrderByUpdatedAtDesc(ownerId.trim());
		List<ProjectSummaryResponse> result = new ArrayList<>();
		for (ProjectSnapshot project : projects) {
			result.add(toSummary(project));
		}
		return result;
	}

	public ProjectSummaryResponse save(ProjectSaveRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request is required.");
		}
		validateOwnerId(request.getOwnerId());
		if (request.getSnapshot() == null) {
			throw new IllegalArgumentException("Project snapshot is required.");
		}
		String normalizedName = normalizeName(request.getName());
		String ownerId = request.getOwnerId().trim();
		Instant now = Instant.now();

		ProjectSnapshot project = findExisting(ownerId, request.getProjectId())
			.orElseGet(ProjectSnapshot::new);

		if (project.getProjectId() == null) {
			project.setProjectId(createProjectId());
			project.setCreatedAt(now);
		}
		project.setOwnerId(ownerId);
		project.setName(normalizedName);
		project.setSnapshotJson(request.getSnapshot().toString());
		project.setUpdatedAt(now);

		return toSummary(projectSnapshotRepository.save(project));
	}

	public ProjectLoadResponse load(String ownerId, String projectId) {
		validateOwnerId(ownerId);
		if (isBlank(projectId)) {
			throw new IllegalArgumentException("projectId is required.");
		}

		ProjectSnapshot project = projectSnapshotRepository
			.findByOwnerIdAndProjectId(ownerId.trim(), projectId.trim())
			.orElseThrow(() -> new IllegalArgumentException("Project not found."));

		ProjectLoadResponse response = new ProjectLoadResponse();
		response.setId(project.getProjectId());
		response.setOwnerId(project.getOwnerId());
		response.setName(project.getName());
		response.setUpdatedAt(project.getUpdatedAt());
		response.setSnapshot(parseSnapshot(project.getSnapshotJson()));
		return response;
	}

	private Optional<ProjectSnapshot> findExisting(String ownerId, String projectId) {
		if (isBlank(projectId)) {
			return Optional.empty();
		}
		return projectSnapshotRepository.findByOwnerIdAndProjectId(ownerId, projectId.trim());
	}

	private ProjectSummaryResponse toSummary(ProjectSnapshot project) {
		ProjectSummaryResponse response = new ProjectSummaryResponse();
		response.setId(project.getProjectId());
		response.setOwnerId(project.getOwnerId());
		response.setName(project.getName());
		response.setUpdatedAt(project.getUpdatedAt());
		return response;
	}

	private JsonNode parseSnapshot(String snapshotJson) {
		try {
			return objectMapper.readTree(snapshotJson);
		} catch (IOException ex) {
			throw new IllegalStateException("Stored project snapshot is invalid JSON.", ex);
		}
	}

	private String normalizeName(String value) {
		if (isBlank(value)) {
			return "Untitled project";
		}
		String trimmed = value.trim();
		return trimmed.length() <= 255 ? trimmed : trimmed.substring(0, 255);
	}

	private void validateOwnerId(String ownerId) {
		if (isBlank(ownerId)) {
			throw new IllegalArgumentException("ownerId is required.");
		}
	}

	private String createProjectId() {
		return "p_" + UUID.randomUUID().toString().replace("-", "");
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
