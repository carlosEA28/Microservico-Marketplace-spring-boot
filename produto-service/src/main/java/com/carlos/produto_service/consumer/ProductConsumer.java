package com.carlos.produto_service.consumer;

import com.carlos.produto_service.dto.CreateProductDto;
import com.carlos.produto_service.entity.ProductEntity;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Autowired
    private DynamoDbTemplate dynamoDbTemplate;

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenProductQueue(@Payload CreateProductDto createProductDto) {

//        var product = new ProductEntity();
//        product.setTitle(createProductDto.title());
//        product.setPrice(createProductDto.price());
//        product.setDescription(createProductDto.description());
//        product.setCategory(createProductDto.category());
//        product.setImgUrl(createProductDto.imgUrl());
//        product.setAdminId(createProductDto.adminId());
//        product.setCreatedAt(createProductDto.createdAt());

        var entity = ProductEntity.product(createProductDto);

        dynamoDbTemplate.save(entity);
    }
}
