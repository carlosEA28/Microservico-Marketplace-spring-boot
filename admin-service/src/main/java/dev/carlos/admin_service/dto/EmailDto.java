package dev.carlos.admin_service.dto;

import java.util.UUID;

public class EmailDto {
    private UUID adminId;
    private String emailTo;
    private String subjet;
    private String text;
//    private String verificationCode;
//
//    public String getVerificationCode() {
//        return verificationCode;
//    }
//
//    public void setVerificationCode(String verificationCode) {
//        this.verificationCode = verificationCode;
//    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubjet() {
        return subjet;
    }

    public void setSubjet(String subjet) {
        this.subjet = subjet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
