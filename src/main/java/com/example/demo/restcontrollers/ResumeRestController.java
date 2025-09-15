package com.example.demo.restcontrollers;

import com.example.demo.repository.primary.EmployeeRepository;
import com.example.demo.model.primary.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Проекты")
@RestController
@AllArgsConstructor
@RequestMapping("/rest")
public class ResumeRestController {
    private final EmployeeRepository repository;
   /* @GetMapping("/{id}")
    public Employee getResume(@PathVariable Long id) {
            return repository.findById(id).get();
    }
*/
    @Operation(summary = "Получить имя по иднетификвтору",
    description = "Достает из базы данных и преобразует в ДТО данные об employee")

    @GetMapping
    public List<Employee> getResumes() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Employee getResume(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

}
