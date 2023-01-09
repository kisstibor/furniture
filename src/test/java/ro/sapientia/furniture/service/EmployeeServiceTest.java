package ro.sapientia.furniture.service;

import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.Employee;
import ro.sapientia.furniture.repository.EmployeeRepository;
import ro.sapientia.furniture.util.StatusMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class EmployeeServiceTest {
    private EmployeeRepository repositoryMock;

    private EmployeeService service;
    private List<Employee> employeeListWithOneEmployee = new ArrayList<Employee>(Arrays.asList(
            new Employee(1L, "Nagy", "Andor", 20, "Worker")
    ));
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

    @Test
    public void testFindAllEmployees_emptyList() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<Employee> employees = service.findAllEmployees();

        assertEquals(0, employees.size());
    }

    @Test
    public void testFindAllEmployees_listWithOneEmployees() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<Employee> employees = service.findAllEmployees();

        assertEquals(1, employeeListWithOneEmployee.size());
    }

    @Test
    public void testFindAllEmployees_listWithTwoEmployees() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<Employee> employees = service.findAllEmployees();

        assertEquals(2, employees2.size());
    }

    @Test
    public void testFindAllEmployees_null() {
        when(repositoryMock.findAll()).thenReturn(null);
        final List<Employee> employees =  service.findAllEmployees();

        assertNull(employees);
    }

    @Test
    public void testFindEmployeeById() {
        // when
        when(repositoryMock.findEmployeeById(anyLong())).thenReturn(employees2.get(0));
        Employee employee = service.findEmployeeById(employees2.get(0).getId());

        // then
        assertNotNull(employee);
        assertEquals(employees2.get(0), employee);
    }

    @Test
    public void testFindEmployeeByIdShouldFail() {
        when(repositoryMock.findEmployeeById(anyLong())).thenReturn(null);
        NotFoundException thrownException = Assertions.assertThrows(NotFoundException.class, () ->{
            service.findEmployeeById(1L);
        });
        Assertions.assertEquals(
                StatusMessage.NOT_FOUND,
                thrownException.getMessage()
        );
    }

    @Test
    public void testCreateEmployee() {
        when(repositoryMock.saveAndFlush(employees2.get(0)))
                .thenReturn(employees2.get(0));

        service.create(employees2.get(0));

        verify(repositoryMock, times(1)).saveAndFlush(any());
    }

    @Test
    public void testUpdateEmployee() {
        // given
        Employee employee = employees2.get(0);
        employee.setFirst_name("NewFirstName");

        // when
        when(repositoryMock.saveAndFlush(any(Employee.class))).thenReturn(employees2.get(0));
        Employee employee1 = service.update(employee);

        // then
        assertEquals(employee1.getFirst_name(), employee.getFirst_name());
    }

    @Test
    public void testDeleteEmployee() {
        // when
        doNothing().when(repositoryMock).deleteById(anyLong());
        service.delete(1L);

        // then
        verify(repositoryMock, times(1)).deleteById(1L);
    }
}
