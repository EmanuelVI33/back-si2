package com.back_si2.back_si2.controllers.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_si2.back_si2.dto.ApiResponse;
import com.back_si2.back_si2.dto.auth.AuthResponse;
import com.back_si2.back_si2.dto.auth.LoginDto;
import com.back_si2.back_si2.dto.auth.RegisterDto;
import com.back_si2.back_si2.services.IAuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginDto loginDto) {
        ApiResponse<AuthResponse> response = authService.login(loginDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterDto registerDto) {
        ApiResponse<AuthResponse> response = authService.register(registerDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
