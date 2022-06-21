package com.app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import org.springframework.stereotype.Component;
import com.app.models.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Component
@Repository
//@EnableAutoConfiguration(exclude= {HibernateJpaAutoConfiguration.class})
public class EmployeeDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public List<Employee> index(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        if (keyword != null) {
            query = session.createQuery("SELECT p FROM Employee p WHERE LOWER( CONCAT(p.name, ' ', p.patronymic, ' ', p.surname, ' ', p.email)) " +
                    "LIKE LOWER(concat('%', ?1, '%'))");
            query.setParameter(1, keyword);
        } else {
            query = session.createQuery("from Employee");
        }
        return query.getResultList();
    }

    @Transactional
    public Employee show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Transactional
    public void save(Employee person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Employee updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Employee personToBeUpdated = session.get(Employee.class, id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setPatronymic(updatedPerson.getPatronymic());
        personToBeUpdated.setSurname(updatedPerson.getSurname());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = new Employee();
        employee.setId(id);
        session.delete(employee);
    }

}
