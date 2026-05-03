package com.example.demo.usecases.authService;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.request.LoginReq;
import com.example.demo.domain.dto.request.RefreshTokenReq;
import com.example.demo.domain.dto.respone.LoginRes;
import com.example.demo.domain.dto.respone.RefreshTokenRes;
import com.example.demo.domain.dto.respone.RegisterRespone;
import com.example.demo.usecases.authService.loginUc.ILoginUc;
import com.example.demo.usecases.authService.logoutUc.ILogoutUc;
import com.example.demo.usecases.authService.refreshTokenUc.IRefreshTokenUc;
import com.example.demo.usecases.authService.registerUC.IRegisterUc;
import com.example.demo.usecases.useService.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserService userService;
    private final IRegisterUc registerUc;
    private final ILoginUc loginUc;
    private final IRefreshTokenUc refreshTokenUc;
    private final ILogoutUc logoutUc;

    @Override
    public RegisterRespone register(CreateUserReq req) {
        return  registerUc.process(req);
    }

    @Override
    public LoginRes login(LoginReq req) {
        return loginUc.login(req);
    }

    @Override
    public RefreshTokenRes refreshToken(RefreshTokenReq req) {
        return refreshTokenUc.refreshToken(req);
    }

    @Override
    public void logout(String accessToken) {
        logoutUc.logout(accessToken);
    }
}
