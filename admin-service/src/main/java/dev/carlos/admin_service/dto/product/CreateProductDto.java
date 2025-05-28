package dev.carlos.admin_service.dto.product;


import java.math.BigDecimal;
import java.time.Instant;

public class CreateProductDto {

    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String imgUrl;
    private String adminId;
    private Instant createdAt;
//    private String adminEmail;
//
//    public String getAdminEmail() {
//        return adminEmail;
//    }
//
//    public void setAdminEmail(String adminEmail) {
//        this.adminEmail = adminEmail;
//    }

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