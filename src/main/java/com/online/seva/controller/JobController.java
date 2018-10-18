package com.online.seva.controller;

import com.online.seva.domain.Job;
import com.online.seva.domain.Response;
import com.online.seva.domain.User;
import com.online.seva.service.JobService;
import com.online.seva.service.SessionService;
import com.online.seva.util.AppConstant;
import com.online.seva.util.JobRemoveScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@Slf4j
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private JobRemoveScheduler jobRemoveScheduler;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Job> getAllJobs() {
        return jobService.retrieveAllJobs();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response saveJob(@RequestBody Job job, HttpServletRequest request) {
        Response response = new Response();
        User loggedUser = sessionService.getLoggedUser(request);
        if (null == loggedUser) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Session expired .. please login again");
            return response;
        }
        log.info("logged User ::: True");
        log.info("User Role::" + loggedUser.getRole());
        if (!sessionService.isAdmin(loggedUser) && !sessionService.isContentAdmin(loggedUser)) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("You are not allowed to save jobs,Please contact admin");
            return response;
        }

        if (jobService.saveJob(job)) {
            response.setMessage("Job Details Saved Successfully");
            response.setMsgType(AppConstant.SUCCESS);
        } else {
            response.setMessage("Some Error occurred while saving job");
            response.setMsgType(AppConstant.ERROR);
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Response updateJob(@RequestBody Job job, HttpServletRequest request) {
        Response response = new Response();
        User loggedUser = sessionService.getLoggedUser(request);
        if (null == loggedUser) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Session expired .. please login again");
            return response;
        }
        log.info("logged User ::: True");
        log.info("User Role::" + loggedUser.getRole());
        if (!sessionService.isAdmin(loggedUser) && !sessionService.isContentAdmin(loggedUser)) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("You are not allowed to save jobs,Please contact admin!!");
            return response;
        }

        if (jobService.updateJob(job)) {
            response.setMessage("Job Details Updated Successfully");
            response.setMsgType(AppConstant.SUCCESS);
        } else {
            response.setMessage("Can't find job in our record");
            response.setMsgType(AppConstant.ERROR);
        }
        return response;
    }

    @RequestMapping(value = "/launch", method = RequestMethod.GET)
    public Response updateJob(HttpServletRequest request) {
        Response response = new Response();
        User loggedUser = sessionService.getLoggedUser(request);
        if (null == loggedUser) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Session Expired .. Please Login...");
            return response;
        }
        if (!sessionService.isAdmin(loggedUser)) {
            response.setMsgType(AppConstant.ERROR);
            response.setMessage("Permission denied,Please contact admin");
            return response;
        }
        boolean toggle = jobRemoveScheduler.toggle();
        response.setMsgType(AppConstant.SUCCESS);
        if (toggle) {
            response.setMessage("Job Activated");
            log.info("job activated");
            return response;
        }
        log.info("job deactivated..");
        response.setMessage("Job Deactivated");
        return response;

    }
}
