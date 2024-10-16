package com.example.queuesystemcore.infrastructure.config.rabbit;

import com.example.queuesystemcore.common.application.FacilityFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {


    @Bean
    public RabbitAdmin createQueues(ConnectionFactory connectionFactory, FacilityFacade facilityFacade) {
        List<String> queueNames = facilityFacade.getAllQueueNames();
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        queueNames.forEach(queueName -> {
            Queue queue = new Queue(queueName, true);
            rabbitAdmin.declareQueue(queue);
            log.info("Created queue, queueName: {}", queueName);
        });
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
