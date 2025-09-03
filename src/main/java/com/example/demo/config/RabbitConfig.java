package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public static final String MAIL_QUEUE = "mailQueue";
    public static final String MAIL_EXCHANGE = "mailExchange";
    public static final String MAIL_ROUTING_KEY = "mail.routing.key";

    public static final String PROJECT_QUEUE = "mailQueue1";
    public static final String MAIL1_EXCHANGE = "mailExchange1";
    public static final String MAIL1_ROUTING_KEY = "mail1.routing.key";

    @Bean
    public Queue queue() {
        return new Queue(MAIL_QUEUE, false);
    }

    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(MAIL_EXCHANGE, true, false);
    }


    @Bean
    public Binding mailBinding( Queue queue, DirectExchange mailExchange) {
        return BindingBuilder.bind(queue)
                .to(mailExchange)
                .with(MAIL_ROUTING_KEY);
    }

 @Bean
    public Queue queue1() {
        return new Queue(PROJECT_QUEUE, false);
    }

    @Bean
    public DirectExchange mailExchange1() {
        return new DirectExchange(MAIL1_EXCHANGE, true, false);
    }
    @Bean
    public Binding mailBinding1(Queue queue1, DirectExchange mailExchange1) {
        return BindingBuilder.bind(queue1)
                .to(mailExchange1)
                .with(MAIL1_ROUTING_KEY);
    }








    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // ВАЖНО: Настройте RabbitTemplate с JSON конвертером
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
