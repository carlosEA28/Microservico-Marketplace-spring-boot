package com.carlos.produto_service.dto;

import java.math.BigDecimal;

public record ProductDtoResponse(
        String title,
        BigDecimal price,
        String description,
        String category,
        String imgUrl

) {
}
