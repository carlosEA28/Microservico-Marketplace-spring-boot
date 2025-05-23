package com.carlos.email_marketplace.dto;

import java.util.UUID;

public record EmailRecordDto(UUID adminId, String emailTo, String subjet, String text) {
}
