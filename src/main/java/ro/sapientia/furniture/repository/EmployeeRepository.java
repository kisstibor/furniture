package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeById(Long id);
}