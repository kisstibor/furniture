package ro.sapientia.furniture.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.sapientia.furniture.model.FurniturePanel;
import ro.sapientia.furniture.service.FurniturePanelService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ro.sapientia.furniture.util.FurniturePanelDatabaseBuilder.buildTestFurniturePanel;
import static ro.sapientia.furniture.util.FurniturePanelDatabaseBuilder.buildTestFurniturePanels;

@WebMvcTest(controllers = FurniturePanelController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class FurniturePanelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(FurniturePanelService.class)
    private FurniturePanelService furniturePanelService;

    @Test
    public void itShouldCheckIfGetAllFurniturePanelsExecutes() throws Exception {
        List<FurniturePanel> furniturePanel = buildTestFurniturePanels();
        when(furniturePanelService.findAllFurniturePanels()).thenReturn(furniturePanel);

        mockMvc.perform(get("/furniturePanel/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].width", is(50)))
                .andExpect(jsonPath("$[0].height", is(80)))
                .andExpect(jsonPath("$[0].depth", is(90)))
                .andExpect(jsonPath("$[1].width", is(150)))
                .andExpect(jsonPath("$[1].height", is(180)))
                .andExpect(jsonPath("$[1].depth", is(190)));
    }

    @Test
    public void itShouldCheckIfGetFurniturePanelByIdExecutes() throws Exception {
        FurniturePanel furniturePanel = buildTestFurniturePanel();

        when(furniturePanelService.findFurniturePanelById(anyLong())).thenReturn(furniturePanel);

        mockMvc.perform(get("/furniturePanel/find/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("width", is(50)))
                .andExpect(jsonPath("height", is(80)))
                .andExpect(jsonPath("depth", is(90)));
    }

    @Test
    public void itShouldCheckIfAddFurniturePanelExecutes() throws Exception {
        FurniturePanel furniturePanel = buildTestFurniturePanel();

        when(furniturePanelService.create(any(FurniturePanel.class))).thenReturn(furniturePanel);

        mockMvc.perform(post("/furniturePanel/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"width\": 50,\n" +
                        "\"height\": 80,\n" +
                        "\"depth\": 90\n}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("width", is(50)))
                .andExpect(jsonPath("height", is(80)))
                .andExpect(jsonPath("depth", is(90)));
    }

    @Test
    public void itShouldCheckIfUpdateFurniturePanelExecutes() throws Exception {
        mockMvc.perform(post("/furniturePanel/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1,\n" +
                        "\"width\": 50,\n" +
                        "\"height\": 80,\n" +
                        "\"depth\": 90\n}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void itShouldCheckIfDeleteFurniturePanelExecutes() throws Exception {
        mockMvc.perform(get("/furniturePanel/delete/1"))
                .andExpect(status().isOk());
    }
}
