package com.example.company.dao.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CompanyDto {
    private Long companyId;
    private Long contactId;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
