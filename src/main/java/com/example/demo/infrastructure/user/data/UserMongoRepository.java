package com.example.demo.infrastructure.user.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByEmail(String email);

    Optional<UserDocument> findByFullName(String fullName);

    boolean existsByEmail(String email);

    boolean existsByFullName(String fullName);

    Optional<UserDocument> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}