package com.example.company.controller;

import com.example.company.dao.dto.CompanyDto;
import com.example.company.dto.EmailResponse;
import com.example.company.mapper.CompanyResponseMapper;
import com.example.company.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/get")
    public ResponseEntity<EmailResponse> getEmail(
            @RequestParam Long companyId,
            @RequestParam Long contactId) {
        CompanyDto companyDto = emailService.get(companyId, contactId);
        EmailResponse emailResponse = CompanyResponseMapper.fromDto(companyDto);
        return ResponseEntity.ok(emailResponse);
    }
}
