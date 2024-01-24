package com.portfolio.portfolio.controllers;

import com.portfolio.portfolio.models.Project;
import com.portfolio.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PortfolioController {

    @Autowired
    private ProjectRepository projectRepository;

    private void setupModelAttributes(Model model, String title, String body) {
        model.addAttribute("title", title);
        model.addAttribute("body", body);
    }

    @GetMapping("/")
    public String portfolio_main(Model model) {
        setupModelAttributes(model, "Main", null);
        return "portfolio-main";
    }

    @GetMapping("/biography")
    public String biography(Model model) {
        setupModelAttributes(model, "Biography", "Biography");
        return "portfolio-biography";
    }

    @GetMapping("/work_experience")
    public String work_experience(Model model) {
        setupModelAttributes(model, "Work Experience", "Work Experience");
        return "portfolio-work-experience";
    }

    @GetMapping("/education")
    public String education(Model model) {
        setupModelAttributes(model, "Education", "My Education");
        return "portfolio-education";
    }

    @GetMapping("/technologies")
    public String technologies(Model model) {
        setupModelAttributes(model, "Technologies", "Technologies");
        return "portfolio-technologies";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        setupModelAttributes(model, "Contacts", "Contacts");
        return "portfolio-contacts";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        Iterable<Project> projects = projectRepository.findAll();
        setupModelAttributes(model, "My Projects", null);
        model.addAttribute("project", projects);
        return "portfolio-projects";
    }

    @GetMapping("/projects/add")
    public String projectsAdd(Model model) {
        setupModelAttributes(model, "Add project", null);
        return "portfolio-project-add";
    }

    @PostMapping("/portfolio/projects/add")
    public String ProjectCardAdd(@RequestParam String name, String language, String description, String link, Model model) {
        Project project = new Project(name, language, description, link);
        projectRepository.save(project);
        return "redirect:/projects";
    }

    @GetMapping("/text")
    public String text(Model model) {
        setupModelAttributes(model, "Text me", null);
        return "portfolio-text-me";
    }
}

