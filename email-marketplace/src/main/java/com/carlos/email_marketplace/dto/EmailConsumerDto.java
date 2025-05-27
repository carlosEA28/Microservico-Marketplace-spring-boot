package com.carlos.email_marketplace.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmailConsumerDto(String email, String title) {
}
