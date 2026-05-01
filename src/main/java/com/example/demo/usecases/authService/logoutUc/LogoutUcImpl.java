package com.example.demo.usecases.authService.logoutUc;

import com.example.demo.infrastructure.redis.IRedisService;
import com.example.demo.security.jwt.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class LogoutUcImpl implements ILogoutUc {

    private final IRedisService redisService;
    private final IJwtService jwtService;

    @Override
    public void logout(String accessToken) {

        // 1️⃣ Lấy payload từ token
        String userId = jwtService.getUserIdFromToken(accessToken);

        // 2️⃣ Nếu access token chưa hết hạn → blacklist
        if (!jwtService.isTokenExpired(accessToken)) {

            long remainingTime = jwtService.getRemainingExpiration(accessToken);

            redisService.blacklistToken(
                    accessToken,
                    Duration.ofMillis(remainingTime)
            );
        }

        // 3️⃣ Xóa refresh token Redis
        redisService.deleteRefreshToken(userId);
    }
}