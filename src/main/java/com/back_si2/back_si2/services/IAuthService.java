package com.back_si2.back_si2.services;

import com.back_si2.back_si2.models.dto.auth.LoginDto;
import com.back_si2.back_si2.models.dto.auth.RegisterDto;
import com.back_si2.back_si2.models.payloads.ApiResponseV1;
import com.back_si2.back_si2.models.payloads.AuthResponse;

public interface IAuthService {
    public ApiResponseV1<AuthResponse> login(LoginDto loginDto);

    public ApiResponseV1<AuthResponse> register(RegisterDto registerDto);
}
