package com.app.controllers;

//import com.app.dao.DepartmentDAO;
//import com.app.dao.EmployeeDAO;
//import com.app.dao.SectionDAO;
import com.app.models.Employee;
import com.app.service.EmployeeService;
import com.app.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final SectionService sectionService;

    @Autowired
    EmployeeController(EmployeeService employeeService,SectionService sectionService) {
        this.employeeService = employeeService;
        this.sectionService = sectionService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) String keyword) {
        model.addAttribute("employees", employeeService.findEmployeeByKeyWord(keyword));
        model.addAttribute("keyword", keyword);
        return "employees/index";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee") Employee employee, Model model) {
        model.addAttribute("sections",sectionService.findAll());
        return "employees/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
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
        model.addAttribute("sections",sectionService.findSectionByKeyword(""));
        return "employees/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employees/edit";
        }
        employeeService.save(employee);
        return "redirect:/employees/{id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
