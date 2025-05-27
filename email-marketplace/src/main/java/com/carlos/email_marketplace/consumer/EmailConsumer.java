package com.carlos.email_marketplace.consumer;

import com.carlos.email_marketplace.dto.EmailConsumerDto;
import com.carlos.email_marketplace.dto.EmailRecordDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "product.v1.product-created.send-email")
    public void listenEmailQueue(@Payload EmailConsumerDto dto) {
        System.out.println(dto.email());
    }
}
