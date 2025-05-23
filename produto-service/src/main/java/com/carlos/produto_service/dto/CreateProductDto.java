package com.carlos.produto_service.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateProductDto(String title,
                               BigDecimal price,
                               String description,
                               String category,
                               String imgUrl,
                               String adminId,
                               Instant createdAt) {
}
