package ro.sapientia.furniture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ro.sapientia.furniture.repository.FurniturePanelRepository;
import ro.sapientia.furniture.service.FurniturePanelService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ro.sapientia.furniture.util.FurniturePanelDatabaseBuilder.buildTestFurniturePanels;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
class FurniturePanelEETest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FurniturePanelService furniturePanelService;

    @Autowired
    FurniturePanelRepository furniturePanelRepository;

    @BeforeEach
    public void setUp() {
        furniturePanelRepository.saveAllAndFlush(buildTestFurniturePanels());
    }

    @AfterEach
    public void tearDown() {
        furniturePanelRepository.deleteAll();
    }

    @Test
    public void itShouldCheckIfGetFurniturePanelsSucceeds() throws Exception {
        mvc.perform(get("/furniturePanel/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].width", is(50)))
                .andExpect(jsonPath("$[0].height", is(80)))
                .andExpect(jsonPath("$[0].depth", is(90)));
    }


    @Test
    public void itShouldCheckIfGetFurniturePanelByIdSucceeds() throws Exception {
        final var existingFurniturePanelId = furniturePanelService.findAllFurniturePanels().get(0).getId();

        mvc.perform(get("/furniturePanel/find/" + existingFurniturePanelId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(existingFurniturePanelId.intValue())))
                .andExpect(jsonPath("width", is(50)))
                .andExpect(jsonPath("height", is(80)))
                .andExpect(jsonPath("depth", is(90)));
    }

    @Test
    public void itShouldCheckIfAddFurniturePanelSucceeds() throws Exception {
        mvc.perform(post("/furniturePanel/add")
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
    public void itShouldCheckIfUpdateFurniturePanelSucceeds() throws Exception {
        final var existingFurniturePanelId = furniturePanelService.findAllFurniturePanels().get(0).getId();

        mvc.perform(post("/furniturePanel/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": " + existingFurniturePanelId +
                        ", \"width\": 50,\n" +
                        "\"height\": 80,\n" +
                        "\"depth\": 90\n}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void itShouldCheckIfDeleteFurniturePanelSucceeds() throws Exception {
        final var existingFurniturePanel = furniturePanelService.findAllFurniturePanels().get(0).getId();

        mvc.perform(get("/furniturePanel/delete/" + existingFurniturePanel))
                .andExpect(status().isOk());
    }
}
