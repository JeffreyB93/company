package com.example.company.dao.mapper;

import com.example.company.dao.dto.CompanyDto;
import com.example.company.dao.entity.CompanyEntity;

public class CompanyMapper {
    public static CompanyDto fromEntity(CompanyEntity entity) {
        if (entity == null) return null;
        return CompanyDto.builder()
                .companyId(entity.getCompanyId())
                .contactId(entity.getContactId())
                .email(entity.getEmail())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
