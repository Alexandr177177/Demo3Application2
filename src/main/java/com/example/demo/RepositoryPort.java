package com.example.demo;

import com.example.demo.model.primary.Employee;

import java.util.List;
import java.util.Optional;

public interface RepositoryPort {
    List<Employee> findAll();
    Optional<Employee> findById(Integer id);
    Employee  save(Employee t);
    void deleteById(Integer id);
}
