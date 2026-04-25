package com.example.company.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @GetMapping("/get")
    public ResponseEntity<String> getEmail() {
        return ResponseEntity.ok("asd");
    }
}
