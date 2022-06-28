//package com.app.dao;
//
//
//import com.app.models.Section;
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
//
//@Component
//@Repository
//public class SectionDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public SectionDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public List<Section> index(String keyword) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query;
//        if (keyword != null) {
//            query = session.createQuery("SELECT p FROM Section p WHERE LOWER(p.name)" +
//                    "LIKE LOWER(concat('%', ?1, '%'))");
//            query.setParameter(1, keyword);
//        } else {
//            query = session.createQuery("from Section");
//        }
//        return query.getResultList();
//    }
//
//    @Transactional
//    public Section show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Section.class, id);
//    }
//
//    @Transactional
//    public void save(Section section) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(section);
//    }
//
//    @Transactional
//    public void update(int id, Section updatedSection) {
//        Session session = sessionFactory.getCurrentSession();
//        Section sectionToBeUpdated = session.get(Section.class, id);
//        sectionToBeUpdated.setName(updatedSection.getName());
//        sectionToBeUpdated.setDepartment(updatedSection.getDepartment());
//        sectionToBeUpdated.setDirector(updatedSection.getDirector());
//        sectionToBeUpdated.setEmployees(updatedSection.getEmployees());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Section section = new Section();
//        section.setId(id);
//        session.delete(section);
//    }
//}
