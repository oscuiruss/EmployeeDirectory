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

    List<Employee> findAllBySection(Section section);

    Employee findEmployeeById(long id);

    @Query(value = "SELECT * FROM Employee WHERE lower(concat(name, ' ', patronymic, ' ', surname, ' ', email))"
            + "LIKE lower(concat('%', ?1, '%'))",
            nativeQuery = true)
    List<Employee> findEmployeeByKeyWord(String keyword);
}
