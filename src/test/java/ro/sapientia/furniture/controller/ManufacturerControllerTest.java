package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import ro.sapientia.furniture.service.ManufacturerService;

import javax.ws.rs.InternalServerErrorException;
import java.util.Collections;

@WebMvcTest(controllers = ManufacturerController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ManufacturerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(ManufacturerService.class)
    private ManufacturerService manufacturerService;

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
    public void testFindManufacturerByIdShouldFailWithNotFoundException() throws Exception {
        when(manufacturerService.findManufacturerById(1L)).thenThrow(new NotFoundException(""));

        this.mockMvc.perform(get("/manufacturer/find/1")).andExpect(status().isNotFound());
    }
}
