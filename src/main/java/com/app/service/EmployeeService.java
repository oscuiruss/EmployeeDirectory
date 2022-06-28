package com.app.service;

import com.app.models.Employee;
import com.app.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository userRepository) {
        this.employeeRepository = userRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(int id){
        return employeeRepository.findEmployeeById(id);
    }

    public  List<Employee> findEmployeeByKeyWord(String keyword){
        return employeeRepository.findEmployeeByKeyWord(keyword);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void update(long id, Employee employee){
        employeeRepository.update(id, employee.getName(), employee.getPatronymic(),
                employee.getSurname(), employee.getEmail(), employee.getPost(), employee.getSection());
    }

    public void delete(long id){
        employeeRepository.deleteById(id);
    }
}
