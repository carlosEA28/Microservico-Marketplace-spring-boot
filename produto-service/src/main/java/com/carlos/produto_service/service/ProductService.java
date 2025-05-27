package com.carlos.produto_service.service;

import com.carlos.produto_service.consumer.ProductConsumer;
import com.carlos.produto_service.dto.CreateProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductConsumer productConsumer;

    public String createProduct(CreateProductDto createProductDto) {

        try {
            productConsumer.listenProductQueue(createProductDto);

            return "Product created";
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
