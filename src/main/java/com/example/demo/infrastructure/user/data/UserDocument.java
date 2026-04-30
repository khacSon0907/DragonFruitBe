package com.example.demo.infrastructure.user.data;

import com.example.demo.domain.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDocument {

    @Id
    private String id;

    private String email;
    private String fullName;
    private String phoneNumber;
    private String avatarUrl;
    private String passwordHash;

    private Role role;

    private String provider; // LOCAL, GOOGLE, FACEBOOK

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}