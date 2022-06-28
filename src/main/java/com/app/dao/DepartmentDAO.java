//package com.app.dao;
//
//import com.app.models.Department;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.Query;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Component
//@Repository
//public class DepartmentDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public DepartmentDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public List<Department> index(String keyword) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query;
//        if (keyword != null) {
//            query = session.createQuery("SELECT p FROM Department p WHERE LOWER(p.name)" +
//                    "LIKE LOWER(concat('%', ?1, '%'))");
//            query.setParameter(1, keyword);
//        } else {
//            query = session.createQuery("from Department");
//        }
//        return query.getResultList();
//    }
//
//    @Transactional
//    public Department show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Department.class, id);
//    }
//
//    @Transactional
//    public void save(Department department) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(department);
//    }
//
//    @Transactional
//    public void update(int id, Department updatedDepartment) {
//        Session session = sessionFactory.getCurrentSession();
//        Department departmentToBeUpdated = session.get(Department.class, id);
//        departmentToBeUpdated.setName(updatedDepartment.getName());
//        departmentToBeUpdated.setDirector(updatedDepartment.getDirector());
//        departmentToBeUpdated.setSections(updatedDepartment.getSections());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Department department= new Department();
//        department.setId(id);
//        session.delete(department);
//    }
//}
