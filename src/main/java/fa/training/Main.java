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
    }
}