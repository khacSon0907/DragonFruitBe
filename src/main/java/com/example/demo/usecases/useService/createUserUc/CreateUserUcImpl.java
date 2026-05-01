package com.example.demo.usecases.useService.createUserUc;

import com.example.demo.domain.dto.request.CreateUserReq;
import com.example.demo.domain.dto.respone.CreateUserResponse;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.enums.Role;
import com.example.demo.repository.IUserRepository;
import com.example.demo.shared.exception.BusinessException;
import com.example.demo.shared.exception.user.UserError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CreateUserUcImpl implements ICreateUserUc {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponse execute(CreateUserReq req) {

        // ✅ Validate email
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BusinessException(UserError.EMAIL_EXISTS);
        }

        if (req.getPhoneNumber() != null &&
                userRepository.existsByPhoneNumber(req.getPhoneNumber())) {
            throw new BusinessException(UserError.PHONE_EXISTS);
        }

        // 🔐 Hash password chuẩn bằng BCrypt
        String passwordHash = passwordEncoder.encode(req.getPassword());

        UserEntity user = UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .email(req.getEmail())
                .fullName(req.getFullName())
                .phoneNumber(req.getPhoneNumber())
                .passwordHash(passwordHash)
                .role(Role.FARMER)
                .provider("LOCAL")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // ✅ Save to DB
        user = userRepository.save(user);

        return CreateUserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .message("User created successfully")
                .build();
    }
}