package com.example.demo.domain.dto.request;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenPayload {
    private String userId;
    private String role;
    private String fullName;
    private String email;

}
