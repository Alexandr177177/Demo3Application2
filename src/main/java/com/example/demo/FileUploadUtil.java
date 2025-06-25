package com.example.demo;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

public class FileUploadUtil {

    private static final String UPLOAD_DIR = "uploads";

    public static String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;

        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "-" + originalFilename;
        Path filePath = uploadPath.resolve(fileName);

        file.transferTo(filePath);

        return "/uploads/" + fileName; // URL для доступа к файлу
    }
}