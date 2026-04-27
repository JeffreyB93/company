package com.example.company.dao.service;

import com.example.company.dao.dto.CompanyDto;
import com.example.company.dao.entity.CompanyEntity;
import com.example.company.dao.mapper.CompanyMapper;
import com.example.company.dao.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyDto get(Long companyId, Long contactId) {
       CompanyEntity entity = companyRepository.findByCompanyIdAndContactId(companyId, contactId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        return CompanyMapper.fromEntity(entity);
    }

    @Transactional
    public CompanyDto save(Long companyId, Long contactId, String email) {
        CompanyEntity entity = CompanyEntity.builder()
                .companyId(companyId)
                .contactId(contactId)
                .email(email)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        companyRepository.save(entity);
        return CompanyMapper.fromEntity(entity);
    }

    @Transactional
    public CompanyDto update(Long companyId, Long contactId, String email) {
        CompanyEntity entity = companyRepository.findByCompanyIdAndContactId(companyId, contactId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        entity.setEmail(email);
        entity.setUpdatedAt(LocalDateTime.now());
        companyRepository.save(entity);
        return CompanyMapper.fromEntity(entity);
    }
}
