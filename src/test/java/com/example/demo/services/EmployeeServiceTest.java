package com.example.demo.services;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.mappers.ProjectMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.repository.EmployeeJPARepository;
import com.example.demo.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.Scenario;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeeServiceTest {
    @Mock
    private EmployeeJPARepository repository;
    @Mock
    private EmployeeMapper mapper;
    private final Integer EMPLOYEE_ID = 1;
    private final String EMPLOYEE_NAME = "Петя";
    private final String EMPLOYEE_EMAIL= "sdg@dsrh";
    private final Employee employee = new Employee();

    private final EmployeeDto employeeDto= new EmployeeDto();
    private final List<Employee> employees = List.of(employee) ;
    private final List<EmployeeDto> employeesDto = List.of(employeeDto) ;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void getEmployeeById() {
        //   EmployeeService employeeService = new EmployeeService(repository, mapper);
        Mockito.when(repository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(employee));
        Mockito.when(mapper.toDto(employee)).thenReturn(employeeDto);
        var result= employeeService.getEmployeeById(EMPLOYEE_ID);
        assertEquals(employeeDto, result);
    }
    @Test
    public void getEmployeeByName() {
        //EmployeeService employeeService = new EmployeeService(repository, mapper);
        Mockito.when(repository.findByName(EMPLOYEE_NAME)).thenReturn(Optional.of(employee));
        Mockito.when(mapper.toDto(employee)).thenReturn(employeeDto);
        var result= employeeService.getEmployeeByName(EMPLOYEE_NAME);
        assertEquals(employeeDto, result);
    }
    @Test
    public void getEmployeeByMail() {
       // EmployeeService employeeService = new EmployeeService(repository, mapper);
        Mockito.when(repository.findByMail(EMPLOYEE_EMAIL)).thenReturn(Optional.of(employee));
        Mockito.when(mapper.toDto(employee)).thenReturn(employeeDto);
        var result= employeeService.getEmployeeByEmail(EMPLOYEE_EMAIL);
        assertEquals(employeeDto, result);
    }

    @Test
    public void getEmployeeAllEmployee() {
        EmployeeService employeeService = new EmployeeService(repository, mapper);
        Mockito.when(repository.findAll()).thenReturn(employees);
        Mockito.when(mapper.toDto(employees)).thenReturn(employeesDto);
        var result= employeeService.getAllEmployee();
        assertEquals(employeesDto, result);
    }/*
    @Test
    public void  getProjectById (){
        ProjectService projectService = new ProjectService(repository2, mapper2,employeeRepository);
        Mockito.when(repository2.findById(EMPLOYEE_ID)).thenReturn(Optional.of(project));
        Mockito.when(mapper2.toDto(project)).thenReturn(projectDto);
        var result= projectService.getProjectById(EMPLOYEE_ID);
        assertEquals(projectDto, result);
    }*/
}