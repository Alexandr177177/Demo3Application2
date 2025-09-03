package com.example.demo.services.rabbitMQ;

import com.example.demo.config.RabbitConfig;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProjectAmqpProducerService {
    private RabbitTemplate template;

    public void sendMessage(ProjectDto project){
        template.convertAndSend(RabbitConfig.PROJECT_QUEUE, project);
    }
}
