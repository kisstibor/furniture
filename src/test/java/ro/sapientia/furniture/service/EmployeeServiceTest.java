package ro.sapientia.furniture.service;

import org.junit.jupiter.api.BeforeEach;
import ro.sapientia.furniture.repository.EmployeeRepository;

import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {
    private EmployeeRepository repositoryMock;

    private EmployeeService service;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(EmployeeRepository.class);
        service = new EmployeeService(repositoryMock);
    }
}
