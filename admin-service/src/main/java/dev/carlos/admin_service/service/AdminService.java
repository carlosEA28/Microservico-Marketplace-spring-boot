package dev.carlos.admin_service.service;

import dev.carlos.admin_service.dto.AdminRecordDto;
import dev.carlos.admin_service.dto.CreateProductDto;
import dev.carlos.admin_service.dto.CreateProductRecordDto;
import dev.carlos.admin_service.entity.AdminEntity;
import dev.carlos.admin_service.enums.AccountActive;
import dev.carlos.admin_service.ports.StoragePorts;
import dev.carlos.admin_service.producers.AdminProducer;
import dev.carlos.admin_service.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptConfig;

    @Autowired
    private AdminProducer adminProducer;

    @Autowired
    private StoragePorts storagePorts;

    @Transactional
    public AdminEntity createUser(AdminRecordDto adminRecordDto) {
        adminRepository.findByEmail(adminRecordDto.email())
                .ifPresent(admin -> {
                    throw new RuntimeException("E-mail já está cadastrado");
                });

        var user = new AdminEntity();
        user.setName(adminRecordDto.name());
        user.setEmail(adminRecordDto.email());
        user.setPassword(bCryptConfig.encode(adminRecordDto.password()));
        user.setPhoneNumber(adminRecordDto.phoneNumber());
        user.setIsActive(AccountActive.INACTIVE);

        var admin = adminRepository.save(user);
        adminProducer.publishMessageEmail(admin);

        return admin;

    }

    public String createProductToRabbitmq(CreateProductDto dto, MultipartFile imageFile) {
        try {
            // Upload da imagem para o storage
            String url = storagePorts.uploadFile(
                    imageFile.getBytes(),
                    UUID.randomUUID().toString(),
                    imageFile.getContentType());

            // Montar o DTO final para enviar via RabbitMQ
            var product = new CreateProductDto();
            product.setTitle(dto.getTitle());
            product.setPrice(dto.getPrice());
            product.setDescription(dto.getDescription());
            product.setCategory(dto.getCategory());
            product.setImgUrl(url);
            product.setAdminId(dto.getAdminId());

            // Enviar mensagem
            adminProducer.publishMessageCreateProduct(product);
            return "Produto enviado para processamento";

        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar imagem: " + e.getMessage(), e);
        }
    }


}
