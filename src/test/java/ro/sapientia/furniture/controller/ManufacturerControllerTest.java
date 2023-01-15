package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.sapientia.furniture.mock.DatabaseMock.manufacturerListWithMultipleManufacturers;
import static ro.sapientia.furniture.mock.DatabaseMock.manufacturerListWithOneManufacturer;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.service.ManufacturerService;

import javax.ws.rs.InternalServerErrorException;
import java.util.Collections;

@WebMvcTest(controllers = ManufacturerController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ManufacturerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(ManufacturerService.class)
    private ManufacturerService manufacturerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAllManufacturersShouldSucceedWithEmptyList() throws Exception {
        when(manufacturerService.findAllManufacturers()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/manufacturer/find/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    public void testFindAllManufacturersShouldSucceedWithOneManufacturer() throws Exception {
        when(manufacturerService.findAllManufacturers()).thenReturn(manufacturerListWithOneManufacturer);

        this.mockMvc.perform(get("/manufacturer/find/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(manufacturerListWithOneManufacturer.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(manufacturerListWithOneManufacturer.get(0).getName())));
    }

    @Test
    public void testFindAllManufacturersShouldSucceedWithMultipleManufacturers() throws Exception {
        when(manufacturerService.findAllManufacturers()).thenReturn(manufacturerListWithMultipleManufacturers);

        this.mockMvc.perform(get("/manufacturer/find/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(manufacturerListWithMultipleManufacturers.get(0).getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(manufacturerListWithMultipleManufacturers.get(1).getId().intValue())))
                .andExpect(jsonPath("$[2].id", is(manufacturerListWithMultipleManufacturers.get(2).getId().intValue())))
                .andExpect(jsonPath("$[3].id", is(manufacturerListWithMultipleManufacturers.get(3).getId().intValue())))
                .andExpect(jsonPath("$[4].id", is(manufacturerListWithMultipleManufacturers.get(4).getId().intValue())))
                .andExpect(jsonPath("$[5].id", is(manufacturerListWithMultipleManufacturers.get(5).getId().intValue())))
                .andExpect(jsonPath("$.size()", is(manufacturerListWithMultipleManufacturers.size())));
    }

    @Test
    public void testFindAllManufacturersShouldFail() throws Exception {
        when(manufacturerService.findAllManufacturers()).thenThrow(new InternalServerErrorException());

        this.mockMvc.perform(get("/manufacturer/find/all")).andExpect(status().is5xxServerError());
    }

    @Test
    public void testFindManufacturerByIdShouldSucceed() throws Exception {
        when(manufacturerService.findManufacturerById(manufacturerListWithOneManufacturer.get(0).getId()))
                .thenReturn(manufacturerListWithOneManufacturer.get(0));

        this.mockMvc.perform(get("/manufacturer/find/" + manufacturerListWithOneManufacturer.get(0).getId().intValue()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(manufacturerListWithOneManufacturer.get(0).getId().intValue())));
    }

    @Test
    public void testFindManufacturerByIdShouldFailWithNotFoundException() throws Exception {
        when(manufacturerService.findManufacturerById(anyLong())).thenThrow(new NotFoundException(""));

        this.mockMvc.perform(get("/manufacturer/find/1")).andExpect(status().isNotFound());
    }

    @Test
    public void testCreateManufacturerShouldSucceed() throws Exception {
        when(manufacturerService.create(any(Manufacturer.class))).thenReturn(manufacturerListWithOneManufacturer.get(0));

        this.mockMvc.perform(post("/manufacturer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(manufacturerListWithOneManufacturer.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(manufacturerListWithOneManufacturer.get(0).getId().intValue())));
    }

    @Test
    public void testDeleteManufacturerByIdShouldSucceed() throws Exception {
        this.mockMvc.perform(delete("/manufacturer/delete/" + manufacturerListWithOneManufacturer.get(0).getId().intValue()))
                .andExpect(status().isOk());
    }
}
