package com.example.demo.restcontrollers;

import com.example.demo.SpringDataRepository.EmployeeRepository;
import com.example.demo.model.Employee;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
