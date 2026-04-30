package com.example.demo.usecases.useService.createUserUc;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.respone.CreateUserResponse;

public interface ICreateUserUc {

    CreateUserResponse execute(CreateUserReq req);

}
