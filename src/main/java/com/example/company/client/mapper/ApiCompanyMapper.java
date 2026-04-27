package com.example.company.client.mapper;

import com.example.company.client.dto.AtiEmailResponse;
import com.example.company.dto.EmailDto;

public class ApiCompanyMapper {
    public static EmailDto fromAtiEmailResponse(AtiEmailResponse response) {
        if (response == null) return null;
        return EmailDto.builder()
                .email(response.getMessage())
                .build();
    }
}
