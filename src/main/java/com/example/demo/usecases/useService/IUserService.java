package com.example.demo.usecases.useService;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.respone.CreateUserResponse;

public interface IUserService {

    CreateUserResponse createUser(CreateUserReq req);

}