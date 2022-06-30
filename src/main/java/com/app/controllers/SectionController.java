package com.app.controllers;


import com.app.models.Section;
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
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    SectionController(SectionService sectionService, EmployeeService employeeService, DepartmentService departmentService) {
        this.sectionService = sectionService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) String keyword) {
        model.addAttribute("sections", sectionService.findSectionByKeyword(keyword));
        model.addAttribute("keyword", keyword);
        return "sections/index";
    }

    @GetMapping("/new")
    public String newSection(@ModelAttribute("section") Section section, Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "sections/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("section", sectionService.findSectionById(id));
        return "sections/show";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("section") @Valid Section section, Model model,
                         BindingResult bindingResult) {
        model.addAttribute("employees", employeeService.findAll());
        if (bindingResult.hasErrors()) {
            return "sections/new";
        }
        sectionService.save(section);
        return "redirect:/sections/" + section.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employees", employeeService.findAllBySection(sectionService.findSectionById(id)));
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("section", sectionService.findSectionById(id));
        return "sections/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("section") @Valid Section section,
                         @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sections/edit";
        }
        sectionService.save(section);
        return "redirect:/sections/{id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        sectionService.delete(id);
        return "redirect:/sections";
    }
}
