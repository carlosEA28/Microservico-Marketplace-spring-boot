package com.carlos.produto_service.repository;

import com.carlos.produto_service.dto.ProductDtoResponse;
import com.carlos.produto_service.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    List<ProductDtoResponse> findAllByAdminId(String adminId);
}
