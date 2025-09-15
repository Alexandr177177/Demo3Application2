package com.example.demo.repository.primary;

import com.example.demo.model.primary.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeJPARepository extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByName(String name);

    Optional<Employee> findByMail(String mail);
}
