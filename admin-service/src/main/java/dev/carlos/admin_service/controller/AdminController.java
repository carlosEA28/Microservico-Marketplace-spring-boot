package dev.carlos.admin_service.controller;

import dev.carlos.admin_service.dto.AdminRecordDto;
import dev.carlos.admin_service.dto.CreateProductDto;
import dev.carlos.admin_service.dto.CreateProductRecordDto;
import dev.carlos.admin_service.entity.AdminEntity;
import dev.carlos.admin_service.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<AdminEntity> createAdmin(@RequestBody @Valid AdminRecordDto dto) {
        var admin = adminService.createUser(dto);

        return ResponseEntity.ok().body(admin);
    }

    @PostMapping(value = "/product")
    public ResponseEntity<String> createProduct(
            @ModelAttribute("product") CreateProductDto dto,
            @RequestParam("image") MultipartFile imageFile
    ) {
        String response = adminService.createProductToRabbitmq(dto, imageFile);
        return ResponseEntity.ok(response);
    }

}
