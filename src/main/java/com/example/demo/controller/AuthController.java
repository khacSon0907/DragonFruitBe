package com.example.demo.controller;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.request.LoginReq;
import com.example.demo.domain.dto.request.RefreshTokenReq;
import com.example.demo.domain.dto.respone.LoginRes;
import com.example.demo.domain.dto.respone.RefreshTokenRes;
import com.example.demo.domain.dto.respone.RegisterRespone;
import com.example.demo.shared.response.ApiResponse;
import com.example.demo.usecases.authService.IAuthService;

import com.example.demo.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginRes>> login(
            @RequestBody LoginReq req,
            HttpServletResponse response,
            HttpServletRequest request
    ) {

        LoginRes res = authService.login(req);

        // lưu refresh token vào cookie
        CookieUtil.addCookie(
                response,
                "refreshToken",
                res.getRefreshToken(),
                7 * 24 * 60 * 60,
                "/"
        );
        // không trả refresh token trong body
        res.setRefreshToken(null);
        return ResponseEntity.ok(
                ApiResponse.success(
                        200,
                        "LOGIN_SUCCESS",
                        "Login successful",
                        res,
                        request.getRequestURI(),
                        null
                )
        );

    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<RefreshTokenRes>> refresh(
            HttpServletRequest request,
            HttpServletResponse response  // ✅ thêm vào
    ) {
        String refreshToken = CookieUtil.getCookie(request, "refreshToken");

        System.out.println("Refresh token from cookie: " + refreshToken); // ✅ log token để debug
        if (refreshToken == null) {
            throw new RuntimeException("Refresh token not found");
        }

        RefreshTokenReq req = RefreshTokenReq.builder()
                .refreshToken(refreshToken)
                .build();

        RefreshTokenRes data = authService.refreshToken(req);

        // ✅ Set refresh token mới vào cookie sau khi rotate
        CookieUtil.addCookie(
                response,
                "refreshToken",
                data.getRefreshToken(),
                7 * 24 * 60 * 60,
                "/"
        );

        // ✅ Không trả refreshToken trong body
        data.setRefreshToken(null);

        return ResponseEntity.ok(
                ApiResponse.success(
                        200,
                        "REFRESH_SUCCESS",
                        "Token refreshed successfully",
                        data,
                        request.getRequestURI(),
                        null
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String authHeader = request.getHeader("Authorization");

        String accessToken = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            accessToken = authHeader.substring(7);
        }

        authService.logout(accessToken);
        // xoá refresh token cookie
        CookieUtil.clearCookie(
                response,
                "refreshToken",
                "/"
        );
        return ResponseEntity.ok(
                ApiResponse.success(
                        200,
                        "LOGOUT_SUCCESS",
                        "Logout successful",
                        "OK",
                        request.getRequestURI(),
                        null
                )
        );
    }


}
