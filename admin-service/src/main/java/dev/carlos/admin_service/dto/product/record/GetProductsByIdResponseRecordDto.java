package dev.carlos.admin_service.dto.product.record;

import java.math.BigDecimal;

public record GetProductsByIdResponseRecordDto(String title,
                                               BigDecimal price,
                                               String description,
                                               String category,
                                               String imgUrl) {
}
