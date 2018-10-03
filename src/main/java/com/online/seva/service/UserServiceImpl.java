package com.online.seva.service;

import com.online.seva.controller.UserController;
import com.online.seva.domain.Role;
import com.online.seva.domain.User;
import com.online.seva.repositories.jpa.JpaUserRepository;
import com.online.seva.repositories.jpa.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    // @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public void save(User user) {
        long count = userRoleRepository.count();

        Role role_user = new Role();
        role_user.setRole("user");
        Role role_admin = new Role();
        role_admin.setRole("admin");
        List<Role> roleList;

        logger.info("Roles count :: " + count);
        if (count == 0) {
            roleList = new ArrayList<>();
            roleList.add(role_user);
            roleList.add(role_admin);
            userRoleRepository.saveAll(roleList);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        if (user.getName().equalsIgnoreCase("sagar kale") || user.getName().equalsIgnoreCase("mohan randive")) {
            List<Role> roles = userRoleRepository.findAll();
            logger.info("all roles ::: " + roles);
            user.setRoles(roles);
        } else {
            Role userRole = userRoleRepository.findByRole("user");
            user.setRoles(new ArrayList<>(Arrays.asList(userRole)));
        }
        jpaUserRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return jpaUserRepository.findByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setActive(true);
        Role userRole = userRoleRepository.findByRole("admin");
        user.setRoles(new ArrayList<>(Arrays.asList(userRole)));
        jpaUserRepository.save(user);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(true);
        Role userRole = userRoleRepository.findByRole("admin");
        user.setRoles(new ArrayList<>(Arrays.asList(userRole)));
        jpaUserRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }

    public List<Role> findAllRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public User authenticateUser(String username, String password) {
        User loggedUser = jpaUserRepository.findByUsername(username);
        return null == loggedUser ? null : bCryptPasswordEncoder.matches(password, loggedUser.getPassword()) ? loggedUser : null;
    }

}
