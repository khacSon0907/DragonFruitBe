package com.example.demo.infrastructure.user.mapper;

import com.example.demo.domain.entities.UserEntity;
import com.example.demo.infrastructure.user.data.UserDocument;

public class UserMapper {

    public static UserEntity toEntity(UserDocument doc) {
        if (doc == null) return null;

        return UserEntity.builder()
                .userId(doc.getId())
                .email(doc.getEmail())
                .fullName(doc.getFullName())
                .phoneNumber(doc.getPhoneNumber())
                .avatarUrl(doc.getAvatarUrl())
                .passwordHash(doc.getPasswordHash())
                .role(doc.getRole())
                .provider(doc.getProvider())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }

    public static UserDocument toDocument(UserEntity entity) {
        if (entity == null) return null;

        return UserDocument.builder()
                .id(entity.getUserId())
                .email(entity.getEmail())
                .fullName(entity.getFullName())
                .phoneNumber(entity.getPhoneNumber())
                .avatarUrl(entity.getAvatarUrl())
                .passwordHash(entity.getPasswordHash())
                .role(entity.getRole())
                .provider(entity.getProvider())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}