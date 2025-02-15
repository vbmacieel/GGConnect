package com.project.ggconnect.tcg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ggconnect.tcg.dto.auth.AuthLoginDTO;
import com.project.ggconnect.tcg.dto.auth.AuthRegisterDTO;
import com.project.ggconnect.tcg.dto.auth.AuthResponseDTO;
import com.project.ggconnect.tcg.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthLoginDTO dto) {
        AuthResponseDTO authResponse = service.loginUser(dto);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody AuthRegisterDTO dto) {
        service.registerUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
