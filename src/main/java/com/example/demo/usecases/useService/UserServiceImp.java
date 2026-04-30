package com.example.demo.usecases.useService;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.respone.CreateUserResponse;
import com.example.demo.usecases.useService.createUserUc.ICreateUserUc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final ICreateUserUc createUserUc;

    @Override
    public CreateUserResponse createUser(CreateUserReq req) {
        return createUserUc.execute(req);
    }
}
