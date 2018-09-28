package com.online.seva.repositories.mongodb;

import com.online.seva.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mongodb")
public interface MongoUserRepository extends MongoRepository<User, String> {
}