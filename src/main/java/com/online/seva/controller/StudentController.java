package com.online.seva.controller;

import com.online.seva.domain.Response;
import com.online.seva.domain.Student;
import com.online.seva.domain.User;
import com.online.seva.service.SessionService;
import com.online.seva.service.StudentService;
import com.online.seva.service.UserService;
import com.online.seva.util.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@Slf4j
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response registration(@RequestBody Student student, HttpServletRequest request) {
        Response response;
        log.info("Under Student controller :: register");
        User loggedUser = sessionService.getLoggedUser(request);
        log.info("checking logged user  ");
        if (null == loggedUser) {
            response = new Response();
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Session expired .. please login again");
            return response;
        }
        log.info("logged user:: true");
        log.info("Student Email::: " + student.getEmail());
        log.info("registering Student:: " + student);
        if (null == student) {
            response = new Response();
            response.setMsgType(AppConstant.ERROR);
            if (null == student.getEmail())
                response.setMessage("Student email cant be empty");
            response.setMessage("Student details empty");
            return response;
        }
        log.info("Fetching user for registering its student ::: " + student.getUsername());
        if (!userService.isUserExists(student.getUsername())) {
            response = new Response();
            response.setMessage("User does not exists...");
            response.setMsgType(AppConstant.ERROR);
            return response;
        }
        log.info("Checking if student is already exists ::: " + student.getEmail());
        if (studentService.isExists(student.getEmail())) {
            response = new Response();
            response.setMessage("Student Email already exists");
            response.setMsgType(AppConstant.ERROR);
            return response;
        }
        User byUsername = userService.findByUsername(student.getUsername());
        student.setUser(byUsername);
        studentService.saveStudent(student);

        response = new Response();
        response.setMessage("Student added successfully");
        response.setMsgType(AppConstant.SUCCESS);
        return response;
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getAllStudent(@RequestBody String username, HttpServletRequest request) {
        Response response = new Response();
        log.info("Under Student controller :: register");
        User loggedUser = sessionService.getLoggedUser(request);
        log.info("checking logged user  ");
        if (null == loggedUser) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Session expired .. please login again");
            return response;
        }
        log.info("logged user:: true");
        log.info("fetching all student corresponds to :: " + username);
        response.setMsgType(AppConstant.SUCCESS);
        List<Student> all = studentService.findAll(username);
        response.setDataList(all);
        return response;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Response fetchStdents(HttpServletRequest request) {
        log.info("fetching all student");
        Response response = new Response();
        User loggedUser = sessionService.getLoggedUser(request);
        log.info("checking logged user..");
        if (null == loggedUser) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Session expired .. please login again");
            return response;
        }
        log.info("logged user:: true");
        response.setMsgType(AppConstant.SUCCESS);
        response.setDataList(studentService.findAll());
        return response;

    }

    @RequestMapping(value = "/update/status", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response changeStatus(@RequestBody String email, HttpServletRequest request) {
        log.info("Under Student Update status");
        Response response = new Response();

        if (email == null) {
            response.setMessage("User name should not be null:::" + email);
            response.setMsgType(AppConstant.ERROR);
            return response;
        }

        User loggedUser = sessionService.getLoggedUser(request);
        if (null == loggedUser) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Session expired .. please login again");
            return response;
        }
        log.info("logged User ::: True");
        log.info("User Role::" + loggedUser.getRole());
        if (!sessionService.isAdmin(loggedUser)) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("You are not allowed to update user status,Please refresh browser for effect");
            return response;
        }
        log.info("Updating student approve status :::" + email);
        boolean updated = studentService.updateStudentApproveStatus(email);
        if (!updated) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("User Not found in database...");
            return response;
        }
        response.setMsgType(AppConstant.SUCCESS);
        response.setMessage("Updated.... ");
        return response;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response removeStudent(@RequestBody String email, HttpServletRequest request) {
        log.info("Under Student Remove");
        Response response = new Response();

        if (email == null) {
            response.setMessage("Student email should not be null:::");
            response.setMsgType(AppConstant.ERROR);
            return response;
        }

        User loggedUser = sessionService.getLoggedUser(request);
        if (null == loggedUser) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Session expired .. please login again");
            return response;
        }

        /*if (!sessionService.isAdmin(loggedUser)) {
            response.setMsgType(AppConstant.ERROR);
            response.setMsgType("You are not allowed to delete user");
            return response;
        }*/
        log.info("Removing Student :::" + email);
        boolean isRemoved = studentService.removeStudent(email);
        if (!isRemoved) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Student Not found in database...");
            return response;
        }
        response.setMsgType(AppConstant.SUCCESS);
        response.setMessage("Student Removed SuccessFully");
        return response;
    }
}
