package com.example.demo.usecases.authService.refreshTokenUc;

import com.example.demo.domain.dto.request.RefreshTokenReq;
import com.example.demo.domain.dto.request.TokenPayload;
import com.example.demo.domain.dto.respone.RefreshTokenRes;
import com.example.demo.infrastructure.redis.IRedisService;
import com.example.demo.security.jwt.IJwtService;
import com.example.demo.security.jwt.JwtProperties;
import com.example.demo.shared.exception.BusinessException;
import com.example.demo.shared.exception.auth.AuthError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RefreshTokenUcImpl implements IRefreshTokenUc {

    private final IJwtService jwtService;
    private final IRedisService redisService;
    private final JwtProperties jwtProperties;

    @Override
    public RefreshTokenRes refreshToken(RefreshTokenReq req) {

        // 1️⃣ Validate refresh token
        TokenPayload payload = jwtService.validateRefreshToken(req.getRefreshToken());
        String userId = payload.getUserId();

        // 2️⃣ Check Redis
        String storedRefreshToken = redisService.getRefreshToken(userId);

        if (storedRefreshToken == null || !storedRefreshToken.equals(req.getRefreshToken())) {
            redisService.deleteRefreshToken(userId);
            throw new BusinessException(AuthError.INVALID_REFRESH_TOKEN);
        }

        // 3️⃣ Generate new tokens
        String newAccessToken = jwtService.generateAccessToken(payload);
        String newRefreshToken = jwtService.generateRefreshToken(payload);

        // 4️⃣ Save new refresh token vào Redis
        redisService.saveRefreshToken(
                userId,
                newRefreshToken,
                Duration.ofMillis(jwtProperties.getRefreshTokenExpiration())
        );

        // 5️⃣ Return response
        return RefreshTokenRes.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .accessTokenExpiresIn(jwtProperties.getAccessTokenExpiration())
                .refreshTokenExpiresIn(jwtProperties.getRefreshTokenExpiration())
                .build();
    }
}