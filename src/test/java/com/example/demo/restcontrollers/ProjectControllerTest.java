package com.example.demo.restcontrollers;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.model.Employee;
import com.example.demo.request.CreateProjectRequest;
import com.example.demo.services.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProjectController.class)
class ProjectControllerTest {
    private static final Integer PROJECT_ID = 1;
    private static final ProjectDto PROJECT = new ProjectDto();
    private static final List<ProjectDto> PROJECT_LIST = List.of(PROJECT);
    private static final CreateProjectRequest REQUEST = new CreateProjectRequest("Test Project","Test Description", 1);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private ProjectService projectService;

    @BeforeEach
    void init() {
        PROJECT.setId(PROJECT_ID);
        PROJECT.setName("Test Project");
        PROJECT.setDescription("Test Description");
    }


    @Test
    void getProjectByIdTest() throws Exception {
        when(projectService.getProjectById(PROJECT_ID)).thenReturn(PROJECT);
        mockMvc.perform(get("/projects/" + PROJECT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(PROJECT_ID))
                .andExpect(jsonPath("$.name").value(PROJECT.getName()))
                .andExpect(jsonPath("$.description").value(PROJECT.getDescription()));
        verify(projectService, times(1)).getProjectById(PROJECT_ID);
    }

    @Test
    void createProjectTest() throws Exception {
        when(projectService.createProject(REQUEST)).thenReturn(PROJECT);
        mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(REQUEST))
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(PROJECT_ID))
                .andExpect(jsonPath("$.name").value((PROJECT.getName())))
                .andExpect(jsonPath("$.description").value(PROJECT.getDescription()));
        verify(projectService, times(1)).createProject(REQUEST);
    }

    @Test
    void getAllProjectTest() throws Exception {
        when(projectService.getAllProject()).thenReturn(PROJECT_LIST);
        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(PROJECT_ID))
                .andExpect(jsonPath("$[0].name").value(PROJECT.getName()))
                .andExpect(jsonPath("$[0].description").value(PROJECT.getDescription()));
    }
}