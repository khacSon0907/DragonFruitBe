package com.example.demo.shared.exception.user;

import com.example.demo.shared.exception.ErrorDescriptor;

public enum UserError implements ErrorDescriptor {

    EMAIL_EXISTS(
            "BUSINESS",
            409,
            "USER.EMAIL_EXISTS",
            "Email đã tồn tại"
    ),

    PHONE_EXISTS(
            "BUSINESS",
            409,
            "USER.PHONE_EXISTS",
            "Số điện thoại đã tồn tại"
    ),

    FULLNAME_EXISTS(
            "BUSINESS",
            409,
            "USER.FULLNAME_EXISTS",
            "Họ và tên đã tồn tại"
    );

    private final String type;
    private final int httpStatus;
    private final String code;
    private final String defaultMessage;

    UserError(String type, int httpStatus, String code, String defaultMessage) {
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