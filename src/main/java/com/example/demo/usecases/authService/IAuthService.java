package com.example.demo.usecases.authService;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.request.LoginReq;
import com.example.demo.domain.dto.request.RefreshTokenReq;
import com.example.demo.domain.dto.respone.LoginRes;
import com.example.demo.domain.dto.respone.RefreshTokenRes;
import com.example.demo.domain.dto.respone.RegisterRespone;

public interface IAuthService {

    RegisterRespone register(CreateUserReq req);

    LoginRes login(LoginReq req);

    RefreshTokenRes refreshToken(RefreshTokenReq req);

    void logout(String accessToken);
}
