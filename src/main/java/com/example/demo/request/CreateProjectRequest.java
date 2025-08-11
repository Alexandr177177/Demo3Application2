package com.example.demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProjectRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    @NotNull
    private Integer employeeId;
}
