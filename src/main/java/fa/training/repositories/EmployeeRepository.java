package fa.training.repositories;

import fa.training.entities.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();

    Employee getById(int id);

    boolean create(Employee employee);

    boolean update(Employee employee);

    boolean delete(int id);
}
