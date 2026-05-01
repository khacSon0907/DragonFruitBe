package com.example.demo.usecases.authService.loginUc;

import com.example.demo.domain.dto.request.LoginReq;
import com.example.demo.domain.dto.respone.LoginRes;

public interface ILoginUc {
    LoginRes login(LoginReq req);
}
