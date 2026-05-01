package com.example.demo.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {

    @NotBlank(message = "fullname hoặc email không được để trống")
    private String identifier; // 👈 đổi tên cho đúng bản chất

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
}