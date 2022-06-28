//package com.app.dao;
//
//import com.app.models.Employee;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.Query;
//import javax.transaction.Transactional;
//import java.util.List;
//
//
//@Component
//@Repository
//public class EmployeeDAO {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public EmployeeDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//
//    @Transactional
//    public List<Employee> index(String keyword) {
//        logger.debug("8");
//        Session session = sessionFactory.getCurrentSession();
//        Query query;
//        if (keyword != null) {
//            query = session.createQuery("SELECT p FROM Employee p WHERE LOWER( CONCAT(p.name, ' ', p.patronymic, ' ', p.surname, ' ', p.email)) " +
//                    "LIKE LOWER(concat('%', ?1, '%'))");
//            query.setParameter(1, keyword);
//        } else {
//            System.out.println("!!!!!!!");
//            query = session.createQuery("from Employee");
//        }
//        return query.getResultList();
//    }
//
//    @Transactional
//    public Employee show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Employee.class, id);
//    }
//
//    @Transactional
//    public void save(Employee person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(person);
//    }
//
//    @Transactional
//    public void update(int id, Employee updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//        Employee personToBeUpdated = session.get(Employee.class, id);
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setPatronymic(updatedPerson.getPatronymic());
//        personToBeUpdated.setSurname(updatedPerson.getSurname());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
//        personToBeUpdated.setSection(updatedPerson.getSection());
//        personToBeUpdated.setPost(updatedPerson.getPost());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Employee employee = new Employee();
//        employee.setId(id);
//        session.delete(employee);
//    }
//
//}
