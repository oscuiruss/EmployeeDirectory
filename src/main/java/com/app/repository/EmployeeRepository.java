package com.app.repository;

import com.app.models.Employee;
import com.app.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAll();

    Employee findEmployeeById(int id);

    @Query(value = "SELECT * FROM employee WHERE lower(concat(name, ' ', patronymic, ' ', surname, ' ', email))"
            + "LIKE lower(concat('%', ?1, '%'))",
            nativeQuery = true)
    List<Employee> findEmployeeByKeyWord(String keyword);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET name=?2, patronymic=?3, surname=?4, email=?5, post=?6, section=?7 WHERE id=?1", nativeQuery = true)
    void update(long id, String name, String patronymic, String surname, String email, String post, Section section);
}
