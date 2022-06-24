package com.app.controllers;

import com.app.dao.SectionDAO;
import com.app.models.Employee;
import com.app.models.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/sections")
public class SectionController {
    private final SectionDAO sectionDAO;

    @Autowired
    SectionController(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) String keyword) {
        model.addAttribute("sections", sectionDAO.index(keyword));
        model.addAttribute("keyword", keyword);
        return "sections/index";
    }

    @GetMapping("/new")
    public String newSection(@ModelAttribute("section") Section section) {
        return "sections/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("section", sectionDAO.show(id));
        return "sections/show";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("section") @Valid Section section,
                         BindingResult bindingResult) {
        System.out.println(section.toString());
        if (bindingResult.hasErrors()) {
            return "sections/new";
        }
        sectionDAO.save(section);
        return "redirect:/sections/" + section.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("section", sectionDAO.show(id));
        return "sections/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("section") @Valid Section section,
                         @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sections/edit";
        }
        sectionDAO.update(id, section);
        return "redirect:/sections/{id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        sectionDAO.delete(id);
        return "redirect:/sections";
    }
}
