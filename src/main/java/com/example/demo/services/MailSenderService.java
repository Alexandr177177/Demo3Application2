package com.example.demo.services;

import com.example.demo.model.Employee;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String from;
    private final MailSender mailSender;
    public void send(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailMessage.setFrom(from);
        mailSender.send(mailMessage);
    }

    public void createXLSFile(Employee employee) {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("sheet1");
    Row row0 = sheet.createRow(0);
    row0.createCell(0).setCellValue("Resume");


    Row row1 = sheet.createRow(1);
    row1.createCell(0).setCellValue(employee.getName());

    Row row2 = sheet.createRow(2);
    row2.createCell(0).setCellValue("Phone");
    row2.createCell(1).setCellValue(employee.getTelephon());
    try {
        FileOutputStream  fileOutputStream = new FileOutputStream("D:\\test.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

    } catch (IOException e) {
            throw new RuntimeException(e);
    }

    }


}
