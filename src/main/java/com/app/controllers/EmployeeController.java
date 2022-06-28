package com.app.controllers;

import antlr.debug.DebuggingParser;
//import com.app.dao.DepartmentDAO;
//import com.app.dao.EmployeeDAO;
//import com.app.dao.SectionDAO;
import com.app.models.Employee;
import com.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
//    private final EmployeeDAO employeeDAO;
    private final EmployeeService employeeService;
//    private final SectionDAO sectionDAO;
//    private final DepartmentDAO departmentDAO;

    @Autowired
    EmployeeController( EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) String keyword) {
        model.addAttribute("employees", employeeService.findEmployeeByKeyWord(keyword));
        model.addAttribute("keyword", keyword);
        return "employees/index";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee") Employee employee) {
        return "employees/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.findEmployeeById(id));
        return "employees/show";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult) {
        System.out.println(employee.toString());
        if (bindingResult.hasErrors())
            return "employees/new";
        employeeService.save(employee);
        return "redirect:/employees/" + employee.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeService.findEmployeeById(id));
//        model.addAttribute("departments",departmentDAO.index(""));
//        model.addAttribute("sections",sectionDAO.index(""));
        return "employees/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("employee") @Valid Employee employee,
                         @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employees/edit";
        }
        employeeService.update(id, employee);
        return "redirect:/employees/{id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
