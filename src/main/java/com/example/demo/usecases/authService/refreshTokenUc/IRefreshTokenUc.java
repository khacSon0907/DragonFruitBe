package com.example.demo.usecases.authService.refreshTokenUc;

import com.example.demo.domain.dto.request.RefreshTokenReq;
import com.example.demo.domain.dto.respone.RefreshTokenRes;

public interface IRefreshTokenUc {
    RefreshTokenRes refreshToken(RefreshTokenReq req);
}
