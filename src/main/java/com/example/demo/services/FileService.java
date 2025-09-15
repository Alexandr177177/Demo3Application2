package com.example.demo.services;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.primary.FormatFiles;
import com.example.demo.services.fileFabrica.FileGenerateFactory;
import com.example.demo.services.fileFabrica.FileGenerator;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    public String createFile(EmployeeDto employee, FormatFiles formatFiles) {
        FileGenerator generator = FileGenerateFactory.getFileGenerator(formatFiles);
        return generator.generateFile(employee);
    }
}