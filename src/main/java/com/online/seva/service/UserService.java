package com.online.seva.service;

import com.online.seva.domain.Role;
import com.online.seva.domain.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findUserByEmail(String email);
    void saveUser(User user);
    void createUser(User user);
    List<User> findAll();
    List<Role> findAllRoles();
}
