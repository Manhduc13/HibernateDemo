package fa.training;

import fa.training.entities.Department;
import fa.training.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Get all departments with native query
        NativeQuery<Department> q = session.createNativeQuery("SELECT * FROM departments", Department.class);
        List<Department> departments = q.getResultList();
        departments.forEach(department -> {
            System.out.println(department.toString());
        });
//        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
//        Employee employee = Employee.builder()
//                .firstName("Duc")
//                .lastName("Manh")
//                .email("johnwick@doe.com")
//                .phoneNumber("0132456789")
//                .hireDate(LocalDateTime.now())
//                .salary(3000)
//                .dateOfBirth(LocalDateTime.now())
//                .insertedAt(ZonedDateTime.now())
//                .updatedAt(ZonedDateTime.now())
//                .deletedAt(ZonedDateTime.now())
//                .isActive(true)
//                .build();
//
//        employeeRepository.create(employee);
//        List<Employee> employees = employeeRepository.getAll();
//        employees.forEach(e -> {
//            System.out.println(e.toString());
//        });

    }
}