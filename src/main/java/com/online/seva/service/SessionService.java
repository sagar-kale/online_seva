package com.online.seva.service;

import com.online.seva.domain.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
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
}
