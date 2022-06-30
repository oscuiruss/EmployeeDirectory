package com.app.service;

import com.app.models.Department;
import com.app.models.Employee;
import com.app.models.Section;
import com.app.repository.EmployeeRepository;
import com.app.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<Section> findAll(){
        return sectionRepository.findAll();
    }

    public Section findSectionById(long id){
        return sectionRepository.findById(id).orElse(null);
    }

    public  List<Section> findSectionByKeyword(String keyword){
        return sectionRepository.findSectionByKeyword(keyword);
    }

    public  List<Section> findSectionByDepartment(Department department){
        return sectionRepository.findSectionByDepartment(department);
    }

    public Section save(Section section){
        return sectionRepository.save(section);
    }

    public void delete(long id){
        sectionRepository.deleteById(id);
    }
}
