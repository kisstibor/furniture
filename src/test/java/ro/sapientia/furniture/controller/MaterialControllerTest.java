package ro.sapientia.furniture.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ro.sapientia.furniture.model.Material;

import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import ro.sapientia.furniture.service.MaterialService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MaterialController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(MaterialService.class)
    private MaterialService materialService;

    @Test
    public void getAllMaterialsReturnMaterials() throws Exception {
        final Material body = new Material();
        body.setPrice(15.0);
        when(materialService.findAllMaterials()).thenReturn(List.of(body));

        this.mockMvc.perform(get("/material/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].price", is(15.0)));
    }

    @Test
    public void getMaterialByIdReturnMaterial() throws Exception {
        final Material body = new Material();
        body.setPrice(10.0);
        body.setId(1L);
        when(materialService.findMaterialById(1L)).thenReturn(body);

        this.mockMvc.perform(get("/material/find/1")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price", is(10.0)));
    }

    @Test
    public void addMaterialReturnMaterial() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Material body = new Material();
        body.setPrice(10.0);
        when(materialService.create(any(Material.class))).thenReturn(body);

        this.mockMvc.perform(post("/material/add")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.price", is(10.0)))
                .andExpect(content().json("{}"));

        verify(materialService, times(1)).create(any(Material.class));
    }


    @Test
    public void shouldUpdateMaterialWorkCorrectly() throws Exception{
        final ObjectMapper objectMapper = new ObjectMapper();
        final Material body = new Material();
        body.setPrice(10.0);
        when(materialService.create(any(Material.class))).thenReturn(body);

        this.mockMvc.perform(post("/material/update")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldDeleteMaterialByIdSucceed() throws Exception{
        this.mockMvc.perform(delete("/material/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
