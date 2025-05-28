package dev.carlos.admin_service.service;

import dev.carlos.admin_service.dto.admin.record.AdminRecordDto;
import dev.carlos.admin_service.dto.product.CreateProductDto;
import dev.carlos.admin_service.dto.product.record.GetProductsByIdResponseRecordDto;
import dev.carlos.admin_service.entity.AdminEntity;
import dev.carlos.admin_service.enums.AccountActive;
import dev.carlos.admin_service.ports.StoragePorts;
import dev.carlos.admin_service.producers.AdminProducer;
import dev.carlos.admin_service.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
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

    @Autowired
    private WebClient.Builder webCleintBuilder;

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
            String url = storagePorts.uploadFile(
                    imageFile.getBytes(),
                    UUID.randomUUID().toString(),
                    imageFile.getContentType());

            var product = new CreateProductDto();
            product.setTitle(dto.getTitle());
            product.setPrice(dto.getPrice());
            product.setDescription(dto.getDescription());
            product.setCategory(dto.getCategory());
            product.setImgUrl(url);
            product.setAdminId(dto.getAdminId());


            adminProducer.publishMessageCreateProduct(product);
            return "Produto enviado para processamento";

        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar imagem: " + e.getMessage(), e);
        }
    }

    public Flux<GetProductsByIdResponseRecordDto> getAllProductsByAdminId(String adminId) {
        return webCleintBuilder.build()
                .get()
                .uri("http://localhost:8080/api/v1/product/" + adminId)//por a url no env
                .retrieve().bodyToFlux(GetProductsByIdResponseRecordDto.class);
    }
}
