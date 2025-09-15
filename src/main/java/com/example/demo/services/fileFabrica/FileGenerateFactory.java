package com.example.demo.services.fileFabrica;

import com.example.demo.model.primary.FormatFiles;

public class FileGenerateFactory {
    public static FileGenerator getFileGenerator(FormatFiles fileFormat) {
        return switch (fileFormat) {
            case DOCX -> new DocxFileGenerator();
            case XLSX -> new XlsxFileGenerator();

        };
    }
}
