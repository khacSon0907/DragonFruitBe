package com.example.demo.repository;

import com.example.demo.domain.entities.UserEntity;

import java.util.Optional;

public interface IUserRepository {

    Optional<UserEntity> findById(String id);

    UserEntity save(UserEntity user);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}