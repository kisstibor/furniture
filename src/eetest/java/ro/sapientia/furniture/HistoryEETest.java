package ro.sapientia.furniture;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ro.sapientia.furniture.controller.HistoryController;
import ro.sapientia.furniture.model.HistoryBody;
import ro.sapientia.furniture.repository.HistoryRepository;
import ro.sapientia.furniture.service.HistoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(HistoryController.class)
public class HistoryEETest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    @MockBean
    private HistoryRepository historyRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCreateRetrieveUpdateDelete() throws Exception {
        // Create a new HistoryBody
        HistoryBody newHistoryBody = new HistoryBody(1L,  "History 1");
        when(historyService.create(newHistoryBody)).thenReturn(newHistoryBody);

        // Send a POST request to create the HistoryBody
        mockMvc.perform(post("/history/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newHistoryBody)))
                .andExpect(status().isOk());

        // Send a GET request to retrieve the HistoryBody
        MvcResult result = mockMvc.perform(get("/history/all"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the HistoryBody was retrieved correctly
        List<HistoryBody> historyBodies = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<HistoryBody>>() {});
        assertEquals(1, historyBodies.size());
        assertEquals(newHistoryBody, historyBodies.get(0));

        // Update the HistoryBody
        HistoryBody updatedHistoryBody = new HistoryBody(1L, "Updated History 1");
        when(historyService.update(updatedHistoryBody)).thenReturn(updatedHistoryBody);

        // Send a POST request to update the HistoryBody
        mockMvc.perform(post("/history/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedHistoryBody)))
                .andExpect(status().isOk());

        // Send a GET request to retrieve the updated HistoryBody
        result = mockMvc.perform(get("/history/all"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the updated HistoryBody was retrieved correctly
        historyBodies = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<HistoryBody>>() {});
        assertEquals(1, historyBodies.size());
        assertEquals(updatedHistoryBody, historyBodies.get(0));

        // Delete the HistoryBody
        doNothing().when(historyService).delete(1L);

        // Send a DELETE request to delete the HistoryBody
        mockMvc.perform(delete("/history/1"))
                .andExpect(status().isOk());

        // Send a GET request to retrieve the deleted HistoryBody
        result = mockMvc.perform(get("/history/all"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the HistoryBody was deleted correctly
        historyBodies = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<HistoryBody>>() {});
        assertEquals(0, historyBodies.size());
    }
}

