package com.app.repository;

import com.app.models.Department;
import com.app.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findAll();

    Section findSectionById(long id);

    List<Section> findSectionByDepartment(Department department);

    @Query(value = "SELECT * FROM Section WHERE lower(name)"
            + "LIKE lower(concat('%', ?1, '%'))",
            nativeQuery = true)
    List<Section> findSectionByKeyword(String keyword);
}
