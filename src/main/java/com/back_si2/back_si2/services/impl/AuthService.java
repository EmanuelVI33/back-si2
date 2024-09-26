package com.back_si2.back_si2.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.back_si2.back_si2.Jwt.JwtService;
import com.back_si2.back_si2.dto.ApiResponse;
import com.back_si2.back_si2.dto.auth.AuthResponse;
import com.back_si2.back_si2.dto.auth.LoginDto;
import com.back_si2.back_si2.dto.auth.RegisterDto;
import com.back_si2.back_si2.entities.User;
import com.back_si2.back_si2.entities.UserRol;
import com.back_si2.back_si2.persistence.IUserDao;
import com.back_si2.back_si2.services.IAuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
        final IUserDao userDao;
        final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        @Override
        public ApiResponse<AuthResponse> login(LoginDto loginDto) {
                try {
                        authenticationManager
                                        .authenticate(
                                                        new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                                                                        loginDto.getPassword()));
                        User user = userDao.findByUsername(loginDto.getUsername()).orElseThrow();
                        String token = jwtService.getToken(user);
                        return ApiResponse.<AuthResponse>builder()
                                        .status(200)
                                        .message("Autentificación exitosa")
                                        .data(AuthResponse.builder().token(token).userId(user.getId()).build())
                                        .build();

                } catch (AuthenticationException e) {
                        return ApiResponse.<AuthResponse>builder()
                                        .status(403)
                                        .success(false)
                                        .message("Credenciales inválidas")
                                        .build();
                } catch (Exception e) {
                        return ApiResponse.<AuthResponse>builder()
                                        .status(500)
                                        .success(false)
                                        .message("Error interno del servidor: " + e.getMessage())
                                        .build();
                }
        }

        @Override
        public ApiResponse<AuthResponse> register(RegisterDto registerDto) {
                try {
                        User user = User.builder().name(registerDto.getName()).username(registerDto.getUsername())
                                        .password(passwordEncoder.encode(registerDto.getPassword())).role(UserRol.USER)
                                        .build();
                        userDao.save(user);
                        String token = jwtService.getToken(user);
                        return ApiResponse.<AuthResponse>builder()
                                        .status(200)
                                        .message("Autentificación exitosa")
                                        .data(AuthResponse.builder().token(token).userId(user.getId()).build())
                                        .build();
                } catch (AuthenticationException e) {
                        return ApiResponse.<AuthResponse>builder()
                                        .status(403)
                                        .success(false)
                                        .message("Credenciales inválidas")
                                        .build();
                } catch (Exception e) {
                        return ApiResponse.<AuthResponse>builder()
                                        .status(500)
                                        .success(false)
                                        .message("Error interno del servidor: " + e.getMessage())
                                        .build();
                }
        }

}
