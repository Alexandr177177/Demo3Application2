package com.example.demo.restcontrollers;

import com.example.demo.dto.ProjectDto;
import com.example.demo.request.CreateProjectRequest;
import com.example.demo.services.ProjectService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

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
    @RequestBody  @Valid CreateProjectRequest request
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