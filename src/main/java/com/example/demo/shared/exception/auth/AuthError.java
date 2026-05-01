package com.example.demo.shared.exception.auth;

import com.example.demo.shared.exception.ErrorDescriptor;

public enum AuthError implements ErrorDescriptor {

    INVALID_CREDENTIALS(
            "BUSINESS",
            401,
            "AUTH.INVALID_CREDENTIALS",
            "Thông tin đăng nhập không hợp lệ"
    ),

    USER_NOT_FOUND(
            "BUSINESS",
            404,
            "AUTH.USER_NOT_FOUND",
            "Không tìm thấy người dùng"
    ),

    INVALID_PASSWORD(
            "BUSINESS",
            401,
            "AUTH.INVALID_PASSWORD",
            "Mật khẩu không đúng"
    ),


    USER_NOT_ACTIVE(
            "BUSINESS",
            403,
            "AUTH.USER_NOT_ACTIVE",
            "Tài khoản chưa được kích hoạt"
    ),

    TOKEN_EXPIRED(
            "BUSINESS",
            401,
            "AUTH.TOKEN_EXPIRED",
            "Token đã hết hạn"
    ),

    TOKEN_INVALID(
            "BUSINESS",
            401,
            "AUTH.TOKEN_INVALID",
            "Token không hợp lệ"
    ),

    INVALID_REFRESH_TOKEN(
            "BUSINESS",
            401,
            "AUTH.INVALID_REFRESH_TOKEN",
            "Refresh token không hợp lệ"
    ),

    ACCESS_TOKEN_BLACKLISTED(
            "BUSINESS",
            401,
            "AUTH.ACCESS_TOKEN_BLACKLISTED",
            "Access token đã bị thu hồi"
    ),

    REFRESH_TOKEN_BLACKLISTED(
            "BUSINESS",
            401,
            "AUTH.REFRESH_TOKEN_BLACKLISTED",
            "Refresh token đã bị thu hồi"
    ),

    FORBIDDEN(
            "BUSINESS",
            403,
            "AUTH.FORBIDDEN",
            "Bạn không có quyền truy cập"
    ),

    TOO_MANY_LOGIN_ATTEMPTS(
            "BUSINESS",
            429,
            "AUTH.TOO_MANY_LOGIN_ATTEMPTS",
            "Quá nhiều lần đăng nhập thất bại. Vui lòng thử lại sau 5phút."
    ),

    TOO_MANY_REFRESH_ATTEMPTS(
            "BUSINESS",
            429,
            "AUTH.TOO_MANY_REFRESH_ATTEMPTS",
            "Quá nhiều lần làm mới token. Vui lòng thử lại sau."
    );

    private final String type;
    private final int httpStatus;
    private final String code;
    private final String defaultMessage;

    AuthError(String type, int httpStatus, String code, String defaultMessage) {
        this.type = type;
        this.httpStatus = httpStatus;
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public int httpStatus() {
        return httpStatus;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String defaultMessage() {
        return defaultMessage;
    }
}
