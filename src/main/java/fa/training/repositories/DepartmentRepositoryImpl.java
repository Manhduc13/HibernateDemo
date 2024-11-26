package fa.training.repositories;

import fa.training.entities.Department;
import fa.training.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final SessionFactory sessionFactory;

    public DepartmentRepositoryImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Department> getAll() {
        Session session = sessionFactory.openSession();
        // Get all departments with native query
        NativeQuery<Department> q = session.createNativeQuery("SELECT * FROM departments", Department.class);
        return q.getResultList();
    }

    @Override
    public Department getById(int id) {
        Session session = sessionFactory.openSession();
        NativeQuery<Department> q = session.createNativeQuery("SELECT * FROM departments WHERE id=:id", Department.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public boolean create(Department department) {
        Transaction tx = null;
        boolean result = false;
        try (Session session = sessionFactory.openSession();) {
            tx = session.beginTransaction();
            NativeQuery<Department> q = session.createNativeQuery("INSERT INTO departments (name, description) VALUES (:name, :description)", Department.class);
            q.setParameter("name", department.getName());
            q.setParameter("description", department.getDescription());
            q.executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Department department) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession();) {
            tx = session.beginTransaction();
            NativeQuery<Department> q = session.createNativeQuery("UPDATE departments SET name=:name, description=:description WHERE id=:id", Department.class);
            q.setParameter("name", department.getName());
            q.setParameter("description", department.getDescription());
            q.setParameter("id", department.getId());
            q.executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession();) {
            tx = session.beginTransaction();
            NativeQuery<Department> q = session.createNativeQuery("DELETE FROM departments WHERE id=:id", Department.class);
            q.setParameter("id", id);
            q.executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}

