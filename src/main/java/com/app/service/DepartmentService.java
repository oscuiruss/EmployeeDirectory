package com.app.service;

import com.app.models.Department;
import com.app.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department findDepartmentById(long id){
        return departmentRepository.findById(id).orElse(null);
    }

    public  List<Department> findDepartmentByKeyWord(String keyword){
        return departmentRepository.findDepartmentByKeyword(keyword);
    }

    public Department save(Department department){
        return departmentRepository.save(department);
    }

    public void delete(long id){
        departmentRepository.deleteById(id);
    }
}
