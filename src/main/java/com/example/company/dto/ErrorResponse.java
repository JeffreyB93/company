package com.example.company.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class ErrorResponse {
    private String message;
    private int status;
    private Instant timestamp;
}
