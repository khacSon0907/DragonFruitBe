package com.example.demo.controller;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.respone.RegisterRespone;
import com.example.demo.shared.response.ApiResponse;
import com.example.demo.usecases.authService.IAuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor    
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterRespone>> register(
            @Valid @RequestBody CreateUserReq req,
            HttpServletRequest request
    ) {
        RegisterRespone data = authService.register(req);

        return ResponseEntity.ok(
                ApiResponse.success(
                        200,
                        "REGISTER_SUCCESS",
                        "Register successfully",
                        data,
                        request.getRequestURI(),
                        null
                )
        );
    }
}
