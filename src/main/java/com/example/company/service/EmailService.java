package com.example.company.service;

import com.example.company.client.AtiCompanyClient;
import com.example.company.client.dto.AtiEmailResponse;
import com.example.company.client.mapper.ApiCompanyMapper;
import com.example.company.dao.dto.CompanyDto;
import com.example.company.dao.service.CompanyService;
import com.example.company.dto.EmailDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final CompanyService companyService;
    private final AtiCompanyClient atiCompanyClient;

    public CompanyDto get(Long companyId, Long contactId) {
        CompanyDto companyDto;
        try {
            companyDto = companyService.getCompany(companyId, contactId);
            String email = companyDto.getEmail();
            if (email == null || email.isEmpty()) {
                EmailDto emailDto = fetchEmail(companyId, contactId);
                return companyService.update(companyId, contactId, emailDto.getEmail());
            } else {
                return companyDto;
            }
        } catch (EntityNotFoundException e) {
            EmailDto emailDto = fetchEmail(companyId, contactId);
            return companyService.save(companyId, contactId, emailDto.getEmail());
        }
    }

    private EmailDto fetchEmail(Long companyId, Long contactId) {
        AtiEmailResponse atiEmailResponse = atiCompanyClient.fetchEmail(companyId.toString(), contactId.toString());
        return ApiCompanyMapper.fromAtiEmailResponse(atiEmailResponse);
    }
}
