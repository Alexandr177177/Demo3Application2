package com.example.demo.restcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc

class EmployeeControllerTest {
@Container
static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:13.2");

    @Autowired
    MockMvc mockMvc;
    @Test
    void getEmployeeById() throws Exception {
        this.mockMvc.perform(get("/employees/1") )
        .andExpectAll(status().isOk());
    }

    @Test
    void getEmployeeByName() {
    }

    @Test
    void getEmployeeByEmail() {
    }
}