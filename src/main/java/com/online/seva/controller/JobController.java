package com.online.seva.controller;

import com.online.seva.domain.Job;
import com.online.seva.domain.Response;
import com.online.seva.service.JobService;
import com.online.seva.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Job> getAllJobs() {
        return jobService.retrieveAllJobs();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response saveJob(@RequestBody Job job) {
        Response response = new Response();

        if (jobService.saveJob(job)) {
            response.setMessage("Job Details Saved Successfully");
            response.setMsgType(AppConstant.SUCCESS);
        } else {
            response.setMessage("Some Error occurred while saving job");
            response.setMsgType(AppConstant.ERROR);
        }
        return response;
    }
}
