package com.example.demo.services;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.model.primary.Employee;

import com.example.demo.repository.primary.EmployeeJPARepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private  final EmployeeJPARepository repository;
    private final EmployeeMapper mapper;


    public EmployeeDto getEmployeeById (Integer id){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return mapper.toDto(employee);
    }
    public List<EmployeeDto> getAllEmployee(){
        List<Employee> employees = repository.findAll();
        employees.forEach(System.out::println);
        return mapper.toDto(employees);

    }

    public EmployeeDto getEmployeeByName (String name) {
        Employee employee = repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return mapper.toDto(employee);
    }

    public EmployeeDto getEmployeeByEmail (String email) {
        Employee employee = repository.findByMail(email)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return mapper.toDto(employee);
    }

}
