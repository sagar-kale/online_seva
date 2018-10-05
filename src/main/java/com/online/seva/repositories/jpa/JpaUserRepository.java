package com.online.seva.repositories.jpa;

import com.online.seva.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!mongodb"})
public interface JpaUserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    User findByEmail(String email);

    @Modifying
    @Query("update user_details u set u.password = :password where u.username = :username")
    void updatePassword(@Param("password") String password, @Param("username") String username);

    void deleteUserByUsername(String username);
    boolean existsByUsername(String username);

}
