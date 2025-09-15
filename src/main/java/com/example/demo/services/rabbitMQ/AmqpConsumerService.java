package com.example.demo.services.rabbitMQ;


import com.example.demo.config.RabbitConfig;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.primary.FormatFiles;
import com.example.demo.services.FileService;
import com.example.demo.services.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AmqpConsumerService {
    MailSenderService mailSender;
    FileService fileService;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE)
    public void receiveMessage(EmployeeDto employee) {
        System.out.println("Received Employee : " + employee);
        mailSender.sendMailWithAttachment(
                "omega177@mail.ru",
                "resume of " + employee.getName(),
                "Вы можете ознакомиться с содержанием",
                fileService.createFile(employee, FormatFiles.DOCX)
        );

    }

}
