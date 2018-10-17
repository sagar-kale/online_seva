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
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Response updateJob(@RequestBody Job job) {
        Response response = new Response();

        if (jobService.updateJob(job)) {
            response.setMessage("Job Details Updated Successfully");
            response.setMsgType(AppConstant.SUCCESS);
        } else {
            response.setMessage("Can't find job in our record");
            response.setMsgType(AppConstant.ERROR);
        }
        return response;
    }
}
