package com.app.repository;

import com.app.models.Department;
import com.app.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAll();

    Department findDepartmentById(int id);


    @Query(value = "SELECT * FROM Department WHERE lower(name)"
            + "LIKE lower(concat('%', ?1, '%'))",
            nativeQuery = true)
    List<Department> findDepartmentByKeyword(String keyword);
}
