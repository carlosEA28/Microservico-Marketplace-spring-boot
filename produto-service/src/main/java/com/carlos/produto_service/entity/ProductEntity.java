package com.carlos.produto_service.entity;

import com.carlos.produto_service.dto.CreateProductDto;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@DynamoDbBean
public class ProductEntity {

    private UUID productId;
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String imgUrl;
    private String adminId;
    private Instant createdAt;

    public static ProductEntity product(CreateProductDto createProductDto) {
        var product = new ProductEntity();
        product.setProductId(UUID.randomUUID());
        product.setTitle(createProductDto.title());
        product.setPrice(createProductDto.price());
        product.setDescription(createProductDto.description());
        product.setCategory(createProductDto.category());
        product.setImgUrl(createProductDto.imgUrl());
        product.setAdminId(createProductDto.adminId());
        product.setCreatedAt(createProductDto.createdAt());

        return product;
    }

    @DynamoDbPartitionKey
    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
