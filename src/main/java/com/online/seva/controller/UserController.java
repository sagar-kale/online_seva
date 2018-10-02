package com.online.seva.controller;


import com.online.seva.config.listeners.SessionCounter;
import com.online.seva.domain.OnlineUsers;
import com.online.seva.domain.Response;
import com.online.seva.domain.User;
import com.online.seva.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@ResponseBody
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response registration(@RequestBody User user) {
        Response response;
        logger.info("User Email::: " + user.getEmail());
        if (null == user.getEmail()) {

            response = new Response();
            response.setError("User details empty");
            return response;
        }
        user.setUsername(user.getEmail());
        if (userService.findByUsername(user.getUsername()) != null) {
            response = new Response();
            response.setError("Email already exists");
            return response;
        }
        user.setPasswordConfirm(user.getPassword());

        userService.save(user);
        logger.info("Password ::: " + user.getPassword());
        logger.info("Password plain ::: " + user.getPasswordConfirm());
        response = new Response();
        response.setMessage("Registration Successful, Please Login");
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response login(@RequestBody User user, HttpSession httpSession) {
        logger.info("Under Login");
        logger.info("request User" + user);
        Response response;
        if (null == user.getUsername() || null == user.getPassword()) {
            response = new Response();
            response.setError("Null Username and Password");
            return response;
        }
        User loggedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        logger.info("Logged User::: " + loggedUser);
        if (null == loggedUser) {
            response = new Response();
            response.setError("Invalid Username and Password");
            return response;
        }
        logger.info("Logged User success::: " + loggedUser);
        httpSession.setAttribute("user", loggedUser);
        response = new Response();
        loggedUser.setPassword(null);
        response.setUser(loggedUser);
        Map<HttpSession, String> logins = getLoggedUserAttr(httpSession);
        logins.put(httpSession, user.getUsername());
        logger.info("Leaving Login Controller");
        return response;
    }

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        logger.info("fetching all users");
        return userService.findAll();
    }

    @RequestMapping(value = "/logged/count", method = RequestMethod.GET)
    public OnlineUsers getLoggedUsersCount() {
        SessionCounter counter = SessionCounter.getInstance(context);
        logger.info("fetching all online uaers");
        OnlineUsers onlineUsers = new OnlineUsers();
        onlineUsers.setLoggedInUsers(counter.getLoggedUsersCount());
        onlineUsers.setTotalOnlineUsers(counter.getTotalUsers());
        return onlineUsers;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpSession session) {
        logger.info("fetching all users");
        User user = (User) session.getAttribute("user");
        if (null == user)
            return ResponseEntity.ok("You are already Logged Out");
        logger.info("logging out following user" + user);
        session.invalidate();
        Map<HttpSession, String> logins = getLoggedUserAttr(session);
        logins.remove(session, user.getUsername());
        return ResponseEntity.ok("SuccessFully Logged Out");
    }

    private Map<HttpSession, String> getLoggedUserAttr(HttpSession httpSession) {
        return (Map<HttpSession, String>) httpSession.getServletContext().getAttribute("loggedUsers");
    }
}
