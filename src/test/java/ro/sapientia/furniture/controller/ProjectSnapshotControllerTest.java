package ro.sapientia.furniture.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ro.diyfurniture.furniture.FurnitureApplication;
import ro.diyfurniture.furniture.project.controller.ProjectSnapshotController;
import ro.diyfurniture.furniture.project.model.transmission.ProjectLoadResponse;
import ro.diyfurniture.furniture.project.model.transmission.ProjectSaveRequest;
import ro.diyfurniture.furniture.project.model.transmission.ProjectSummaryResponse;
import ro.diyfurniture.furniture.project.service.ProjectSnapshotService;

@WebMvcTest(controllers = ProjectSnapshotController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
@ContextConfiguration(classes = FurnitureApplication.class)
public class ProjectSnapshotControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ProjectSnapshotService projectSnapshotService;

	@Test
	public void listShouldReturnProjects() throws Exception {
		ProjectSummaryResponse item = new ProjectSummaryResponse();
		item.setId("p_1");
		item.setOwnerId("u_1");
		item.setName("Kitchen");
		item.setUpdatedAt(Instant.parse("2026-01-01T10:00:00Z"));

		when(projectSnapshotService.list(eq("u_1"))).thenReturn(List.of(item));

		mockMvc.perform(get("/api/projects").param("ownerId", "u_1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id").value("p_1"))
			.andExpect(jsonPath("$[0].name").value("Kitchen"));
	}

	@Test
	public void saveShouldReturnSummary() throws Exception {
		ProjectSaveRequest request = new ProjectSaveRequest();
		request.setOwnerId("u_1");
		request.setName("Project");
		ObjectNode snapshot = JsonNodeFactory.instance.objectNode();
		snapshot.put("version", 1);
		request.setSnapshot(snapshot);

		ProjectSummaryResponse response = new ProjectSummaryResponse();
		response.setId("p_42");
		response.setOwnerId("u_1");
		response.setName("Project");
		response.setUpdatedAt(Instant.now());

		when(projectSnapshotService.save(any(ProjectSaveRequest.class))).thenReturn(response);

		mockMvc.perform(
			post("/api/projects/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
		)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value("p_42"))
			.andExpect(jsonPath("$.ownerId").value("u_1"));
	}

	@Test
	public void loadShouldReturnSnapshot() throws Exception {
		ProjectLoadResponse response = new ProjectLoadResponse();
		response.setId("p_1");
		response.setOwnerId("u_1");
		response.setName("Kitchen");
		response.setUpdatedAt(Instant.now());
		ObjectNode snapshot = JsonNodeFactory.instance.objectNode();
		snapshot.put("bodies", 2);
		response.setSnapshot(snapshot);

		when(projectSnapshotService.load(eq("u_1"), eq("p_1"))).thenReturn(response);

		mockMvc.perform(get("/api/projects/p_1").param("ownerId", "u_1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value("p_1"))
			.andExpect(jsonPath("$.snapshot.bodies").value(2));
	}
}
