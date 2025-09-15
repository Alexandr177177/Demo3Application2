package com.example.demo.mappers;

import com.example.demo.dto.ProjectDto;
import com.example.demo.model.primary.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto toDto (Project project);
    List<ProjectDto> toDto (List<Project> projects);

}
