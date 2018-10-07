package com.online.seva.service;

import com.online.seva.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
@Slf4j
public class SessionService {
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
        log.info("User Role::" + loggedUser.getRole());
        if (loggedUser.getRole().equalsIgnoreCase("admin"))
            isAdmin = true;
        log.info("isAdmin ::: " + isAdmin);
        return isAdmin;
    }
}
