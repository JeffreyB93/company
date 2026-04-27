package com.example.company.mapper;

import com.example.company.dao.dto.CompanyDto;
import com.example.company.dto.EmailResponse;

public class CompanyResponseMapper {
    public static EmailResponse fromDto(CompanyDto dto) {
        if (dto == null) return null;
        return EmailResponse.builder()
                .email(dto.getEmail())
                .build();
    }
}
