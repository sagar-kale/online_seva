package com.online.seva.controller;

import com.online.seva.domain.Job;
import com.online.seva.domain.Response;
import com.online.seva.service.JobService;
import com.online.seva.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@ResponseBody
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    List<Job> getAllJobs() {
        return jobService.retrieveAllJobs();
    }

    @PostMapping("/save")
    Response saveJob(@RequestBody Job job) {
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
