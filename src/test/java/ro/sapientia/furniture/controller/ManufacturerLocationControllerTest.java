package ro.sapientia.furniture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.model.Stock;
import ro.sapientia.furniture.service.ManufacturerLocationService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ManufacturerLocationController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ManufacturerLocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(ManufacturerLocationService.class)
    private ManufacturerLocationService service;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Manufacturer> manufacturers = new ArrayList<>();
    private List<ManufacturerLocation> locations = new ArrayList<>();
    private List<Stock> stocks = new ArrayList<>();
    private List<Schedule> schedules = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        manufacturers.add( new Manufacturer(
                1L,
                "manufacturer1"
        ));
        manufacturers.add( new Manufacturer(
                2L,
                "manufacturer2"
        ));
        locations.add( new ManufacturerLocation(
                1L,
                "manufacturerLocation1",
                "address1",
                manufacturers.get(0),
                new HashSet<>(),
                new HashSet<>()
        ));
        locations.add( new ManufacturerLocation(
                2L,
                "manufacturerLocation2",
                "address2",
                manufacturers.get(1),
                new HashSet<>(),
                new HashSet<>()
        ));
    }

    @Test
    public void testFindAllManufacturerLocationsShouldSucceed() throws Exception {
        when(service.findAllManufacturerLocations()).thenReturn(locations);

        mockMvc.perform(get("/manufacturer-location/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(locations.size())));
    }

    @Test
    public void testFindManufacturerLocationByIdShouldSucceed() throws Exception {
        when(service.findManufacturerLocationById(anyLong())).thenReturn(locations.get(0));

        mockMvc.perform(get("/manufacturer-location/find/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(locations.get(0).getName())))
                .andExpect(jsonPath("$.address", is(locations.get(0).getAddress())));
    }

    @Test
    public void testCreateManufacturerLocationShouldSucceed() throws Exception {
        when(service.create(any(ManufacturerLocation.class))).thenReturn(locations.get(0));

        ObjectMapper ObjectMapper = new ObjectMapper();
        mockMvc.perform(post("/manufacturer-location/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper.writeValueAsString(locations.get(0))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(locations.get(0).getName())))
                .andExpect(jsonPath("$.address", is(locations.get(0).getAddress())));
    }


    @Test
    public void testUpdateManufacturerLocationShouldSucceed() throws Exception {
        when(service.update(any(ManufacturerLocation.class))).thenReturn(locations.get(0));

        ObjectMapper ObjectMapper = new ObjectMapper();
        mockMvc.perform(put("/manufacturer-location/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ObjectMapper.writeValueAsString(locations.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(locations.get(0).getName())))
                .andExpect(jsonPath("$.address", is(locations.get(0).getAddress())));
    }

    @Test
    public void testDeleteManufacturerLocationShouldSucceed() throws Exception {
        doNothing().when(service).delete(anyLong());

        mockMvc.perform(delete("/manufacturer-location/delete/{id}", 1L))
                .andExpect(status().isOk());
    }

}
