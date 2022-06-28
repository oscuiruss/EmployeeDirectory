//package com.app.controllers;
//
//import com.app.dao.DepartmentDAO;
//import com.app.dao.EmployeeDAO;
//import com.app.models.Department;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//
//@Controller
//@RequestMapping("/departments")
//public class DepartmentController {
//    private final DepartmentDAO departmentDAO;
//    private final EmployeeDAO employeeDAO;
//
//    @Autowired
//    DepartmentController(DepartmentDAO departmentDAO, EmployeeDAO employeeDAO) {
//        this.departmentDAO = departmentDAO;
//        this.employeeDAO = employeeDAO;
//    }
//
//    @GetMapping()
//    public String index(Model model, @RequestParam(required = false) String keyword) {
//        model.addAttribute("departments", departmentDAO.index(keyword));
//        model.addAttribute("keyword", keyword);
//        return "departments/index";
//    }
//
//    @GetMapping("/new")
//    public String newSection(@ModelAttribute("department") Department department) {
//        return "departments/new";
//    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("department", departmentDAO.show(id));
//        return "departments/show";
//    }
//
//    @PostMapping("/create")
//    public String create(@ModelAttribute("section") @Valid Department department,
//                         BindingResult bindingResult) {
//        System.out.println(department.toString());
//        if (bindingResult.hasErrors()) {
//            return "departments/new";
//        }
//        departmentDAO.save(department);
//        return "redirect:/departments/" + department.getId();
//    }
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("department", departmentDAO.show(id));
//        model.addAttribute("employees", employeeDAO.index(""));
//        return "departments/edit";
//    }
//
//    @PostMapping("/{id}")
//    public String update(@ModelAttribute("department") @Valid Department department,
//                         @PathVariable("id") int id, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "departments/edit";
//        }
//        System.out.println(department.toString());
//        System.out.println(department.getDirector().toString());
//        departmentDAO.update(id, department);
//        return "redirect:/departments/{id}";
//    }
//
//    @GetMapping(value = "/delete/{id}")
//    public String delete(@PathVariable("id") int id) {
//        departmentDAO.delete(id);
//        return "redirect:/departments";
//    }
//}