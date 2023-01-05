package ro.sapientia.furniture.service;

import ro.sapientia.furniture.model.Employee;
import ro.sapientia.furniture.repository.EmployeeRepository;
import ro.sapientia.furniture.repository.ManufacturerRepository;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public Employee findEmployeeById(final Long id) {
        return this.employeeRepository.findEmployeeById(id);
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.saveAndFlush(employee);
    }

    public Employee update(Employee employee) {
        return this.employeeRepository.saveAndFlush(employee);
    }

    public void delete(Long id) {
        this.employeeRepository.deleteById(id);
    }
}
