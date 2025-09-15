package com.example.demo;

import com.example.demo.model.primary.Employee;
import com.example.demo.model.primary.Education;
import com.example.demo.model.primary.EnglishLevel;
import com.example.demo.model.primary.Skill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/resumes")
public class ResumeController {

    private final RepositoryPort repository;

    // Показать одно резюме
    @GetMapping("/{id}")
    public String getResume(Model model, @PathVariable Integer id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "resume";
        }
        return "error";
    }

    // Список всех резюме
    @GetMapping
    public String getAllResumes(Model model) {
        List<Employee> employees = repository.findAll();
        model.addAttribute("employees", employees);
        return "index";
    }

    // Удаление резюме
    @GetMapping("/delete/{id}")
    public String deleteResume(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/resumes";
    }

    // Форма создания
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("skillsList", Skill.values());
        model.addAttribute("englishLevelsList", EnglishLevel.values());
        return "create";
    }

    // Форма редактирования
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable Integer id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            model.addAttribute("employee", emp);
            model.addAttribute("skillsList", Skill.values());
            model.addAttribute("englishLevelsList", EnglishLevel.values());
            return "edit";
        }
        return "redirect:/resumes";
    }

    // Форма редактирования образования
    @GetMapping("/{id}/education")
    public String showEducationForm(Model model, @PathVariable Integer id) {
        Optional<Employee> employeeOpt = repository.findById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            model.addAttribute("employee", employee);
            model.addAttribute("educationList", employee.getEducation());
            model.addAttribute("newEducation", new Education());
            return "education"; // Предполагается, что страница уже существует или будет обновлена
        }
        return "error";
    }

    // Добавление образования
    @PostMapping("/{id}/education/add")
    public String addEducation(@PathVariable Integer id,
                               @RequestParam int yearStart,
                               @RequestParam int yearEnd,
                               @RequestParam String nameEducation,
                               @RequestParam String degree) {
        Optional<Employee> employeeOpt = repository.findById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            Education education = new Education();
            education.setYearStart(yearStart);
            education.setYearEnd(yearEnd);
            education.setNameEducation(nameEducation);
            education.setDegree(degree);
            education.setEmployee(employee);

            employee.getEducation().add(education);
            repository.save(employee);
        }
        return "redirect:/resumes/" + id + "/education";
    }

    // Удаление образования
    @GetMapping("/{id}/education/delete")
    public String deleteEducation(@PathVariable Integer id,
                                  @RequestParam Integer eduId) {
        Optional<Employee> employeeOpt = repository.findById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.getEducation().removeIf(e -> e.getId() == eduId);
            repository.save(employee);
        }
        return "redirect:/resumes/" + id + "/education";
    }

    // Создание сотрудника
    @PostMapping
    public String createResume(@ModelAttribute Employee employee,
                               @RequestParam("imageFile") MultipartFile imageFile,
                               @RequestParam int yearStart,
                               @RequestParam int yearEnd,
                               @RequestParam String nameEducation,
                               @RequestParam String degree,
                               Model model) {
        try {
            if (!imageFile.isEmpty()) {
                String imagePath = FileUploadUtil.saveFile(imageFile);
                employee.setImage(imagePath);
            }
        } catch (IOException e) {
            model.addAttribute("error", "Ошибка загрузки изображения");
            model.addAttribute("employee", employee);
            model.addAttribute("skillsList", Skill.values());
            model.addAttribute("englishLevelsList", EnglishLevel.values());
            return "create";
        }

        // Убедиться, что коллекции инициализированы
        if (employee.getEducation() == null) {
            employee.setEducation(new HashSet<>());
        }

        // Создать новый объект Education и установить ссылку на сотрудника
        Education education = new Education();
        education.setYearStart(yearStart);
        education.setYearEnd(yearEnd);
        education.setNameEducation(nameEducation);
        education.setDegree(degree);
        education.setEmployee(employee);  // <<< ОБЯЗАТЕЛЬНОЕ ПОЛЕ

        employee.getEducation().add(education);

        repository.save(employee);
        return "redirect:/resumes";
    }
    // Обновление сотрудника
    @PutMapping("/{id}")
    public String updateResume(@PathVariable Integer id,
                               @ModelAttribute Employee employee,
                               @RequestParam("imageFile") MultipartFile imageFile,
                               Model model) {
        try {
            if (!imageFile.isEmpty()) {
                String imagePath = FileUploadUtil.saveFile(imageFile);
                employee.setImage(imagePath);
            } else {
                employee.setImage(repository.findById(id).map(Employee::getImage).orElse(null));
            }
        } catch (IOException e) {
            model.addAttribute("error", "Ошибка загрузки изображения");
            model.addAttribute("employee", employee);
            model.addAttribute("skillsList", Skill.values());
            model.addAttribute("englishLevelsList", EnglishLevel.values());
            return "edit";
        }

        repository.save(employee);
        return "redirect:/resumes";
    }
}