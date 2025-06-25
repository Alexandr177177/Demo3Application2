package com.example.demo.services;

import com.example.demo.dto.ProjectDto;
import com.example.demo.mappers.ProjectMapper;
import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProgectServices {
    private  final ProjectRepository repository;
    private final ProjectMapper mapper;


    public ProjectDto getProjectById (Integer id){
        Project project = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return mapper.toDto(project);
    }
    public List<ProjectDto> getAllProject(){
        List<Project> projects = repository.findAll();
        projects.forEach(System.out::println);
        return mapper.toDto(projects);

    }
}
