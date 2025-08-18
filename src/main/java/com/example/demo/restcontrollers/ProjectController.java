package com.example.demo.restcontrollers;

import com.example.demo.dto.ProjectDto;
import com.example.demo.request.CreateProjectRequest;
import com.example.demo.services.MailSenderService;
import com.example.demo.services.ProjectService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.TimeZone;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;
    private final MailSenderService mailSender;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Integer id) {
        try {
            ProjectDto project = service.getProjectById(id);
            return ResponseEntity.ok(project);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(
            @Parameter(description = "Данные нового проекта", required = true)
            @RequestBody @Valid CreateProjectRequest request
    ) {
        try {
            ProjectDto createProject = service.createProject(request);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createProject.getId())
                    .toUri();
            return ResponseEntity.created(location).body(createProject);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/mail")
    public ResponseEntity<String> sayHello() {

        mailSender.send(
                "omega177@mail.ru",
                "Hello my friend",
                "Привет!"
        );
        return ResponseEntity.ok("Hello my friend. it's ok");
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProject() {
        List<ProjectDto> projects = service.getAllProject();
        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(projects);
        }

    }
}