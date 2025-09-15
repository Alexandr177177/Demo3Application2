package com.example.demo.services.rabbitMQ;
import com.example.demo.config.RabbitConfig;
import com.example.demo.dto.ProjectDto;
import com.example.demo.model.primary.FormatFiles;
import com.example.demo.services.FileService;
import com.example.demo.services.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectConsumerService {
    MailSenderService mailSender;
    FileService fileService;

    @RabbitListener(queues = RabbitConfig.PROJECT_QUEUE)
    public void receiveMessage(ProjectDto projectDto) {
        System.out.println("№№№"+projectDto.toString());


    }
}
