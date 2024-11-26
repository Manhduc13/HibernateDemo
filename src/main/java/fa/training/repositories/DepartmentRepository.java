package fa.training.repositories;

import fa.training.entities.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> getAll();

    Department getById(int id);

    boolean create(Department department);

    boolean update(Department department);

    boolean delete(int id);
}
