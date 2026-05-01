package com.example.demo.usecases.authService.loginUc;

import com.example.demo.infrastructure.redis.IRedisService;
import com.example.demo.shared.exception.BusinessException;
import com.example.demo.shared.exception.auth.AuthError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class LoginAttemptService {

    private final IRedisService redisService;

    private static final int MAX_ATTEMPTS = 5;

    public void checkAttempts(String username) {

        int attempts = redisService.getLoginAttempts(username);

        if (attempts >= MAX_ATTEMPTS) {
            throw new BusinessException(AuthError.TOO_MANY_LOGIN_ATTEMPTS);
        }
    }

    public void loginFailed(String username) {

        redisService.incrementLoginAttempts(username);
        redisService.setLoginAttemptsExpire(username, Duration.ofMinutes(5));
    }

    public void loginSucceeded(String username) {

        redisService.resetLoginAttempts(username);
    }
}