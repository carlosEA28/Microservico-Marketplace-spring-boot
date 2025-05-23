package dev.carlos.admin_service.entity;

import dev.carlos.admin_service.enums.AccountActive;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin_table")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID adminId;

    @NotBlank(message = "The field [name] cant be empty")
    private String name;

    @NotBlank(message = "The field [phoneNumber] cant be empty")
    private String phoneNumber;

    @NotBlank(message = "The field [email] cant be empty")
    @Email
    private String email;

//    @Size(min = 6, max = 8, message = "Password must be between 6 and 8 characters")
    @NotBlank(message = "The field [password] cant be empty")
    private String password;


    @Enumerated(EnumType.STRING)
    private AccountActive isActive;

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountActive getIsActive() {
        return isActive;
    }

    public void setIsActive(AccountActive isActive) {
        this.isActive = isActive;
    }
}
