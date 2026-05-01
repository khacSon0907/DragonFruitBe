package com.example.demo.usecases.authService.registerUC;


import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.respone.RegisterRespone;
import com.example.demo.usecases.useService.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUcImpl implements IRegisterUc{

    private final IUserService userService;

    @Override
    public RegisterRespone process(CreateUserReq req) {
        var createResponse = userService.createUser(req);
        return RegisterRespone.builder()
                .userId(createResponse.getUserId())
                .email(createResponse.getEmail())
                .message(createResponse.getMessage())
                .build();

    }

}
