package com.carlos.produto_service.repository;

import com.carlos.produto_service.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
