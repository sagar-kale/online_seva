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
import java.util.List;
import java.util.Optional;

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
        Role role_content_admin = new Role();
        role_content_admin.setRole("content_admin");
        List<Role> roleList;

        logger.info("Roles count :: " + count);
        if (count == 0) {
            roleList = new ArrayList<>();
            roleList.add(role_user);
            roleList.add(role_admin);
            roleList.add(role_content_admin);
            userRoleRepository.saveAll(roleList);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        if (user.getUsername().equalsIgnoreCase("sagark.kale@hotmail.com") || user.getUsername().equalsIgnoreCase("mohanpri93@gmail.com")) {
            Role adminRole = userRoleRepository.findByRole("admin");
            user.setRole(adminRole.getRole());
            user.setActive(true);
        } else {
            Role userRole = userRoleRepository.findByRole("user");
            user.setRole(userRole.getRole());
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
        user.setRole(userRole.getRole());
        jpaUserRepository.save(user);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(true);
        Role userRole = userRoleRepository.findByRole("admin");
        user.setRole(userRole.getRole());
        jpaUserRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<User> userList = jpaUserRepository.findAll();
        for (User user : userList) {
            user.setPassword(null);
            users.add(user);
        }
        return users;

    }

    public List<Role> findAllRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public User authenticateUser(String username, String password) {
        User loggedUser = jpaUserRepository.findByUsername(username);
        return null == loggedUser ? null : bCryptPasswordEncoder.matches(password, loggedUser.getPassword()) ? loggedUser : null;
    }

    @Override
    public Optional<User> findUserByResetToken(String resetToken) {
        return Optional.empty();
    }

    @Override
    public boolean updatePassword(String password, String username) {
        jpaUserRepository.updatePassword(password, username);
        return true;
    }

    @Override
    public boolean isUserExists(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public boolean updateUserActiveStatus(String username) {

        User byUsername = jpaUserRepository.findByUsername(username);
        logger.info("fetched user for updation" + byUsername);
        if (null == byUsername)
            return false;
        logger.info("updating user status :::: current status:: " + byUsername.isActive());
        if (byUsername.isActive())
            byUsername.setActive(false);
        else
            byUsername.setActive(true);
        User save = jpaUserRepository.save(byUsername);
        logger.info("updated user status :::: current status:: " + save.isActive());
        return true;
    }

    @Override
    public boolean updateUserRole(User user) {
        User byUsername = jpaUserRepository.findByUsername(user.getUsername());
        logger.info("fetched user for role update" + byUsername);
        if (null == byUsername)
            return false;
        logger.info("updating user role :::: current role:: " + byUsername.getRole());
        if (byUsername.getRole().equalsIgnoreCase(user.getRole()))
            return true;
        else
            byUsername.setRole(user.getRole());

        User save = jpaUserRepository.save(byUsername);
        logger.info("updated user role :::: current role:: " + save.getRole());
        return true;
    }

    @Override
    public boolean removeUser(String username) {
        User byUsername = jpaUserRepository.findByUsername(username);
        if (null == byUsername)
            return false;
        try {
            jpaUserRepository.delete(byUsername);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


}
