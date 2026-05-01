package com.example.demo.infrastructure.user;

import com.example.demo.domain.entities.UserEntity;
import com.example.demo.infrastructure.user.data.UserMongoRepository;
import com.example.demo.infrastructure.user.mapper.UserMapper;
import com.example.demo.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final UserMongoRepository mongoRepository;

    @Override
    public Optional<UserEntity> findById(String id) {
        return mongoRepository.findById(id)
                .map(UserMapper::toEntity);
    }

    @Override
    public UserEntity save(UserEntity user) {
        var doc = UserMapper.toDocument(user);
        var saved = mongoRepository.save(doc);
        return UserMapper.toEntity(saved);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return mongoRepository.findByEmail(email)
                .map(UserMapper::toEntity);
    }

    @Override
    public Optional<UserEntity> findByPhoneNumber(String phoneNumber) {
        return mongoRepository.findByPhoneNumber(phoneNumber)
                .map(UserMapper::toEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return mongoRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return mongoRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<UserEntity> findByFullName(String fullName) {
        return mongoRepository.findByFullName(fullName)
                .map(UserMapper::toEntity);
    }
}