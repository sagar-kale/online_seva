package com.online.seva.service;

import com.online.seva.domain.Role;
import com.online.seva.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findUserByEmail(String email);

    void saveUser(User user);

    void createUser(User user);

    List<User> findAll();

    List<Role> findAllRoles();

    User authenticateUser(String username, String password);

    Optional<User> findUserByResetToken(String resetToken);

    boolean updatePassword(String password, String username);

    boolean isUserExists(String username);

    boolean updateUserActiveStatus(String username);

    boolean removeUser(String username);
}
