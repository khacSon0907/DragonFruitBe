package com.example.demo.usecases.authService.loginUc;


import com.example.demo.domain.dto.request.LoginReq;
import com.example.demo.domain.dto.request.TokenPayload;
import com.example.demo.domain.dto.respone.LoginRes;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.infrastructure.redis.IRedisService;
import com.example.demo.repository.IUserRepository;
import com.example.demo.security.jwt.IJwtService;
import com.example.demo.security.jwt.JwtProperties;
import com.example.demo.shared.exception.BusinessException;
import com.example.demo.shared.exception.auth.AuthError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUcImpl implements ILoginUc {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final IRedisService redisService;
    private final JwtProperties jwtProperties;
    private final LoginAttemptService loginAttemptService;

    @Override
    public LoginRes login(LoginReq req) {

        String identifier = req.getIdentifier().trim();

        // 1️⃣ Check brute-force
        loginAttemptService.checkAttempts(identifier);

        // 2️⃣ Find user
        UserEntity user = findUserByIdentifier(identifier)
                .orElseThrow(() -> handleLoginFailed(identifier, AuthError.INVALID_CREDENTIALS));

        // 3️⃣ Validate user status
        // 4️⃣ Validate password
        validatePassword(req.getPassword(), user, identifier);

        // 5️⃣ Reset attempt
        loginAttemptService.loginSucceeded(identifier);

        // 6️⃣ Generate tokens
        String accessToken = jwtService.generateAccessToken(buildPayload(user));
        String refreshToken = jwtService.generateRefreshToken(buildPayload(user));

        // 7️⃣ Save refresh token
        saveRefreshToken(user, refreshToken);

        // 8️⃣ Build response
        return buildLoginResponse(user, accessToken, refreshToken);
    }

    // ================= PRIVATE METHODS =================

    private Optional<UserEntity> findUserByIdentifier(String identifier) {
        if (isEmail(identifier)) {
            return userRepository.findByEmail(identifier);
        }
        return userRepository.findByFullName(identifier);
    }

    private boolean isEmail(String input) {
        return input.contains("@");
    }



    private void validatePassword(String rawPassword, UserEntity user, String identifier) {
        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw handleLoginFailed(identifier, AuthError.INVALID_CREDENTIALS);
        }
    }

    private BusinessException handleLoginFailed(String identifier, AuthError error) {
        loginAttemptService.loginFailed(identifier);
        return new BusinessException(error);
    }

    private TokenPayload buildPayload(UserEntity user) {
        return TokenPayload.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }

    private void saveRefreshToken(UserEntity user, String refreshToken) {
        redisService.saveRefreshToken(
                user.getUserId(),
                refreshToken,
                Duration.ofMillis(jwtProperties.getRefreshTokenExpiration())
        );
    }

    private LoginRes buildLoginResponse(UserEntity user, String accessToken, String refreshToken) {
        return LoginRes.builder()
                .id(user.getUserId())
                .username(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .avatarUrl(user.getAvatarUrl())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(jwtProperties.getAccessTokenExpiration())
                .refreshTokenExpiresIn(jwtProperties.getRefreshTokenExpiration())
                .build();
    }
}