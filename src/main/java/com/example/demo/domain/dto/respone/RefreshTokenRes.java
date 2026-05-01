package com.example.demo.domain.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRes {
    private String accessToken; // Access Token mới
    private String refreshToken; // Refresh Token mới (optional)
    private long accessTokenExpiresIn; // Thời gian hết hạn của access token (ms)
    private long refreshTokenExpiresIn; // Thời gian hết hạn của refresh token (ms)
}
