package com.online.seva.service;

import com.online.seva.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
@Slf4j
public class SessionService {
    @Autowired
    private UserService userService;

    public Map<HttpSession, String> getLoggedUserAttr(HttpSession httpSession) {
        return (Map<HttpSession, String>) httpSession.getServletContext().getAttribute("loggedUsers");
    }

    public User getLoggedUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null == session)
            return null;
        return (User) session.getAttribute("user");
    }

    public boolean isAdmin(User loggedUser) {
        boolean isAdmin = false;
        User byUsername = userService.findByUsername(loggedUser.getUsername());
        log.info("User Role::" + byUsername.getRole());
        if (byUsername.getRole().equalsIgnoreCase("admin"))
            isAdmin = true;
        log.info("isAdmin ::: " + isAdmin);
        return isAdmin;
    }

    public boolean isContentAdmin(User loggedUser) {
        boolean isAdmin = false;
        User byUsername = userService.findByUsername(loggedUser.getUsername());
        log.info("User Role::" + byUsername.getRole());
        if (byUsername.getRole().equalsIgnoreCase("content_admin"))
            isAdmin = true;
        log.info("is Content Admin ::: " + isAdmin);
        return isAdmin;
    }

    public boolean checkForSuperAdmin(String username) {
        log.info("checking if any action against super admin::" + username);
        if (username.equalsIgnoreCase("sagark.kale@hotmail.com"))
            return true;
        return false;
    }
}
