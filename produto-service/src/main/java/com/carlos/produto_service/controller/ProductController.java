package com.carlos.produto_service.controller;

import com.carlos.produto_service.dto.CreateProductDto;
import com.carlos.produto_service.dto.ProductDtoResponse;
import com.carlos.produto_service.entity.ProductEntity;
import com.carlos.produto_service.service.ProductService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {


    @Autowired
    private ProductService productService;

    public ResponseEntity<String> createProduct(CreateProductDto createProductDto) {

        var product = productService.createProduct(createProductDto);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/product/{adminId}")
    public ResponseEntity<List<ProductDtoResponse>> getAllProductsById(@PathVariable("adminId") String adminId) {

        var products = productService.getAllProducts(adminId);

        return ResponseEntity.ok().body(products);
    }
}
