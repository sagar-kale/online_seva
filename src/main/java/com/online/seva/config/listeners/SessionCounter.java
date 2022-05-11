package com.online.seva.config.listeners;

import com.online.seva.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionCounter implements ServletContextListener, HttpSessionListener, ServletRequestListener {

    private static final String ATTRIBUTE_NAME = "com.online-seva.SessionCounter";
    private final Map<HttpSession, String> sessions = new ConcurrentHashMap<>();
    private final Map<HttpSession, String> loggedSessions = new ConcurrentHashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute(ATTRIBUTE_NAME, this);
        event.getServletContext().setAttribute("loggedUsers", loggedSessions);
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        HttpSession session = request.getSession();
        if (session.isNew()) {
            sessions.put(session, request.getRemoteAddr());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.remove(session);
        User user = (User) session.getAttribute("user");
        System.out.println("User in Session destroyed:::" + user);
        if (null != user) {
            loggedSessions.remove(event.getSession());
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // NOOP. Useless since we can't obtain IP here.
       /* HttpSession session = event.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("User in Session creation:::" + user);
        if (null != user)
            loggedSessions.put(session, user.getUsername());*/
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        // NOOP. No logic needed.
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // NOOP. No logic needed. Maybe some future cleanup?
    }

    public static SessionCounter getInstance(ServletContext context) {
        return (SessionCounter) context.getAttribute(ATTRIBUTE_NAME);
    }

    public int getOnlineUserByIP(String remoteAddr) {
        return Collections.frequency(sessions.values(), remoteAddr);
    }

    public int getLoggedUsersCount() {
        return loggedSessions.size();
    }

    public int getTotalUsers() {
        return sessions.size();
    }

}
