package com.example.demo.usecases.authService;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.request.LoginReq;
import com.example.demo.domain.dto.request.RefreshTokenReq;
import com.example.demo.domain.dto.respone.LoginRes;
import com.example.demo.domain.dto.respone.RefreshTokenRes;
import com.example.demo.domain.dto.respone.RegisterRespone;
import com.example.demo.usecases.useService.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserService userService;

    @Override
    public RegisterRespone register(CreateUserReq req) {
        var createResponse = userService.createUser(req);
        return RegisterRespone.builder()
                .userId(createResponse.getUserId())
                .email(createResponse.getEmail())
                .message(createResponse.getMessage())
                .build();
    }

    @Override
    public LoginRes login(LoginReq req) {
        // TODO: Implement login logic
        return null;
    }

    @Override
    public RefreshTokenRes refreshToken(RefreshTokenReq req) {
        // TODO: Implement refresh token logic
        return null;
    }

    @Override
    public void logout(String accessToken) {
        // TODO: Implement logout logic
    }
}
