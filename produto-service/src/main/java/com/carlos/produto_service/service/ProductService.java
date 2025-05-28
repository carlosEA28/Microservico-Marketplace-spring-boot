package com.carlos.produto_service.service;

import com.carlos.produto_service.consumer.ProductConsumer;
import com.carlos.produto_service.dto.CreateProductDto;
import com.carlos.produto_service.dto.ProductDtoResponse;
import com.carlos.produto_service.entity.ProductEntity;
import com.carlos.produto_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductConsumer productConsumer;

    @Autowired
    private ProductRepository productRepository;

    public String createProduct(CreateProductDto createProductDto) {

        try {
            productConsumer.listenProductQueue(createProductDto);

            return "Product created";
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductDtoResponse> getAllProducts(String adminId) {

        var hasProducts = productRepository.findAllByAdminId(adminId);

        if (hasProducts.isEmpty()) {
            throw new RuntimeException("Voce nao possui nenhum produto anunciado");
        }

        return hasProducts.stream().map(product -> new ProductDtoResponse(product.title(), product.price(), product.description(), product.category(), product.imgUrl())).toList();
    }
}
