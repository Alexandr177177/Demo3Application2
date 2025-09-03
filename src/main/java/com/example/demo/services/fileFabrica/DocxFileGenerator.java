package com.example.demo.services.fileFabrica;

import com.example.demo.dto.EmployeeDto;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;

public class DocxFileGenerator implements FileGenerator{
    @Override
    public String generateFile(EmployeeDto employee) {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph titleParagraph = doc.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setText("Resume");
        titleRun.addBreak();
        titleRun.setText(employee.getName());

        titleRun.setBold(true);
        titleRun.setFontFamily("Times New Roman");
        titleRun.setFontSize(14);
        titleRun.setColor("0070C0");
        titleRun.addBreak();

        // Добавление основного текста
        XWPFParagraph paragraph1 = doc.createParagraph();
        XWPFRun run1 = paragraph1.createRun();

        run1.addBreak();
        run1.setText("Telegram: " + employee.getTelephon());
        run1.addBreak();
        run1.setFontFamily("Times New Roman");
        run1.setFontSize(12);
        run1.addBreak();
        XWPFRun run2 = paragraph1.createRun();
        run2.setText(employee.getTelephon());

        try {
            String path ="D:\\test.docx";
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            doc.write(fileOutputStream);
            fileOutputStream.close();
            return path;
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}
