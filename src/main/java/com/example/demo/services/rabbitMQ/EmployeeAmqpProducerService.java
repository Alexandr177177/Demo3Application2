package com.example.demo.services.rabbitMQ;

import com.example.demo.config.RabbitConfig;
import com.example.demo.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeAmqpProducerService {
    private RabbitTemplate template;

    public void sendMessage(EmployeeDto employee){
        template.convertAndSend(RabbitConfig.MAIL_QUEUE, employee);
    }
}
