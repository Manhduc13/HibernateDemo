package fa.training.repositories;

import fa.training.entities.Employee;
import fa.training.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private SessionFactory sessionFactory;

    public EmployeeRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.openSession();

        // Get all departments with native query
        var query = session.createNativeQuery("SELECT * FROM employees",
                Employee.class);

        var employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee getById(int id) {
        Session session = sessionFactory.openSession();

        var query = session.createNativeQuery("SELECT * FROM employees WHERE id = :id",
                Employee.class);

        query.setParameter("id", id);

        Employee employee = query.getSingleResult();

        return employee;
    }

    @Override
    public boolean create(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Create using native query
            session.persist(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Delete using native query
            NativeQuery<Employee> query = session.createNativeQuery("DELETE FROM employees WHERE id = :id");
            query.setParameter("id", id);
            var result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}
