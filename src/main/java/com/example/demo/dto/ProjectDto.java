package com.example.demo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Builder
@Data
@Schema(description = "Информация о проекте")
public class ProjectDto {
    @Schema(description = "Уникальный идентификатор проекта", example = "101")
    private Integer id;

    @Schema(description = "Название проекта", example = "CRM-система")
    private String name;

    @Schema(description = "Описание проекта", example = "Проект по автоматизации продаж")
    private String description;
}