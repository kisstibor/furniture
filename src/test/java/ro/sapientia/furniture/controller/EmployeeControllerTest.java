package ro.sapientia.furniture.controller;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import static org.mockito.Mockito.doNothing;
import ro.sapientia.furniture.model.Employee;
import ro.sapientia.furniture.repository.EmployeeRepository;
import ro.sapientia.furniture.service.EmployeeService;


import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = EmployeeController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class EmployeeControllerTest {

    private EmployeeRepository repositoryMock;

    private EmployeeService service;
    private List<Employee> employees2 = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(EmployeeRepository.class);
        service = new EmployeeService(repositoryMock);
        employees2.add( new Employee(
                1L,
                "Nagy",
                "Andor",
                20,
                "Worker"
        ));
        employees2.add( new Employee(
                2L,
                "Kiss",
                "Andrea",
                22,
                "Manager"
        ));
    }
    @Autowired
    private MockMvc mockMvc;

    @MockBean(EmployeeService.class)
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAllEmployeeShouldSucceed() throws Exception {
        when(employeeService.findAllEmployees()).thenReturn(employees2);

        mockMvc.perform(get("/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(employees2.size())));
    }

    @Test
    public void testFindEmployeeByIdShouldFail() throws Exception {
        when(employeeService.findEmployeeById(anyLong())).thenThrow(new NotFoundException(""));

        mockMvc.perform(get("/employee/find/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindEmployeeByIdShouldSucceed() throws Exception {
        when(employeeService.findEmployeeById(anyLong())).thenReturn(employees2.get(0));

        mockMvc.perform(get("/employee/find/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateEmployeeShouldSucceed() throws Exception {
        when(employeeService.create(any(Employee.class))).thenReturn(employees2.get(0));

        mockMvc.perform(post("/employee/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employees2.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", is(employees2.get(0).getFirst_name())))
                .andExpect(jsonPath("$.last_name", is(employees2.get(0).getLast_name())))
                .andExpect(jsonPath("$.age", is(employees2.get(0).getAge())))
                .andExpect(jsonPath("$.employment_type", is(employees2.get(0).getEmployment_type())));
    }

    @Test
    public void testUpdateEmployeeShouldSucceed() throws Exception {
        when(employeeService.update(any(Employee.class))).thenReturn(employees2.get(0));

        mockMvc.perform(put("/employee/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employees2.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", is(employees2.get(0).getFirst_name())))
                .andExpect(jsonPath("$.last_name", is(employees2.get(0).getLast_name())))
                .andExpect(jsonPath("$.age", is(employees2.get(0).getAge())))
                .andExpect(jsonPath("$.employment_type", is(employees2.get(0).getEmployment_type())));
    }

    @Test
    public void testDeleteEmployeeShouldSucceed() throws Exception {
        doNothing().when(employeeService).delete(anyLong());

        mockMvc.perform(delete("/employee/delete/{id}", 1L))
                .andExpect(status().isOk());
    }
}
