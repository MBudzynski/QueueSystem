package com.example.queuesystemcore.infrastructure.config.rabbit;

import com.example.queuesystemcore.common.application.FacilityFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

    public static final String DEAD_LETTER_EXCHANGE = "queuesystem.dlx";

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE, true, false);
    }

    @Bean
    public RabbitAdmin createQueues(ConnectionFactory connectionFactory,
                                    FacilityFacade facilityFacade,
                                    DirectExchange deadLetterExchange) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        rabbitAdmin.declareExchange(deadLetterExchange);

        List<String> queueNames = facilityFacade.getAllQueueNames();

        for (String queueName : queueNames) {
            String deadLetterQueueName = queueName + ".dlq";
            Map<String, Object> arguments = new HashMap<>();
            arguments.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
            arguments.put("x-dead-letter-routing-key", deadLetterQueueName);

            Queue mainQueue = new Queue(queueName, true, false, false, arguments);
            Queue deadLetterQueue = new Queue(deadLetterQueueName, true);
            Binding binding = BindingBuilder.bind(deadLetterQueue)
                    .to(deadLetterExchange)
                    .with(deadLetterQueueName);

            rabbitAdmin.declareQueue(mainQueue);
            rabbitAdmin.declareQueue(deadLetterQueue);
            rabbitAdmin.declareBinding(binding);
            log.info("Declared RabbitMQ queue '{}' and dead-letter queue '{}'",
                    queueName, deadLetterQueueName);
        }
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
