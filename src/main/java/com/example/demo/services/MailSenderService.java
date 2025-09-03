package com.example.demo.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class MailSenderService {


    private final JavaMailSender javaMailSender;
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


    public void sendMailWithAttachment(String to, String subject, String text, String attachmentPath) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            // Используем помощник для создания multipart сообщения
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(from);

            //Добавление вложений
            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
            helper.addAttachment((file.getFilename()), file);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Ошибка при отправлении письма с вложением");
        }
    }
}