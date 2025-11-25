package com.biblioteca.universidad.controller;

import com.biblioteca.universidad.dto.AuthResponse;
import com.biblioteca.universidad.dto.LoginRequest;
import com.biblioteca.universidad.dto.RegisterRequest;
import com.biblioteca.universidad.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registrarUsuario(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}