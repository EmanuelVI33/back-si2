package com.back_si2.back_si2.controllers.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_si2.back_si2.models.dto.auth.LoginDto;
import com.back_si2.back_si2.models.dto.auth.RegisterDto;
import com.back_si2.back_si2.models.payloads.ApiResponseV1;
import com.back_si2.back_si2.models.payloads.AuthResponse;
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
    public ResponseEntity<ApiResponseV1<AuthResponse>> login(@RequestBody LoginDto loginDto) {
        ApiResponseV1<AuthResponse> response = authService.login(loginDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseV1<AuthResponse>> register(@RequestBody RegisterDto registerDto) {
        ApiResponseV1<AuthResponse> response = authService.register(registerDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
