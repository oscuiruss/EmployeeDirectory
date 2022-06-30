package com.app.controllers;


import com.app.models.Department;
import com.app.service.DepartmentService;
import com.app.service.EmployeeService;
import com.app.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final SectionService sectionService;

    @Autowired
    DepartmentController(DepartmentService departmentService, EmployeeService employeeService, SectionService sectionService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.sectionService = sectionService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) String keyword) {
        model.addAttribute("departments", departmentService.findDepartmentByKeyWord(keyword));
        model.addAttribute("keyword", keyword);
        return "departments/index";
    }

    @GetMapping("/new")
    public String newSection(@ModelAttribute("department") Department department, Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "departments/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("department", departmentService.findDepartmentById(id));
        model.addAttribute("sections", sectionService.findSectionByDepartment(departmentService.findDepartmentById(id)));
        return "departments/show";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("section") @Valid Department department,
                         Model model, BindingResult bindingResult) {
        model.addAttribute("employees", employeeService.findAll());
        if (bindingResult.hasErrors()) {
            return "departments/new";
        }
        departmentService.save(department);
        return "redirect:/departments/" + department.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("department", departmentService.findDepartmentById(id));
        model.addAttribute("employees", employeeService.findAll());
        return "departments/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("department") @Valid Department department,
                         @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "departments/edit";
        }
        departmentService.save(department);
        return "redirect:/departments/{id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}