package com.example.demo.domain.entities;

import com.example.demo.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserEntity {
    private String userId;
    private String email ;
    private String fullName;
    private String phoneNumber;
    private String avatarUrl;
    private String passwordHash;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Role role;
    private String provider; // LOCAL, GOOGLE, FACEBOOK
}
