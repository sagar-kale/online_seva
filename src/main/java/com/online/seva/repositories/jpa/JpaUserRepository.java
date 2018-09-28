package com.online.seva.repositories.jpa;

import com.online.seva.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!mongodb"})
public interface JpaUserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);
}
