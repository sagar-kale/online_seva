package com.online.seva.service;

import com.online.seva.domain.Job;

import java.util.List;

public interface JobService {
    boolean saveJob(Job job);
    List<Job> retrieveAllJobs();
}
