package dev.carlos.admin_service.repository;

import dev.carlos.admin_service.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {
    Optional<AdminEntity> findByEmail(String email);
}
