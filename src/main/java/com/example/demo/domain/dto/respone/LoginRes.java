package com.example.demo.domain.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {
    private String id;
    private String username;
    private String email;
    private String role;
    private String fullName;
    private String phoneNumber;
    private String avatarUrl;
    private String accessToken; // Access Token (ngắn hạn)
    private String refreshToken; // Refresh Token (dài hạn)
    private long accessTokenExpiresIn; // Thời gian hết hạn của access token (ms)
    private long refreshTokenExpiresIn; // Thời gian hết hạn của refresh token (ms)
}
