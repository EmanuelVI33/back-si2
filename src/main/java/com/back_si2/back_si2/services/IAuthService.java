package com.back_si2.back_si2.services;

import com.back_si2.back_si2.dto.ApiResponse;
import com.back_si2.back_si2.dto.auth.AuthResponse;
import com.back_si2.back_si2.dto.auth.LoginDto;
import com.back_si2.back_si2.dto.auth.RegisterDto;

public interface IAuthService {
    public ApiResponse<AuthResponse> login(LoginDto loginDto);

    public ApiResponse<AuthResponse> register(RegisterDto registerDto);
}
