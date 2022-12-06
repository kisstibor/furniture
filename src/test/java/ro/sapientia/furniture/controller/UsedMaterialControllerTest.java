package ro.sapientia.furniture.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import ro.sapientia.furniture.model.UsedMaterial;
import ro.sapientia.furniture.model.dto.UsedMaterialRequest;
import ro.sapientia.furniture.service.UsedMaterialService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@WebMvcTest(controllers = UsedMaterialController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class UsedMaterialControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean(UsedMaterialService.class)
    private UsedMaterialService usedMaterialService;

    @Test
    public void itShouldGetTwoUsedMaterials() throws Exception {
        final UsedMaterial usedMaterial1 = new UsedMaterial();
        final UsedMaterial usedMaterial2 = new UsedMaterial();

        when(usedMaterialService.findAll()).thenReturn(List.of(usedMaterial1, usedMaterial2));

        this.mockMvc.perform(get("/used_materials"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void itShouldGetOneUsedMaterialById() throws Exception {
        final UsedMaterial usedMaterial = new UsedMaterial();
        usedMaterial.setId(1L);

        when(usedMaterialService.findById(anyLong())).thenReturn(usedMaterial);

        this.mockMvc.perform(get("/used_materials/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void itShouldGet404ErrorForNonExistentUsedMaterial() throws Exception {
        when(usedMaterialService.findById(anyLong())).thenReturn(null);

        this.mockMvc.perform(get("/used_materials/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void itShouldCreateOneUsedMaterial() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final UsedMaterialRequest usedMaterialRequest = new UsedMaterialRequest(1L, 1L, 1L, 0, null, null);
        final UsedMaterial usedMaterial = new UsedMaterial();
        usedMaterial.setId(1L);

        when(usedMaterialService.create(any(UsedMaterialRequest.class))).thenReturn(usedMaterial);

        this.mockMvc.perform(
                        post("/used_materials/create")
                                .content(objectMapper.writeValueAsString(usedMaterialRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        verify(usedMaterialService, times(1)).create(any(UsedMaterialRequest.class));
    }

    @Test
    public void itShouldUpdateOneUsedMaterial() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final UsedMaterialRequest usedMaterialRequest = new UsedMaterialRequest(1L, 1L, 1L, 0, null, null);
        final BigDecimal price = new BigDecimal(15.3);
        final UsedMaterial usedMaterial = new UsedMaterial();
        usedMaterial.setId(1L);
        usedMaterial.setPrice(price);

        when(usedMaterialService.update(any(UsedMaterialRequest.class))).thenReturn(usedMaterial);

        this.mockMvc.perform(
                        post("/used_materials/update")
                                .content(objectMapper.writeValueAsString(usedMaterialRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.price", is(price)));

        verify(usedMaterialService, times(1)).update(any(UsedMaterialRequest.class));
    }

    @Test
    public void itShouldDeleteOneUsedMaterial() throws Exception {
        this.mockMvc.perform(delete("/used_materials/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(usedMaterialService, times(1)).delete(anyLong());
    }
}
