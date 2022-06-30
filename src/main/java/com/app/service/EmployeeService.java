package com.app.service;

import com.app.models.Employee;
import com.app.models.Section;
import com.app.repository.DepartmentRepository;
import com.app.repository.EmployeeRepository;
import com.app.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SectionRepository sectionRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository userRepository, SectionRepository sectionRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public  List<Employee> findEmployeeByKeyWord(String keyword){
        return employeeRepository.findEmployeeByKeyWord(keyword);
    }

    public List<Employee> findAllBySection(Section section){
        return employeeRepository.findAllBySection(section);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void delete(long id){
        Employee oldEmployee = employeeRepository.findById(id).get();
        if (oldEmployee.getSection() == null || oldEmployee.getSection().getDirector() == null){
        } else {
            if (oldEmployee.getSection().getDirector().getId() == oldEmployee.getId()){
                oldEmployee.getSection().setDirector(null);
                sectionRepository.save(oldEmployee.getSection());
            }
        }
        if (oldEmployee.getDepartment() == null || oldEmployee.getDepartment().getDirector() == null){
        } else {
            if (oldEmployee.getDepartment().getDirector().getId() == oldEmployee.getId()){
                oldEmployee.getDepartment().setDirector(null);
                departmentRepository.save(oldEmployee.getDepartment());
            }
        }
        employeeRepository.deleteById(id);
    }
}
