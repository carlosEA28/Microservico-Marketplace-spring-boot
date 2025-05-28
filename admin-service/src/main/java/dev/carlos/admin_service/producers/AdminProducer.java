package dev.carlos.admin_service.producers;

import dev.carlos.admin_service.dto.product.CreateProductDto;
import dev.carlos.admin_service.dto.EmailDto;
import dev.carlos.admin_service.entity.AdminEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.email.name}")
    private String routingKey;


    public void publishMessageEmail(AdminEntity adminEntity) {
        var adminDto = new EmailDto();
        adminDto.setAdminId(adminEntity.getAdminId());
        adminDto.setEmailTo(adminEntity.getEmail());
        adminDto.setSubjet("Cadastro realizado com sucesso! ");
        adminDto.setText(adminEntity.getName() + ", Seja bem vindo(a)!,aproveite o nosso marketplace ");


        rabbitTemplate.convertAndSend("", routingKey, adminDto);
    }

    public void publishMessageCreateProduct(CreateProductDto dto) {

        var productDto = new CreateProductDto();
        productDto.setTitle(dto.getTitle());
        productDto.setPrice(dto.getPrice());
        productDto.setDescription(dto.getDescription());
        productDto.setCategory(dto.getCategory());
        productDto.setImgUrl(dto.getImgUrl());
        productDto.setAdminId(dto.getAdminId());
//        productDto.setAdminEmail(dto.getAdminEmail());


        rabbitTemplate.convertAndSend("", routingKey, productDto);
//        rabbitTemplate.convertAndSend("product.v1.product-created.send-email", productDto);
    }
}
