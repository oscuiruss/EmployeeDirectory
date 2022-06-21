package com.app.controllers;

import com.app.dao.EmployeeDAO;
import com.app.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeDAO employeeDAO;

    @Autowired
    EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) String keyword) {
        model.addAttribute("employees", employeeDAO.index(keyword));
        model.addAttribute("keyword", keyword);
        return "employees/index";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee") Employee employee) {
        return "employees/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeDAO.show(id));
        return "employees/show";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult) {
        System.out.println(employee.toString());
        if (bindingResult.hasErrors())
            return "employees/new";
        employeeDAO.save(employee);
        return "redirect:/employees/" + employee.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeDAO.show(id));
        return "employees/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("employee") @Valid Employee employee,
                         @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employees/edit";
        }
        employeeDAO.update(id, employee);
        return "redirect:/employees/{id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeDAO.delete(id);
        return "redirect:/employees";
    }
}
