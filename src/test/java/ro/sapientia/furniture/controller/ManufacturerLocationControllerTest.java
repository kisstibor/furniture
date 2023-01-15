package ro.sapientia.furniture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.sapientia.furniture.mock.DatabaseMock;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.service.ManufacturerLocationService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ManufacturerLocationController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ManufacturerLocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(ManufacturerLocationService.class)
    private ManufacturerLocationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAllManufacturerLocationsShouldSucceed() throws Exception {
        when(service.findAllManufacturerLocations()).thenReturn(DatabaseMock.manufacturerLocationsWithOneManufacturer);

        mockMvc.perform(get("/manufacturer-location/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(DatabaseMock.manufacturerLocationsWithOneManufacturer.size())))
                .andExpect(jsonPath("$[0].name", is(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0).getName())));
    }

    @Test
    public void testFindManufacturerLocationByIdShouldFail() throws Exception {
        when(service.findManufacturerLocationById(anyLong())).thenThrow(new NotFoundException(""));

        mockMvc.perform(get("/manufacturer-location/find/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindManufacturerLocationByIdShouldSucceed() throws Exception {
        when(service.findManufacturerLocationById(anyLong())).thenReturn(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0));

        mockMvc.perform(get("/manufacturer-location/find/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0).getName())))
                .andExpect(jsonPath("$.address", is(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0).getAddress())));
    }

    @Test
    public void testCreateManufacturerLocationShouldSucceed() throws Exception {
        when(service.create(any(ManufacturerLocation.class))).thenReturn(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0));

        mockMvc.perform(post("/manufacturer-location/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0).getName())))
                .andExpect(jsonPath("$.address", is(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0).getAddress())));
    }


    @Test
    public void testUpdateManufacturerLocationShouldSucceed() throws Exception {
        when(service.update(any(ManufacturerLocation.class))).thenReturn(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0));

        mockMvc.perform(put("/manufacturer-location/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0).getName())))
                .andExpect(jsonPath("$.address", is(DatabaseMock.manufacturerLocationsWithOneManufacturer.get(0).getAddress())));
    }

    @Test
    public void testDeleteManufacturerLocationShouldSucceed() throws Exception {
        doNothing().when(service).delete(anyLong());

        mockMvc.perform(delete("/manufacturer-location/delete/{id}", 1L))
                .andExpect(status().isOk());
    }

}
