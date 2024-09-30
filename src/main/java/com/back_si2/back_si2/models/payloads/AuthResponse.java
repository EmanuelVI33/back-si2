package com.back_si2.back_si2.models.payloads;

import com.back_si2.back_si2.entities.UserRol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    Long userId;
    UserRol role;
}
