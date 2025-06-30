package com.example.demo.mappers;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto (Employee employee);
    List<EmployeeDto> toDto (List<Employee> employees);

}
