package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Информация о сотруднике")
public class EmployeeDto {
    @Schema(description = "Уникальный идентификатор сотрудника", example = "1")
    private Integer id;

    @Schema(description = "Имя сотрудника", example = "Иван Иванов")
    private String name;

    @Schema(description = "Ссылка на изображение", example = "https://example.com/photo.jpg ")
    private String image;

    @Schema(description = "Телефон сотрудника", example = "+7 (999) 123-45-67")
    private String telephon;

    @Schema(description = "Email сотрудника", example = "ivan@example.com")
    private String mail;
}