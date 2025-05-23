package dev.carlos.admin_service.dto;

import java.math.BigDecimal;

public record CreateProductRecordDto(String title, BigDecimal price, String description, String category, String imgUrl,
                                     String adminId) {
}
