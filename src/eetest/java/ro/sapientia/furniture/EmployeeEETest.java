package ro.sapientia.furniture;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ro.sapientia.furniture.model.*;
import ro.sapientia.furniture.repository.EmployeeRepository;
import ro.sapientia.furniture.service.EmployeeService;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeEETest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Employee> employeeListWithOneEmployee = new ArrayList<Employee>(Arrays.asList(
            new Employee(1L, "Nagy", "Andor", 20, "Worker")
    ));
    private List<Employee> employeeListWithMultipleEmployees = new ArrayList<Employee>(Arrays.asList(
            new Employee(1L, "Nagy", "Andor", 20, "Worker"),
            new Employee(2L, "Kiss", "Erika", 22, "Worker"),
            new Employee(3L, "Bartha", "Adam", 30, "Manager"),
            new Employee(4L, "Feher", "Andrea", 20, "Worker")
    ));

    @BeforeEach
    public void setUp() {
        entityManager.clear();
        entityManager.flush();
    }

    @Test
    void testFindAllEmployeesWithEmptyListShouldSucceed() throws Exception {
        mvc.perform(get("/employee/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    void testFindAllEmployeesWithOneEmployeeShouldSucceed() throws Exception {
        entityManager.persist(entityManager.merge(employeeListWithOneEmployee.get(0)));
        mvc.perform(get("/employee/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(employeeListWithOneEmployee.size())));
    }

    @Test
    void testFindAllEmployeesWithMultipleEmployeesShouldSucceed() throws Exception {
        employeeListWithMultipleEmployees.forEach(employee -> {
            entityManager.persist(entityManager.merge(employee));
        });
        mvc.perform(get("/employee/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(employeeListWithMultipleEmployees.size())));
    }

    @Test
    public void testFindEmployeesByIdShouldSucceed() throws Exception {
        entityManager.persist(entityManager.merge(employeeListWithOneEmployee.get(0)));
        mvc.perform(get("/employee/find/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", is(employeeListWithOneEmployee.get(0).getFirst_name())))
                .andExpect(jsonPath("$.last_name", is(employeeListWithOneEmployee.get(0).getLast_name())))
                .andExpect(jsonPath("$.age", is(employeeListWithOneEmployee.get(0).getAge())))
                .andExpect(jsonPath("$.employment_type", is(employeeListWithOneEmployee.get(0).getEmployment_type())));
    }

    @Test
    public void testCreateEmployeeShouldSucceed() throws Exception {
        entityManager.persist(entityManager.merge(employeeListWithOneEmployee.get(0)));
        mvc.perform(post("/employee/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeListWithOneEmployee.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", is(employeeListWithOneEmployee.get(0).getFirst_name())))
                .andExpect(jsonPath("$.last_name", is(employeeListWithOneEmployee.get(0).getLast_name())))
                .andExpect(jsonPath("$.age", is(employeeListWithOneEmployee.get(0).getAge())))
                .andExpect(jsonPath("$.employment_type", is(employeeListWithOneEmployee.get(0).getEmployment_type())));
    }

    @Test
    public void testDeleteEmployeeByIdShouldSucceed() throws Exception {
        entityManager.persist(entityManager.merge(employeeListWithOneEmployee.get(0)));
        mvc.perform(delete("/employee/delete/{id}", 1L))
                .andExpect(status().isOk());
    }
}
