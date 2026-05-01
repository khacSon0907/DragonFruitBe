package com.example.demo.usecases.authService.registerUC;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.respone.RegisterRespone;

public interface IRegisterUc {
    RegisterRespone process(CreateUserReq req);
}
