package com.example.demo.util;

import com.example.demo.annotations.SimpleLog;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.model.EnglishLevel;
import lombok.Builder;

public class Random {

    private static final java.util.Random RANDOM = new java.util.Random();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String[] PHOTOS = {
            "/images/img_1.png", "/images/img_2.png"
    };


    static public String getRandomString(int length) {

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    static public EnglishLevel getRandomEnglishLevel() {
        EnglishLevel[] levels = EnglishLevel.values();
        return levels[RANDOM.nextInt(levels.length)];
    }

    static public String getRandomPhoto() {
        return PHOTOS[RANDOM.nextInt(PHOTOS.length)];
    }

    static public ProjectDto getRandomProjectDto(){
        return ProjectDto.builder()
                .name(getRandomString(30))
                .description(getRandomString(100))
                .build();
    }

    static public EmployeeDto getRandomEmployeeDto() {
        return EmployeeDto.builder()
                .name(getRandomString(30))
                .mail(getRandomString(20) + "@gmail.com")
                .build();
    }
}
